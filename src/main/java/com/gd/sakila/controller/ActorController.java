package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.ActorService;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class ActorController {
	@Autowired ActorService actorService;
	
	// 배우 추가 폼
	@GetMapping("/addActor")	
	public String addActor() {
		return "addActor";
	}
	// 배우 추가 액션
	@PostMapping("/addActor")
	public String addActor(Actor actor) {
		log.debug("add actor: "+actor);
		actorService.addActor(actor);
		return "redirect:/admin/getActorList";
	}
	
	// 배우 리스트
	@GetMapping("/getActorList") // Actor_info_view
	public String getActorList( Model model,
								@RequestParam(value="currentPage", defaultValue="1") int currentPage,
								@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
								@RequestParam(value="actor", required = false) String actor) {
		log.debug("actor: "+actor);
		if(actor != null && actor.equals(" ")) {
			actor=null;
		}
		Map<String, Object> map = actorService.getActorList(currentPage, rowPerPage, actor);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("actor", actor);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("actorTotal", map.get("actorTotal"));
		model.addAttribute("actorList", map.get("actorList"));
		log.debug("map: "+map);
		
		return "getActorList";
	}
}
