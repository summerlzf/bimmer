package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by liuzifeng on 2018/9/17.
 */
@Controller
public class IndexController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

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
