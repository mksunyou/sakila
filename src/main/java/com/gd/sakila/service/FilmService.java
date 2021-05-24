package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class FilmService {
	@Autowired FilmMapper filmMapper;
	
	// map <-- film, filmcount
	// 상세보기
	public Map<String, Object> getFilmOne(int filmId, int storeId) {
		// 1) 상세보기
		Map<String, Object> filmMap = filmMapper.selectFilmOne(filmId);
		log.debug("filmMap: "+filmMap);
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); // method 호출
		paramMap.put("filmId", filmId); //in
		paramMap.put("storeId", storeId); // in
		int filmCount = 0;
		paramMap.put("filmCount", filmCount); // out
		List<Integer> filmList = filmMapper.selectFilmInStock(paramMap);
		filmMapper.selectFilmInStock(paramMap);
		log.debug("paramMap: "+paramMap);
		log.debug("filmCount: "+paramMap.get("filmCount"));
		log.debug("filmList: "+filmList);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("paramMap", paramMap);
		returnMap.put("filmCount", filmCount);
		returnMap.put("filmList", filmList);
		returnMap.put("filmMap", filmMap);
		
		return returnMap;		
	}
	
	//FilmViewList
	public Map<String, Object> getFilmList(int currentPage, int rowPerPage, String searchWord) {
		// 1. film 개수
		int filmTotal = filmMapper.selectTotal(searchWord);
		int lastPage = (int)Math.ceil((double)filmTotal / rowPerPage); // Math.ceil은 올림함수
		log.debug("filmTotal: "+filmTotal);
		
		// 2. 페이지 설정
		Page page = new Page();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		log.debug("page: "+page);
		
		// 3. List
		List<Film> filmList = filmMapper.selectFilmList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastPage", lastPage);
		map.put("filmList", filmList);
		log.debug("FilmList map: "+map);
		
		return map;
	}
}
