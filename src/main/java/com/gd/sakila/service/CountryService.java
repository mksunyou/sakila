package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CountryMapper;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.Page;

@Service
@Transactional
public class CountryService {
	@Autowired
	private CountryMapper countryMapper;
	public Map<String, Object> getCountryList(int currentPage, int rowPerPage) {
		//beginRow
		int beginRow = (currentPage-1) * rowPerPage;
		
		//mybatis는 매개변수 하나만.
		// 1. 컨트롤러에서 보내어준 매개값을 dao한테 바로 보내줄수 없어서 가공...
		Page pageParam = new Page();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		
		// 2. dao 호출
		List<Country> list = countryMapper.selectCountryList(pageParam);
		int total = countryMapper.selectCountryTotal();
		
		// 3. dao의 반환값을 가공
		int lastPage = total / rowPerPage;
		if(total % rowPerPage != 0) {
			lastPage += 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("lastPage", lastPage);
		
		return map;
	}
}
