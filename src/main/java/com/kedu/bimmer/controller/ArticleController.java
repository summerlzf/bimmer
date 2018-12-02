package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.model.UserInfo;
import com.kedu.bimmer.service.ArticleService;
import com.kedu.bimmer.service.UserInfoService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

/**
 * @author Jef
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/article/item/{id}")
    public String article(Model model, @PathVariable("id") String id) {
        Article vo = GUID.isGUID(id) ? articleService.get(id) : null;
        if (vo == null || vo.isHidden()) { // 隐藏的文章不显示
            return "redirect:/";
        }
        UserInfo user = userInfoService.get(vo.getAuthorUserId());
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(vo.getTitle());
        dto.setSubTitle(vo.getSubTitle());
        dto.setAuthorUserName(user == null ? "--" : CommonUtil.isBlank(user.getNickName()) ? user.getUserName() : user.getNickName()); // 优先获取昵称，其次获取用户名
        dto.setCreateTimeStr(vo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("vo", dto);
        return "article";
    }
}
