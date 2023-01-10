package com.evan.wj.error;

import org.apache.http.HttpStatus;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;

import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage error404Page = new ErrorPage(org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_NOT_FOUND), "/index.html");
		registry.addErrorPages(error404Page);
	}
}
