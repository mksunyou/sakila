package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Category;
@Mapper
public interface CategoryMapper {
	List<Category> selectCategoryNameList();
	List<Map<String, Object>> selectSalesByCategory();
}
