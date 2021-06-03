package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.City;

@Mapper
public interface CityMapper {
	int insertCity (City city);
	List<City> selectCity(int countryId);
}
