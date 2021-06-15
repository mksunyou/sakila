package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Rental;

@Mapper
public interface RentalMapper {
	Map<String, Object> insertRental(Rental rental);
	List<Map<String, Object>> selectNotReturnList(Map<String, Object> map);
}
