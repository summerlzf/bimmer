package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Jef
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping({"/", "/main"})
    public String main(Model model) {
        UserBasicInfo user = SystemContext.getUser();
        model.addAttribute("username", user.getUserName());
        return "admin/main";
    }

    @RequestMapping("/articleList")
    public String articleList(Model model) {
        UserBasicInfo user = SystemContext.getUser();
        model.addAttribute("username", user.getUserName());
        return "admin/articleList";
    }

    @PostMapping("/getArticleList")
    @ResponseBody
    public Result getArticleList(ArticleSearchDTO articleSearchDTO) {
        List<Article> list = articleService.query(articleSearchDTO);
        return Result.success(list);
    }
}