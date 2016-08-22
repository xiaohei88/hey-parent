package org.heyframework.web.sys.sys.controller;

import java.util.Map;

import org.heyframework.api.sys.auth.bean.User;
import org.heyframework.api.sys.auth.service.UserService;
import org.heyframework.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/get")
	@ResponseBody
	public Map<String, Object> get() {
		User user = userService.get();
		System.out.println(user.getName() + "*****" + user.getPasswd());
		return SUCCESS_MAP;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add() {
		userService.add();
		System.out.println("成功");
		return SUCCESS_MAP;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update() {
		userService.update();
		System.out.println("成功");
		return SUCCESS_MAP;
	}
}
