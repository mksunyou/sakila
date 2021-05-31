package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model,
									@RequestParam(value="currentPage", defaultValue="1") int currentPage,
									@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
									@RequestParam(value="name", required = false) String name,
									@RequestParam(value="phone", required = false) Double phone,
									@RequestParam(value="SID", required = false) Double SID) {
		log.debug("name: "+name);
		if(name != null && name.equals("")) {
			name=null;
		}
		log.debug("phone: "+phone);
		if(phone != null && phone==0) {
			phone=null;
		}
		
		
		Map<String, Object> map = customerService.getCustomerList(rowPerPage, currentPage, name, phone, SID);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("customerList", map.get("customerList"));
		model.addAttribute("lastPage", map.get("lastPage"));
				
		return "getCustomerList";
	}
}
