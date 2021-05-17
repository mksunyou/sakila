package com.gd.sakila.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	// Logger log = LoggerFactory.getLogger(this.getClass()); // (this.getClass(HomeController)) -> @Slf4j
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		// 디버깅
			//System.out.println("home...");
		log.debug("test"); // test 출력 (HomeController)
		return "home";
	}
}