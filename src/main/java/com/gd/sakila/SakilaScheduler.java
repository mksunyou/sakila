package com.gd.sakila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SakilaScheduler {
	@Autowired CustomerService customerService;
	
	// Scheduled 메서드 void 반환, 매개변수 0개
	@Scheduled(cron = "0 0 0 1 * *") // (cron=*초 *분 *시 *일 *월 *요일 *연도), 따라서 매달 1일 24시 0분 0초에 스케쥴 실행. 
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByScheduler();
		log.debug("@@@ modifyCustomerActive 스케쥴러 실행 완료");
	}
}
