package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gd.sakila.service.StaffService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired StaffService staffService;
	
	@GetMapping("getStaffList")
	public String getStaffList(Model model) {
		Map<String, Object> map = staffService.getStaffList();
		model.addAttribute("staffList", map.get("staffList"));
		log.debug("getStaffList size(): "+map.size());
		return "getStaffList";
	}
}
