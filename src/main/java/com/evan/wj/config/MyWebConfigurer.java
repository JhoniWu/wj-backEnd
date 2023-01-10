package com.evan.wj.config;

import com.evan.wj.LoginInterceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
	@Bean
	public LoginInterceptor getLoginIntercepter(){
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
	}
}
