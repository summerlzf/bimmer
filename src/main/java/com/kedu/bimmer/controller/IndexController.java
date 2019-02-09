package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.constant.ArticlePosition;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.model.ArticleExtend;
import com.kedu.bimmer.service.ArticleExtendService;
import com.kedu.bimmer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzifeng on 2018/9/17.
 */
@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleExtendService articleExtendService;

	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		UserBasicInfo user = SystemContext.getUser();
		if(user != null) {
			model.addAttribute("username", user.getUserName());
		}
		List<ArticleExtend> exts = articleExtendService.listByPosition(ArticlePosition.INDEX_MIDDLE_NAV);
		if (exts.size() > 3) {
			// 最多显示3个
			exts = exts.subList(0, 3);
		}
		List<Article> articles = new ArrayList<>();
		for(ArticleExtend ext : exts) {
			Article vo = articleService.get(ext.getArticleId());
			if (vo != null) {
				articles.add(vo);
			}
		}
		model.addAttribute("middleNavArticles", articles);
		return "index";
	}
}
