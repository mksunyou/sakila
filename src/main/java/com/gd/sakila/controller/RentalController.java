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
import com.gd.sakila.service.RentalService;
import com.gd.sakila.vo.Rental;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
public class RentalController {
	@Autowired RentalService rentalService;
	@Autowired FilmService filmService;
	
	@PostMapping("/addRental")
	public String addRental(Rental rental) {
		return "redirect:/admin/getCustomerOne?customerId="+rental.getCustomerId();
	}
	
	@GetMapping("/addRental")
	public String addRetal(Model model,
							@RequestParam(value="customerId", required=true) int customerId) {
		List<Map<String, Object>> filmList = filmService.getFilmListByRental();
		model.addAttribute("filmList",filmList);
		return "addRental";
	}
}
