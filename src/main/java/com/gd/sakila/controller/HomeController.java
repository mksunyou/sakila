package com.gd.sakila.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired StaffService staffService;
	// Logger log = LoggerFactory.getLogger(this.getClass()); // (this.getClass(HomeController)) -> @Slf4j
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		// 디버깅
			//System.out.println("home...");
		log.debug("test"); // test 출력 (HomeController)
		return "home";
	}
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Staff staff) { // servlet에서 세션을 직접 사용, 컨트롤러 메서드의 매개변수는 DI대상
		log.debug("login staff param: "+staff.toString());
		Staff loginStaff = staffService.login(staff);
		log.debug("login staff: "+loginStaff);
		if(loginStaff != null) { //로그인 성공시
			session.setAttribute("loginStaff", loginStaff); //new Staff();
		}
		
		return "redirect:/";
	}
}