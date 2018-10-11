package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.UserBasicInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jef
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping({"/", "/main"})
    public String main(Model model) {
        UserBasicInfo user = SystemContext.getUser();
        if(user != null) {
            model.addAttribute("username", user.getUserName());
        }
        return "admin/main";
    }

    @RequestMapping("/articleList")
    public String articleList() {
        return "admin/articleList";
    }
}
