package com.evan.wj.controller;


import com.evan.wj.Result.Result;
import com.evan.wj.Result.ResultFactory;
import com.evan.wj.pojo.User;
import com.evan.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

	@Autowired
	UserService userService;


	@CrossOrigin
	@PostMapping(value = "api/login")
	@ResponseBody
	public Result login(@RequestBody User requestUser) {
		String username = requestUser.getUsername();
		username = HtmlUtils.htmlEscape(username);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
		usernamePasswordToken.setRememberMe(true);

		try{
			subject.login(usernamePasswordToken);
			return ResultFactory.buildSuccessResult(username);
		} catch (AuthenticationException e) {
			String message = "账号密码错误";
			return ResultFactory.buildFailResult(message);
		}


	}

	@CrossOrigin
	@PostMapping(value = "api/register")
	@ResponseBody
	public Result register(@RequestBody User user) {
		String username = user.getUsername();

		String password = user.getPassword();
		username = HtmlUtils.htmlEscape(username);

		boolean exist = userService.isExist(username);
		if (exist) {
			String message = "用户名已被使用";
			return ResultFactory.buildFailResult(message);
		}

		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 2;
		String encodedPassword = new SimpleHash("md5",password,salt,times).toString();

		user.setSalt(salt);
		user.setUsername(username);
		user.setPassword(encodedPassword);
		userService.add(user);

		return ResultFactory.buildSuccessResult(user);
	}

	@CrossOrigin
	@GetMapping("api/logout")
	@ResponseBody
	public Result logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		String message = "成功登出";
		return ResultFactory.buildSuccessResult(message);
	}

	@CrossOrigin
	@ResponseBody
	@GetMapping(value = "api/authentication")
	public String authentication(){
		return "身份认证成功";
	}

}