package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author Jef
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/main")
    public String main(Model model) {
//        UserBasicInfo user = SystemContext.getUser();
//        model.addAttribute("username", user.getUserName());
        return "admin/main";
    }

    @RequestMapping("/articleList")
    public String articleList(Model model) {
        return "admin/articleList";
    }

    @PostMapping("/getArticleList")
    @ResponseBody
    public Result getArticleList(ArticleSearchDTO articleSearchDTO, int pageNum) {
        Page<ArticleDTO> page = articleService.query(articleSearchDTO, pageNum);
        return Result.success(page);
    }

    @RequestMapping("/articleEdit")
    public String articleEdit(Model model, String id) {
        Article vo = GUID.isGUID(id) ? articleService.get(id) : null;
        model.addAttribute("title", vo == null ? "" : vo.getTitle());
        model.addAttribute("content", vo == null ? "" : vo.getContent());
        return "admin/articleEdit";
    }

    @PostMapping("/saveArticle")
    @ResponseBody
    public Result saveArticle(Article vo) {
        if (CommonUtil.isBlank(vo.getTitle()) || CommonUtil.isBlank(vo.getContent())) {
            return Result.fail("参数有误");
        }
        LocalDateTime now = LocalDateTime.now();
        UserBasicInfo user = SystemContext.getUser();
        vo.setArticleId(GUID.generate());
        vo.setAuthorUserId(user.getUserId());
        vo.setCreateTime(now);
        vo.setLastModifyTime(now);
        articleService.insert(vo);
        return Result.success();
    }
}
