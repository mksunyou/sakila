package com.gd.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.StaffViewList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired StaffService staffService;
	
	@GetMapping("getStaffList")
	public String getStaffList(Model model) {
		List<StaffViewList> StaffList = staffService.getStaffList();
		model.addAttribute("staffList", StaffList);
		log.debug("getStaffList size(): "+StaffList.size());
		return "getStaffList";
	}
}
