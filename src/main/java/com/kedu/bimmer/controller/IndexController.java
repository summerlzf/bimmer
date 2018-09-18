package com.kedu.bimmer.controller;

import com.kedu.bimmer.service.UserService;
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
	private UserService userService;

	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("users", userService.query());
		model.addAttribute("time", time);
		logger.info("the time now is: " + time);
		return "index";
	}
}
