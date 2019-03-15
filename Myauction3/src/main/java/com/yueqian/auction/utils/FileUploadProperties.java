package com.yueqian.auction.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
	private String staticAccessPath; //对外公开的访问路径
	
	private String uploadFileFolder; //文件放存的物理目录

	public String getStaticAccessPath() {
		return staticAccessPath;
	}

	public void setStaticAccessPath(String staticAccessPath) {
		this.staticAccessPath = staticAccessPath;
	}

	public String getUploadFileFolder() {
		return uploadFileFolder;
	}

	public void setUploadFileFolder(String uploadFileFolder) {
		this.uploadFileFolder = uploadFileFolder;
	}
	
}
