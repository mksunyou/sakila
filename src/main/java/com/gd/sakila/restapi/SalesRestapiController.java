package com.gd.sakila.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SalesRestapiController {
	@Autowired CategoryMapper categoryMapper;
	@Autowired PaymentService paymentService;
	
	// 월별 매출
	@GetMapping("/getSalesByMonth")
	public Map<String, Object> getPaymentByMonth(Model model) {		
		return paymentService.getPaymentByMonth();
	}
	// 일별 매출
	@GetMapping("/getSalesByDay")
	public String getPaymentByDay(Model model,
									@RequestParam(value="year", defaultValue = "2005") int year,
									@RequestParam(value="month", defaultValue = "6") int month) {
		
		Map<String, Object> map = paymentService.getPaymentByDay(year, month);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("map", map);		
		log.debug("map: "+map);
		return "getSalesByDay";
	}
	
	// 카테고리별 매출
	@GetMapping("/getSalesByCategory")
	public List<Map<String, Object>> getSalesByCategory() {
		return categoryMapper.selectSalesByCategory();
	}
}
