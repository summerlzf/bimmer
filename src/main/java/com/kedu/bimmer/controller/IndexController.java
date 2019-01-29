package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuzifeng on 2018/9/17.
 */
@Controller
public class IndexController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		UserBasicInfo user = SystemContext.getUser();
		if(user != null) {
			model.addAttribute("username", user.getUserName());
		}
		return "index";
	}
}
