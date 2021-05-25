package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	@Autowired FilmService filmService;
	
	// 영화 상세보기
	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model,
								@RequestParam(value="filmId", required = true) int filmId) {
		Map<String, Object> map = filmService.getFilmOne(filmId);
		log.debug("getFilmOne map: "+map);
		model.addAttribute("store1Stock", map.get("store1Stock"));
		model.addAttribute("store2Stock", map.get("store2Stock"));		
		model.addAttribute("filmMap", map.get("filmMap"));
		return "getFilmOne";
	}
	
	// 영화 리스트
	@GetMapping("/getFilmList")
	public String getFilmList(Model model,
								@RequestParam(name="categoryName", required = false) String categoryName,
								@RequestParam(name="price", required = false) Double price, // 기본타입은 null값을 가질 수 없기 때문에 Double로,
								@RequestParam(value="currentPage", defaultValue="1") int currentPage,
								@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
								@RequestParam(value="title", required = false) String title,
								@RequestParam(value="rating", required = false) String rating,
								@RequestParam(value="actors", required = false) String actors) {
		log.debug("categoryName: "+categoryName);
		if(categoryName != null && categoryName.equals("")) {
			categoryName=null;
		}
		log.debug("price: "+price);
		if(price != null && price==0) {
			price=null;
		}
		log.debug("title: "+title);
		if(title != null && title.equals("")) {
			title=null;
		}
		log.debug("rating: "+rating);
		if(rating != null && rating.equals("")) {
			rating=null;
		}
		
		Map<String, Object> map = filmService.getFilmList(currentPage, rowPerPage, title, categoryName, price, rating, actors);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("title", title);
		model.addAttribute("rating", rating);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("filmList", map.get("filmList"));		
		model.addAttribute("categoryNameList",map.get("categoryNameList"));
		model.addAttribute("price", price);
		model.addAttribute("actors", actors);
		
		log.debug("map: "+map);
		
		return "getFilmList";
	}
	/*
	
	public String getFilmViewList(Model model,
									@RequestParam(value="currentPage", defaultValue="1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
									@RequestParam(value="searchWord", required = false) String searchWord) {
		
		Map<String, Object> map = filmService.getFilmList(currentPage, rowPerPage, searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("filmList", map.get("filmList"));
		log.debug("getFilmList map: "+map);
		
		return "getFilmList";		
	}
	*/
}
