package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.mapper.LanguageMapper;
import com.gd.sakila.vo.Category;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmForm;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class FilmService {
	@Autowired FilmMapper filmMapper;
	@Autowired CategoryMapper categoryMapper;
	@Autowired LanguageMapper languageMapper;
	
	/*
	 * param : film 입력폼을 통해 받아 오는 값
	 * return : 입력된 filmId값을 리턴
	 */
	
	// 대출 영화 정보
	public List<Map<String, Object>> getFilmListByRental() {
		
		return filmMapper.selectFilmListByRental();
	}
	
	// 영화정보 수정
	public int modifyFilm(FilmForm filmForm) {
		Film film = filmForm.getFilm();
		filmMapper.updateFilm(film);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filmId", film.getFilmId());
		map.put("categoryId", filmForm.getCategory().getCategoryId());
		map.put("specialFeatures", filmForm.getSpecialFeatures());
		log.debug("map: "+map);
		filmMapper.updateFilmCategory(map);
		return film.getFilmId();
	}
	
	// 재고추가시 영화 리스트
	public List<Film> getFilmListByInventory() {
		return filmMapper.selectFilmListByInventory();
	}
	
	// CategoryService 로 이동해야 함.
	public List<Category> getCategoryList() {
		return categoryMapper.selectCategoryNameList();
	}
	
	// 영화추가 
	public int addFilm(FilmForm filmForm) {
		Film film = filmForm.getFilm();
		filmMapper.insertFilm(film); // film ID가 생성된 후 film.setFilmId(생성된 filmId값)가 호출.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", filmForm.getCategory().getCategoryId());
		map.put("filmId", film.getFilmId());
		map.put("specialFeatures", filmForm.getSpecialFeatures());
		log.debug("addFilm map: "+map);
		filmMapper.insertFilmCategory(map);
		return film.getFilmId(); // controller에서 filmId 값을 바로 가져오기 위해.
	}
	
	// 영화배우 수정
	public int modifyFilmActor(List<Integer> actorId, int filmId) {
		// 1) 기존 배우 삭제
		int actorRow = filmMapper.deleteActorListByFilm(filmId);
		log.debug("delete actorRow: "+actorRow);
		
		// 2) 새 배우 추가
		if(actorId==null) {
			return 0;
		}
		Map<String, Object> map = new HashMap<>();		
		map.put("actorId", actorId);
		map.put("filmId",filmId);
		
		int insertRow = filmMapper.insertActorListByFilm(map);
		
		log.debug("insert actorRow:" +map);
		
		return insertRow;
	}
	// 영화별 배우 추가 리스트
	public List<Map<String, Object>> getActorListByFilm(int filmId) {
		
		return filmMapper.selectActorListByFilm(filmId);
	}
	// map <-- film, filmcount
	// 상세보기
	public Map<String, Object> getFilmOne(int filmId) {
		// 1) 상세보기
		Map<String, Object> filmMap = filmMapper.selectFilmOne(filmId);
		log.debug("filmMap: "+filmMap);
		
		// 2) 매장별 재고량 파악
		
		// 2-1) 지점 1의 재고량
		int store1FilmCount = 0;
		Map<String, Object> store1Map = new HashMap<String, Object>(); // method 호출
		store1Map.put("filmId", filmId); //in
		store1Map.put("storeId", 1); // in		
		store1Map.put("filmCount", store1FilmCount); // out
		List<Integer> store1Stock = filmMapper.selectFilmInStock(store1Map);
		filmMapper.selectFilmInStock(store1Map);
		log.debug("paramMap: "+store1Map);
		log.debug("filmCount: "+store1Map.get("filmCount"));
		log.debug("store1Stock: "+store1Stock);
		
		// 2-1) 지점 1의 재고량
		int store2FilmCount = 0;
		Map<String, Object> store2Map = new HashMap<String, Object>(); // method 호출
		store2Map.put("filmId", filmId); //in
		store2Map.put("storeId", 2); // in		
		store2Map.put("filmCount", store2FilmCount); // out
		List<Integer> store2Stock = filmMapper.selectFilmInStock(store2Map);
		filmMapper.selectFilmInStock(store2Map);
		log.debug("paramMap: "+store2Map);
		log.debug("filmCount: "+store2Map.get("filmCount"));
		log.debug("store2Stock: "+store2Stock);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("store1Stock", store1Map.get("filmCount"));
		returnMap.put("store2Stock", store2Map.get("filmCount"));
		returnMap.put("filmMap", filmMap);
		
		return returnMap;		
	}
	
	
	//FilmViewList
	public Map<String, Object> getFilmList(int currentPage, int rowPerPage, String title, String categoryName, Double price, String rating, String actors) {
		// 1. film 개수
		Map<String, Object> totalMap = new HashMap<String, Object>();
		totalMap.put("title", title);
		totalMap.put("categoryName", categoryName);
		totalMap.put("price", price);
		totalMap.put("rating", rating);
		totalMap.put("actors", actors);
		
		int filmTotal = filmMapper.selectTotal(totalMap);
		
		int lastPage = (int)Math.ceil((double)filmTotal / rowPerPage); // Math.ceil은 올림함수
		log.debug("filmTotal: "+filmTotal);
		log.debug("lastPage: "+lastPage);
		
		// 2. 페이징
		Page page = new Page();
		int beginRow = (currentPage-1)*rowPerPage;
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		log.debug("page: "+page);
		
		// 3. List
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("paramMap", paramMap);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("categoryName", categoryName);
		paramMap.put("title", title);
		paramMap.put("price", price);
		paramMap.put("rating", rating);
		paramMap.put("actors", actors);
		log.debug("paramMap: "+paramMap);
		
		List<Map<String, Object>> filmList = filmMapper.selectFilmList(paramMap);
		
		// 4. categoryList
		List<Category> categoryNameList = categoryMapper.selectCategoryNameList();
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("filmList",filmList);
		returnMap.put("categoryNameList", categoryNameList);
		returnMap.put("lastPage",lastPage);
		log.debug("returnMap: "+returnMap);
		
		return returnMap;
	}

}
