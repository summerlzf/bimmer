package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jef
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @RequestMapping("/signin")
    @ResponseBody
    public Result signin(String username, String password) {
        System.err.println("================>   username: " + username + ", password: " + password);
        return Result.success();
    }
}
