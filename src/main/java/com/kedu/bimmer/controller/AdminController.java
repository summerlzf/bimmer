package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.*;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.model.Comment;
import com.kedu.bimmer.model.FileInfo;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.service.CommentService;
import com.kedu.bimmer.service.FileInfoService;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping("/main")
    public String main(Model model) {
//        UserBasicInfo user = SystemContext.getUser();
//        model.addAttribute("username", user.getUserName());
        return "admin/main";
    }

    @RequestMapping("/articleList")
    public String articleList() {
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
        model.addAttribute("edit", vo != null);
        model.addAttribute("id", vo == null ? "" : vo.getArticleId());
        model.addAttribute("title", vo == null ? "" : vo.getTitle());
        model.addAttribute("subTitle", vo == null ? "" : vo.getSubTitle());
        model.addAttribute("content", vo == null ? "" : vo.getContent());
        model.addAttribute("allowComment", vo == null || vo.isAllowComment()); // 默认：允许评论
        model.addAttribute("hidden", vo != null && vo.isHidden()); // 默认：不隐藏
        return "admin/articleEdit";
    }

    @PostMapping("/saveArticle")
    @ResponseBody
    public Result saveArticle(Article vo) {
        if (CommonUtil.isBlank(vo.getTitle()) || CommonUtil.isBlank(vo.getContent())) {
            return Result.fail("文章标题/内容不能为空");
        }
        LocalDateTime now = LocalDateTime.now();
        UserBasicInfo user = SystemContext.getUser();
		Article ac = GUID.isGUID(vo.getArticleId()) ? articleService.get(vo.getArticleId()) : null;
		if (ac == null) { // 新增
			vo.setArticleId(GUID.generate());
			vo.setAuthorUserId(user.getUserId());
			vo.setViewCount(0);
			vo.setAllowComment(true); // 默认：允许评论
			vo.setHidden(false); // 默认：不隐藏
			vo.setCreateTime(now);
			vo.setLastModifyTime(now);
			articleService.insert(vo);
		} else { // 更新
			ac.setTitle(vo.getTitle());
			ac.setSubTitle(vo.getSubTitle());
			ac.setContent(vo.getContent());
			ac.setAllowComment(vo.isAllowComment());
			ac.setHidden(vo.isHidden());
			ac.setLastModifyTime(now);
			articleService.update(ac);
		}
        return Result.success();
    }

    @RequestMapping("/commentList")
    public String commentList() {
        return "admin/commentList";
    }

    @PostMapping("/getCommentList")
    @ResponseBody
    public Result getCommentList(CommentSearchDTO commentSearchDTO, int pageNum) {
        Page<CommentDTO> page = commentService.query(commentSearchDTO, pageNum);
        return Result.success(page);
    }

    @PostMapping("/updateCommentHidden")
    @ResponseBody
    public Result updateCommentHidden(Comment vo) {
        if (!GUID.isGUID(vo.getCommentId())) {
            return Result.fail("参数有误");
        }
        commentService.updateHidden(vo);
        return Result.success();
    }

	@RequestMapping("/fileInfoList")
	public String fileInfoList() {
		return "admin/fileInfoList";
	}

	@PostMapping("/getFileInfoList")
	@ResponseBody
	public Result getFileInfoList(FileSearchDTO fileSearchDTO, int pageNum) {
		Page<FileInfoDTO> page = fileInfoService.query(fileSearchDTO, pageNum);
		return Result.success(page);
	}

	@RequestMapping("/fileInfoEdit")
	public String fileInfoEdit(Model model, String id) {
    	FileInfo vo = GUID.isGUID(id) ? fileInfoService.get(id) : null;
		model.addAttribute("edit", vo != null);
		model.addAttribute("id", vo == null ? "" : vo.getFileId());
		model.addAttribute("realName", vo == null ? "" : vo.getRealName());
		model.addAttribute("fileName", vo == null ? "" : vo.getFileName());
		model.addAttribute("fileType", vo == null ? "" : vo.getFileType());
		model.addAttribute("hidden", vo != null && vo.isHidden()); // 默认：不隐藏
		return "admin/fileInfoEdit";
	}

	@PostMapping("/saveFileInfo")
	@ResponseBody
	public Result saveFileInfo(FileInfo vo) {
		if (CommonUtil.isBlank(vo.getRealName()) || CommonUtil.isBlank(vo.getFileName())) {
			return Result.fail("文件名不能为空");
		}
		if (vo.getFileType() == 0) {
			return Result.fail("文件类型不能为空");
		}
		FileInfo f = GUID.isGUID(vo.getFileId()) ? fileInfoService.get(vo.getFileId()) : null;
		if (f == null) { // 新增
			vo.setFileId(GUID.generate());
			vo.setHidden(false); // 默认：不隐藏
			vo.setCreateTime(LocalDateTime.now());
			fileInfoService.insert(vo);
		} else { // 更新
			f.setRealName(vo.getRealName());
			f.setFileName(vo.getFileName());
			f.setFileType(vo.getFileType());
			f.setHidden(vo.isHidden());
			fileInfoService.update(f);
		}
		return Result.success();
	}
}
