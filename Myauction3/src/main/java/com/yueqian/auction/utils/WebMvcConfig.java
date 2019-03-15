package com.yueqian.auction.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private FileUploadProperties fileUploadProperties;


	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/testprojectfile/");
	}*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(fileUploadProperties.getStaticAccessPath()).addResourceLocations("file:"+fileUploadProperties.getUploadFileFolder()+"/");
	}
	
}
