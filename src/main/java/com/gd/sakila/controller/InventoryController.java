package com.gd.sakila.controller;

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
import com.gd.sakila.service.InventoryService;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.Inventory;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/admin")
@Controller
public class InventoryController {
	@Autowired InventoryService inventoryService;
	@Autowired FilmService filmService;
	
	@PostMapping("removeInventory")
	public String removeInventory(Inventory inventory) {
		int inventoryId = inventoryService.removeInventory(inventory);
		log.debug("remove inventoryId"+inventoryId);
		return "redirect:/admin/getInventoryList";
	}
	
	// 재고 삭제 폼
	@GetMapping("/removeInventory")
	public String removeInventory(Model model) {
		List<Film> filmList = filmService.getFilmListByInventory();
		model.addAttribute("filmList", filmList);
		return "removeInventory";
	}
	// 재고 추가 액션
	@PostMapping("/addInventory")
	public String addInventory(Inventory inventory) {
		int inventoryId = inventoryService.addInventory(inventory);		
		log.debug("add inventoryId"+ inventoryId);
		return "redirect:/admin/getInventoryList";
	}
	// 재고 추가 폼
	@GetMapping("/addInventory")
	public String addInventory(Model model) {
		List<Film> filmList = filmService.getFilmListByInventory();
		model.addAttribute("filmList",filmList);
		log.debug("filmListByInventory" + filmList);
		return "addInventory";
	}
	// 재고 리스트
	@GetMapping("/getInventoryList")
	public String getInventoryList(Model model,
									@RequestParam(value="title", required=false) String title,
									@RequestParam(value="currentPage", defaultValue="1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
		Map<String,Object> map = inventoryService.getInventoryList(rowPerPage, currentPage, title);
		log.debug("map: "+map);
		log.debug("title: "+title);
		if(title != null && title.equals("")) {
			title=null;
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("title", title);
		model.addAttribute("inventoryList", map.get("inventoryList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		return "getInventoryList";
	}
}
