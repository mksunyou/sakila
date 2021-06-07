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

import com.gd.sakila.mapper.AddressMapper;
import com.gd.sakila.mapper.CityMapper;
import com.gd.sakila.service.CountryService;
import com.gd.sakila.service.CustomerService;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.CustomerForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
	@Autowired CountryService countryService;
	@Autowired CityMapper cityMapper;
	@Autowired AddressMapper addressMapper;
	
	
	// 고객 수정 액션
	@PostMapping("/modifyCustomer")
	public String modifyCustomer(CustomerForm customerForm) {
		int customerId = customerService.modifyCustomer(customerForm);
		log.debug("modify customerId: "+customerId);
		return "redirect:/admin/getCustomerOne?customerId="+customerId;
	}
	
	// 고객 수정 폼
	@GetMapping("/modifyCustomer")
	public String modifyCustomer(Model model,
									@RequestParam(value="customerId", required=true) int customerId) {
		List<Country> countryList = countryService.getCountryList();
		Map<String, Object> map = customerService.getCustomerOne(customerId);
		model.addAttribute("customerMap",map.get("customerMap"));
		model.addAttribute("countryList",countryList);
		log.debug("countryList: "+countryList);
		return "modifyCustomer";
		
	}
	
	//고객 추가 액션
	@PostMapping("/addCustomer")
	public String addCustomer(CustomerForm customerForm) {
		int customerId=customerService.addCustomer(customerForm);
		log.debug("add customerId: "+customerId);
		return "redirect:/admin/getCustomerOne?customerId="+customerId;
	}
	
	//고객 추가 폼
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		List<Country> countryList = countryService.getCountryList();
		model.addAttribute("countryList",countryList);
		log.debug("countryList: "+countryList);
		return "addCustomer";
	}
	
	
	//고객 상세보기
	@GetMapping("/getCustomerOne")
	public String getCustomerOne(Model model,
									@RequestParam(value="customerId", required=true) int customerId) {
		Map<String, Object> map = customerService.getCustomerOne(customerId);
		model.addAttribute("customerMap",map.get("customerMap"));
		model.addAttribute("rentalList", map.get("rentalList"));
		model.addAttribute("paymentMap", map.get("paymentMap"));
		log.debug("map: "+map);
		return "getCustomerOne";
	}
	// 고객 리스트
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
