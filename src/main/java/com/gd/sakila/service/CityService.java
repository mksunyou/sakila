package com.gd.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.CityMapper;
import com.gd.sakila.vo.City;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CityService {
	@Autowired CityMapper cityMapper;
	public List<City> getCityList(int countryId) {
		return cityMapper.selectCity(countryId);
	}
	public int addCity(City city) {
		log.debug("city :"+city);
		
		int row = cityMapper.insertCity(city);
		log.debug("add row: "+row);
		
		return row;
	}
}
