package com.shida.cn.dao;

import com.shida.cn.dao.model.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}