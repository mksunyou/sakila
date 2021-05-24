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
	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model,
								@RequestParam(value="filmId", required = true) int filmId,
								@RequestParam(value="storeId", required = true) int storeId) {
		Map<String, Object> map = filmService.getFilmOne(filmId, storeId);
		log.debug("getFilmOne map: "+map);
		model.addAttribute("paramMap", map.get("paramMap"));
		model.addAttribute("filmCount", map.get("filmCount"));
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("filmMap", map.get("filmMap"));
		return "getFilmOne";
	}
	
	@GetMapping("/getFilmList")
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
}
