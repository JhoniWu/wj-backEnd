package com.evan.wj.LoginInterceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
/*
	@Override
	public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object o) throws Exception{
		HttpSession session = httpServletRequest.getSession();
		String contextPath = session.getServletContext().getContextPath();
		String[] requireAuthPages = new String[]{
			"index",
		};

		String uri = httpServletRequest.getRequestURI();

		uri = StringUtils.remove(uri,contextPath+"/");
		String page = uri;

		if(begingWith(page,requireAuthPages)){
			User user = (User) session.getAttribute("user");
			if(user == null){
				httpServletResponse.sendRedirect("login");
				return false;
			}
		}

		return true;
	}
*/
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse , Object o) throws Exception {
		if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())){
			httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
			return true;
		}
		Subject subject = SecurityUtils.getSubject();
		//使用Shiro验证

		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			return false;
		}

		System.out.println(subject.isRemembered());
		System.out.println(subject.isAuthenticated());


		return true;
	}


	private boolean begingWith(String page, String[] requireAuthPages) {
		boolean result = false;
		for(String requiredAuthPage : requireAuthPages) {
			if(StringUtils.startsWith(page,requiredAuthPage)){
				result = true;
				break;
			}
		}

		return result;
	}
}
