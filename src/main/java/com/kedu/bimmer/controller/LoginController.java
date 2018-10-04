package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.model.UserInfo;
import com.kedu.bimmer.service.UserInfoService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jef
 */
@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @RequestMapping("/signin")
    @ResponseBody
    public Result signin(String username, String password) {
        System.err.println("================>   username: " + username + ", password: " + password);
        if (CommonUtil.isBlank(username) || !CommonUtil.isLegalPassword(password)) {
            return Result.fail("用户名/密码错误");
        }
        UserInfo vo = userInfoService.getByUserName(username);
        if (vo == null) {
            return Result.fail("用户不存在");
        }
        String pwd = CommonUtil.hash(password);
        if (pwd == null || !pwd.equals(vo.getPassword())) {
            return Result.fail("密码错误");
        }
        return Result.success();
    }
}
