package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;
import com.gd.sakila.service.LanguageService;
import com.gd.sakila.vo.Category;
import com.gd.sakila.vo.FilmForm;
import com.gd.sakila.vo.Language;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	@Autowired FilmService filmService;
	@Autowired LanguageService languageService;
	
	// 영화 수정 액션
	@PostMapping("modifyFilm")
	public String modifyFilm(FilmForm filmForm) {
		filmService.modifyFilm(filmForm);
		log.debug("filmForm "+filmForm);
		return "redirect:/admin/getFilmOne?filmId="+filmForm.getFilm().getFilmId();
	}
	
	// 영화 수정 폼
	@GetMapping("/modifyFilm")
	public String modifyFilm(Model model,
								@RequestParam(value="filmId")int filmId) {
		List<Category> categoryList = filmService.getCategoryList();
		List<Language> languageList = languageService.getLanguageList();
		Map<String, Object> map = filmService.getFilmOne(filmId);
		model.addAttribute("map",map);
		model.addAttribute("filmMap", map.get("filmMap"));
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("languageList", languageList);
		log.debug("filmMap: "+map.get("filmMap"));
		log.debug("categoryList: "+categoryList);
		log.debug("languageList: "+languageList);
		return "modifyFilm";
	}
	
	// 영화 추가 list 폼
	@GetMapping("/addFilm")
	public String addFilm(Model model) {
		List<Category> categoryList = filmService.getCategoryList();
		List<Language> languageList=languageService.getLanguageList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("languageList", languageList);
		log.debug("categoryList: "+categoryList);
		log.debug("languageList: "+languageList);		
		
		return "addFilm";
	}
	// 영화 추가 액션
	@PostMapping("/addFilm") // 매개변수의 이름과 name이 같으면 맵핑, 참조타입은 필드를 찾는것이 아니라 setFilmId를 찾음.
	public String addFilm(FilmForm filmForm) {  // 참조타입은 필드명과 name이 같으면 맵핑. 스프링에서는 이것을 커멘드타입. reflectionAPI 검색해보자!
		int filmId = filmService.addFilm(filmForm);
		log.debug("filmId: "+filmId);
		return "redirect:/admin/getFilmOne?filmId="+filmId;
		
	}
	
	// 영화배우 수정 액션
	@PostMapping("/modifyFilmActor")
	public String modifyFilmActor(	@RequestParam(value="actorId") List<Integer> actorId,
									@RequestParam(value="filmId", required = true) int filmId
									) {
		log.debug("filmId: "+filmId);
		log.debug("actorId: "+actorId);
		filmService.modifyFilmActor(actorId,filmId);
		// service - mapper
		// delete from film_actor where film_id = #{filmId}
		// for{
		// insert into(actor_id, film_id) values(#{ck[0],#{filmId})
		// }
		
		return "redirect:/admin/getFilmOne?filmId="+filmId;
	}
	
	// 상세보기 내에서 영화배우
	@GetMapping("/getActorListByFilm")
	public String getActorListByFilm(Model model,
								@RequestParam(value="filmId", required = true) int filmId) {
		List<Map<String, Object>> list = filmService.getActorListByFilm(filmId);
		log.debug("list.size(): "+list.size());
		model.addAttribute("actorList", list);
		model.addAttribute("filmId", filmId);
		return "getActorListByFilm";		
	}
	
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
