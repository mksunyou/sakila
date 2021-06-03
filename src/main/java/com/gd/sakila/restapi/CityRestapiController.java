package com.gd.sakila.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.service.CityService;
import com.gd.sakila.vo.City;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CityRestapiController {
	@Autowired CityService cityService;
	
	@GetMapping("/addCity")
	public void addcity(City city) {
		log.debug("city: "+city);
	}
	
	@GetMapping("/getCityList")
	public List<City> getCityList(@RequestParam(value="countryId", required = false) int countryId) {
		log.debug("countryId: "+countryId);
		return this.cityService.getCityList(countryId);
		
	}
	
}
