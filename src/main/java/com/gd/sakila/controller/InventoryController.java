package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.InventoryService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/admin")
@Controller
public class InventoryController {
	@Autowired InventoryService inventoryService;
	@GetMapping("/getInventoryList")
	public String getInventoryList(Model model,
									@RequestParam(value="title", required=false) String title,
									@RequestParam(value="storeId", required=false) Double storeId,
									@RequestParam(value="currentPage", defaultValue="1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
		Map<String,Object> map = inventoryService.getInventoryList(rowPerPage, currentPage, title, storeId);
		log.debug("map: "+map);
		log.debug("title: "+title);
		if(title != null && title.equals("")) {
			title=null;
		}
		log.debug("storeId: "+storeId);
		if(storeId != null && storeId==0) {
			storeId=null;
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("title", title);
		model.addAttribute("storeId",storeId);
		model.addAttribute("inventoryList", map.get("inventoryList"));
		model.addAttribute("lastPage", map.get("lastPage"));
		return "getInventoryList";
	}
}
