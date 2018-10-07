package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.CookieHolder;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.model.UserInfo;
import com.kedu.bimmer.service.UserInfoService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/signin")
    @ResponseBody
    public Result signin(HttpServletResponse response, String username, String password, boolean remember) {
        if (CommonUtil.isBlank(username) || !CommonUtil.isLegalPassword(password)) {
            return Result.fail("请输入正确的用户名/密码");
        }
        UserInfo vo = userInfoService.getByUserName(username);
        if (vo == null) {
            return Result.fail("用户名/密码错误");
        }
        String pwd = CommonUtil.hash(password);
        if (pwd == null || !pwd.equals(vo.getPassword())) {
            return Result.fail("用户名/密码错误");
        }
        // 更新登录时间
        userInfoService.updateLoginTime(vo.getUserId());

        UserBasicInfo user = new UserBasicInfo();
        user.setUserId(vo.getUserId());
        user.setUserName(vo.getUserName());
        user.setNickName(vo.getNickName());
        user.setPhone(vo.getPhone());
        user.setEmail(vo.getEmail());
        CookieHolder.setUser(response, user);
        return Result.success();
    }
}
