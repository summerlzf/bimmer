package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.FileHolder;
import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.cache.CacheHolder;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.model.FileInfo;
import com.kedu.bimmer.model.UserInfo;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.service.CommentService;
import com.kedu.bimmer.service.FileInfoService;
import com.kedu.bimmer.service.UserInfoService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jef
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping("/article/item/{id}")
    public String article(Model model, @PathVariable("id") String id) {
        Article vo = GUID.isGUID(id) ? articleService.get(id) : null;
        if (vo == null || vo.isHidden()) { // 隐藏的文章不显示
            return "redirect:/";
        }
		UserInfo user = userInfoService.get(vo.getAuthorUserId());
        ArticleDTO dto = new ArticleDTO();
        dto.setArticleId(id);
        dto.setTitle(vo.getTitle());
        dto.setSubTitle(vo.getSubTitle());
        dto.setContent(vo.getContent());
        dto.setContents(separate(vo.getContent())); // 将文章进行分段处理
		dto.setOriginalUrl(vo.getOriginalUrl());
        dto.setAuthorUserName(user == null ? "--" : CommonUtil.isBlank(user.getNickName()) ? user.getUserName() : user.getNickName()); // 优先获取昵称，其次获取用户名
        dto.setViewCount(vo.getViewCount() + 1);
        dto.setAllowComment(vo.isAllowComment());
        dto.setCreateTimeStr(vo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("vo", dto);
        model.addAttribute("comments", commentService.listByArticleId(id));
        model.addAttribute("year", LocalDate.now().getYear());
        // 异步更新浏览次数
        articleService.updateViewCount(id);
        return "article";
    }

    @RequestMapping("/article/preview/{token}")
    public String articlePrev(Model model, @PathVariable("token") String token) {
        // 从缓存中token对应的预览内容
        Article vo = CacheHolder.get(token);
        if (vo == null) {
            return "redirect:/";
        }
        LocalDateTime now = LocalDateTime.now();
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(vo.getTitle());
        dto.setSubTitle(vo.getSubTitle());
        dto.setContents(separate(vo.getContent())); // 将文章进行分段处理
		dto.setOriginalUrl(vo.getOriginalUrl());
        dto.setAuthorUserName("预览者(Jacky)");
        dto.setAllowComment(false);
        dto.setCreateTimeStr(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("vo", dto);
        model.addAttribute("comments", new ArrayList<>());
        model.addAttribute("year", now.getYear());
        // 从缓存中移除token及对应的预览内容，确保预览页面只能展示一次
        CacheHolder.remove(token);
        return "article";
    }

    private List<String> separate(String content) {
    	List<String> list = new ArrayList<>();
        for(String str : content.split("\r\n")) {
        	list.addAll(subSeparate(str));
		}
		return list;
    }

	private List<String> subSeparate(String str) {
    	// 处理图片
		int start = str.indexOf("[image]"), end = str.indexOf("[/image]");
		if (start == -1 || end == -1) {
			return CommonUtil.asList(str);
		}
		List<String> list = new ArrayList<>();
		if (start > 0) {
			list.add(str.substring(0, start));
		}
		String fileId = str.substring(start + 7, end);
        FileInfo file = GUID.isGUID(fileId) ? fileInfoService.get(fileId) : null;
        if (file == null) {
            list.add(fileId); // 如果查不到图片，则直接显示文本内容
        } else {
            list.add("<img width=\"100%\" src=\"" + FileHolder.getUrl(file) + "\" />");
        }
		if (end + 8 < str.length()) {
			list.add(str.substring(end + 8));
		}
		return list;
	}

    @RequestMapping("/article/test")
    public String article() {
        return "article0";
    }

    @RequestMapping("/article/listPopular")
	@ResponseBody
    public Result listPopular() {
		return Result.success(articleService.listPopular());
    }
}
