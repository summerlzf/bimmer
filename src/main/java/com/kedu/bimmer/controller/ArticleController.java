package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jef
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/article/item/{id}")
    public String article(Model model, @PathVariable("id") String id) {
        Article vo = GUID.isGUID(id) ? articleService.get(id) : null;
        if (vo == null) {
            return "redirect:/";
        }
        model.addAttribute("vo", vo);
        return "article";
    }
}
