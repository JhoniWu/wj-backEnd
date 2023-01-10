package com.evan.wj.controller;


import com.evan.wj.Result.Result;
import com.evan.wj.pojo.User;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

	@Autowired
	UserService userService;


	@CrossOrigin
	@PostMapping(value = "api/login")
	@ResponseBody
	public Result login(@RequestBody User requestUser, HttpSession session) {
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);

		User user = userService.get(username, requestUser.getPassword());
		if (null == user) {
			System.out.println("账户错误");
			return new Result(400);
		} else {
			session.setAttribute("user",user);
			return new Result(200);
		}
	}
}