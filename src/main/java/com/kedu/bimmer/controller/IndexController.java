package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.constant.ArticlePosition;
import com.kedu.bimmer.dto.ArticleIndexDTO;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.model.ArticleExtend;
import com.kedu.bimmer.service.ArticleExtendService;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by liuzifeng on 2018/9/17.
 */
@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleExtendService articleExtendService;
	@Autowired
	private ExecutorService executorService;

	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		UserBasicInfo user = SystemContext.getUser();
		if(user != null) {
			model.addAttribute("username", user.getUserName());
		}
		// 首页中间导航
		Future<List<ArticleIndexDTO>> navTask = executorService.submit(() -> {
			List<ArticleExtend> exts = articleExtendService.listByPosition(ArticlePosition.INDEX_MIDDLE_NAV);
			if (exts.size() > 3) {
				// 最多显示3个
				exts = exts.subList(0, 3);
			}
			List<ArticleIndexDTO> list = new ArrayList<>();
			for (ArticleExtend ext : exts) {
				Article a = articleService.get(ext.getArticleId());
				if (a == null) {
					continue;
				}
				ArticleIndexDTO vo = new ArticleIndexDTO();
				vo.setArticleId(a.getArticleId());
				vo.setTitle(CommonUtil.substr(a.getTitle(), 20, "..."));
				vo.setSubTitle(a.getSubTitle());
				vo.setContent(CommonUtil.substr(a.getContent(), 100, " ..."));
				vo.setLinkUrl(ext.getLinkUrl());
				vo.setImageUrl(ext.getImageUrl());
				list.add(vo);
			}
			return list;
		});
		// 首页中间内容
		Future<List<ArticleIndexDTO>> contentTask = executorService.submit(() -> {
			List<ArticleExtend> exts = articleExtendService.listByPosition(ArticlePosition.INDEX_MIDDLE_CONTENT);
			if (exts.size() > 4) {
				// 最多显示4个
				exts = exts.subList(0, 4);
			}
			List<ArticleIndexDTO> list = new ArrayList<>();
			for (ArticleExtend ext : exts) {
				Article a = articleService.get(ext.getArticleId());
				if (a == null) {
					continue;
				}
				ArticleIndexDTO vo = new ArticleIndexDTO();
				vo.setArticleId(a.getArticleId());
				vo.setTitle(a.getTitle());
				vo.setSubTitle(a.getSubTitle());
				vo.setContent(CommonUtil.substr(a.getContent(), 100, " ..."));
				vo.setLinkUrl(ext.getLinkUrl());
				vo.setImageUrl(ext.getImageUrl());
				list.add(vo);
			}
			return list;
		});

		try {
			model.addAttribute("middleNavArticles", navTask.get());
			model.addAttribute("middleContentArticles", contentTask.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/robots.txt")
	public String robots() {
		// robots协议（爬虫蜘蛛），不过这个“文件”页面的访问，建议在nginx上做
		return "robots";
	}
}
