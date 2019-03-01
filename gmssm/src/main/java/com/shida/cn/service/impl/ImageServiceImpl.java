package com.shida.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.ImageMapper;
import com.shida.cn.dao.model.Image;
import com.shida.cn.service.IImageService;

@Service("imageService")
public class ImageServiceImpl implements IImageService {
	@Autowired
	private ImageMapper imageMapper;
	
	public int save(Image image) {
		return imageMapper.insertSelective(image);
	}
	
}
