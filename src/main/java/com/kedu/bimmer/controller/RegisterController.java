package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.model.UserInfo;
import com.kedu.bimmer.service.UserInfoService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author Jef
 */
@Controller
public class RegisterController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping("/signup")
    @ResponseBody
    public Result signup(String username, String password, String phone) {
        System.err.println("=======>   username: " + username + ", password: " + password + ", phone: " + phone);
        if (CommonUtil.isBlank(username)) {
            return Result.fail("用户名不能为空");
        }
        if (!CommonUtil.isMobilePhone(phone)) {
            return Result.fail("手机号码不是合法的");
        }
        if (!CommonUtil.isLegalPassword(password)) {
            return Result.fail("密码不是合法的");
        }
        if (userInfoService.getByUserName(username) != null) {
            return Result.fail("用户名已经存在");
        }
        if (userInfoService.getByPhone(phone) != null) {
            return Result.fail("手机号码已经存在");
        }
        LocalDateTime now = LocalDateTime.now();
        UserInfo vo = new UserInfo();
        vo.setUserId(GUID.generate());
        vo.setUserName(username);
        vo.setPassword(CommonUtil.hash(password)); // 保存哈希值
        vo.setPhone(phone);
        vo.setCreateTime(now);
        vo.setLastModifyTime(now);
        userInfoService.save(vo);
        return Result.success();
    }
}
