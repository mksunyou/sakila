package com.gd.sakila.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.PaymentMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {
	@Autowired PaymentMapper paymentMapper;
	
	//월별 매출
	public Map<String, Object> getPaymentByMonth() {
		Map<String, Object> returnMap = paymentMapper.selectPaymentByMonth();
		log.debug("returnMap: "+returnMap);
		return returnMap;
	}
	
	// 일별 매출
	public Map<String, Object> getPaymentByDay(int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		Map<String, Object> returnMap = paymentMapper.selectPaymentByDay(map);
		log.debug("returnMap: "+returnMap);
		return returnMap;
	}
}
