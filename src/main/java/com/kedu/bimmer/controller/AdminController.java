package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.*;
import com.kedu.bimmer.cache.CacheHolder;
import com.kedu.bimmer.constant.ArticlePosition;
import com.kedu.bimmer.constant.FileType;
import com.kedu.bimmer.dto.*;
import com.kedu.bimmer.model.*;
import com.kedu.bimmer.service.*;
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
    private ArticleExtendService articleExtendService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private FileTagService fileTagService;

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
        model.addAttribute("originalUrl", vo == null ? "" : vo.getOriginalUrl());
        model.addAttribute("allowComment", vo == null || vo.isAllowComment()); // 默认：允许评论
        model.addAttribute("hidden", vo != null && vo.isHidden()); // 默认：不隐藏
		// 文章扩展表信息
		ArticleExtend ext = GUID.isGUID(id) ? articleExtendService.get(id) : null;
		model.addAttribute("linkUrl", ext == null ? "" : ext.getLinkUrl());
		model.addAttribute("imageUrl", ext == null ? "" : ext.getImageUrl());
		model.addAttribute("position", ext == null ? "" : ext.getPosition());
		model.addAttribute("sortOrder", ext == null ? 0 : ext.getSortOrder());
		model.addAttribute("allPosition", ArticlePosition.asList()); // 文章所有位置信息
		return "admin/articleEdit";
    }

    @PostMapping("/saveArticle")
    @ResponseBody
    public Result saveArticle(Article vo, ArticleExtend ext) {
		String err = verify(vo);
		if (err != null) {
			return Result.fail(err);
		}
		LocalDateTime now = LocalDateTime.now();
        UserBasicInfo user = SystemContext.getUser();
        boolean extEmpty = CommonUtil.isBlank(ext.getLinkUrl()) && CommonUtil.isBlank(ext.getImageUrl()) && CommonUtil.isBlank(ext.getPosition());
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
			if (!extEmpty) {
				if (!ArticlePosition.exist(ext.getPosition())) {
					return Result.fail("文章所在位置参数错误");
				}
				ext.setArticleId(vo.getArticleId());
				articleExtendService.insert(ext);
			}
		} else { // 更新
			ac.setTitle(vo.getTitle());
			ac.setSubTitle(vo.getSubTitle());
			ac.setContent(vo.getContent());
			ac.setOriginalUrl(vo.getOriginalUrl());
			ac.setAllowComment(vo.isAllowComment());
			ac.setHidden(vo.isHidden());
			ac.setLastModifyTime(now);
			articleService.update(ac);
			if (extEmpty) {
				articleExtendService.delete(ac.getArticleId());
			} else {
				if (!ArticlePosition.exist(ext.getPosition())) {
					return Result.fail("文章所在位置参数错误");
				}
				articleExtendService.save(ext);
			}
		}
        return Result.success();
    }

	@PostMapping("/previewArticle")
	@ResponseBody
	public Result previewArticle(Article vo) {
    	String err = verify(vo);
		if (err != null) {
			return Result.fail(err);
		}
    	String token = GUID.generate();
    	// 将生成的token和预览的内容写入缓存
		CacheHolder.put(token, vo);
		return Result.success(token);
	}

	/**
	 * 校验文章参数
	 * @param vo
	 * @return
	 */
	private String verify(Article vo) {
		if (CommonUtil.isBlank(vo.getTitle()) || CommonUtil.isBlank(vo.getContent())) {
			return "文章标题/内容不能为空";
		}
		String url = vo.getOriginalUrl();
		if (!CommonUtil.isBlank(url)) {
			if (url.length() > 290) {
				return "文章来源URL地址太长";
			}
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				return "文章来源不是正确的URL地址";
			}
		}
		return null;
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

	@PostMapping("/listFileInfoByTag")
	@ResponseBody
	public Result listFileInfoByTag(String tagId) {
		return Result.success(fileInfoService.listByTagId(tagId));
	}

	@RequestMapping("/fileInfoEdit")
	public String fileInfoEdit(Model model, String id) {
    	FileInfo vo = GUID.isGUID(id) ? fileInfoService.get(id) : null;
		model.addAttribute("edit", vo != null);
		model.addAttribute("id", vo == null ? "" : vo.getFileId());
		model.addAttribute("realName", vo == null ? "" : vo.getRealName());
		model.addAttribute("fileName", vo == null ? "" : vo.getFileName());
		model.addAttribute("fileType", vo == null ? 1 : vo.getFileType());
		model.addAttribute("hidden", vo != null && vo.isHidden()); // 默认：不隐藏
        // 文件路径，对于新增的文件，默认类型为：图片
		model.addAttribute("filePath", FileHolder.getFilePath(vo == null ? FileType.IMAGE : FileType.of(vo.getFileType())));
		// 文件标签列表（所有标签）
		model.addAttribute("tags", fileTagService.listTags(id));
		return "admin/fileInfoEdit";
	}

	@PostMapping("/saveFileInfo")
	@ResponseBody
	public Result saveFileInfo(FileInfo vo, String tagIds) {
		if (CommonUtil.isBlank(vo.getRealName()) || CommonUtil.isBlank(vo.getFileName())) {
			return Result.fail("文件名不能为空");
		}
		if (vo.getFileType() == 0) {
			return Result.fail("文件类型不能为空");
		}
		String[] tids = CommonUtil.isBlank(tagIds) ? new String[]{} : tagIds.split(",");
		if (tids.length > 3) {
			return Result.fail("文件标签最多选择3个");
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
		// 批量保存文件信息-标签关系表
		fileTagService.saveFileTags(vo.getFileId(), tids);
		return Result.success();
	}

	@RequestMapping("/fileTagList")
	public String fileTagList() {
		return "admin/fileTagList";
	}

	@PostMapping("/getFileTagList")
	@ResponseBody
	public Result getFileTagList(FileTag fileTag, int pageNum) {
		Page<FileTag> page = fileTagService.query(fileTag, pageNum);
		return Result.success(page);
	}

	@PostMapping("/listAllFileTags")
	@ResponseBody
	public Result listAllFileTags() {
		return Result.success(fileTagService.listAll());
	}

	@PostMapping("/getFileTag")
	@ResponseBody
	public Result getFileTag(String tagId) {
		return Result.success(fileTagService.get(tagId));
	}

	@PostMapping("/saveFileTag")
	@ResponseBody
	public Result saveFileTag(FileTag vo) {
		if (CommonUtil.isBlank(vo.getTagName())) {
			return Result.fail("标签名称不能为空");
		}
		FileTag t = fileTagService.getByTagName(vo.getTagName());
		if (GUID.isGUID(vo.getTagId())) {
			if (t != null && !vo.getTagId().equals(t.getTagId())) {
				return Result.fail("标签名称已存在");
			}
			fileTagService.update(vo);
		} else {
			if (t != null) {
				return Result.fail("标签名称已存在");
			}
			vo.setTagId(GUID.generate());
			fileTagService.insert(vo);
		}
		return Result.success();
	}

	@PostMapping("/deleteFileTag")
	@ResponseBody
	public Result deleteFileTag(String tagId) {
		if (!GUID.isGUID(tagId)) {
			return Result.fail("参数错误");
		}
		if (fileTagService.listByTagId(tagId).size() > 0) {
			return Result.fail("存在与文件信息的关联");
		}
		fileTagService.delete(tagId);
		return Result.success();
	}
}
