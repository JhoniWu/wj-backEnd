package com.evan.wj.config;

import com.evan.wj.LoginInterceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
	@Bean
	public LoginInterceptor getLoginInterceptor(){
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(getLoginInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/index.html")
			.excludePathPatterns("/api/login")
			.excludePathPatterns("/api/logout");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/file/**").addResourceLocations("file:" +
			"E:/JAVA_BackEnd/White-Jotter/img_test/"
		);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
			.allowCredentials(true)
			.allowedOrigins("http://localhost:8080")
			.allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
			.allowedHeaders("*");
	}
}
