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

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffViewList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired StaffService staffService;
	
	@PostMapping("/addStaff")
	public String addStaff(Staff staff) {
		int staffId = staffService.addStaff(staff);
		log.debug("staffId: "+staffId);
		return "redirect:/admin/getStaffList";
	}
	
	@GetMapping("/addStaff")
	public String addStaff(Model model) {
		return "addStaff";
	}
	
	@GetMapping("/getStaffOne")
	public String getStaffOne(Model model,
								@RequestParam(value="staffId", required = true) int staffId) {
		Map<String, Object> map = staffService.getStaffOne(staffId);
		log.debug("staffId: "+staffId);
		model.addAttribute("map",map);
		return "getStaffOne";		
	}
	
	@GetMapping("/getStaffList")
	public String getStaffList(Model model) {
		List<StaffViewList> StaffList = staffService.getStaffList();
		model.addAttribute("staffList", StaffList);
		log.debug("getStaffList size(): "+StaffList.size());
		return "getStaffList";
	}
}
