package com.demo.service;

import com.demo.model.Category;
import org.springframework.data.repository.query.Param;

public interface ICategoryService extends GenerateService<Category>{
    void  deleteByIdCategory(@Param("id") Long id);
}
