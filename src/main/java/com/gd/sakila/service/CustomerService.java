package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CustomerService {
	@Autowired CustomerMapper customerMapper;
	
	//고객 리스트
	public Map<String, Object> getCustomerList(int rowPerPage, int currentPage, String name, Double phone, Double SID) {
		
		// List 갯수
		Map<String, Object> totalMap = new HashMap<>();
		totalMap.put("name", name);
		totalMap.put("phone", phone);
		totalMap.put("SID", SID);
		int customerTotal=customerMapper.totalCustomer(totalMap);
		int lastPage = (int)Math.ceil((double)customerTotal / rowPerPage); // Math.ceil은 올림함수
		log.debug("customerTotal: "+customerTotal);
		log.debug("lastPage: "+lastPage);
		log.debug("totalMap: "+totalMap);
		log.debug("customerTotal: "+customerTotal);
		
		// 페이징
		Page page = new Page();
		int beginRow = (currentPage-1)*rowPerPage;
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
				
		Map<String, Object> map = new HashMap<>();		
		map.put("name", name);
		map.put("phone", phone);
		map.put("rowPerPage", rowPerPage);
		map.put("currentPage", currentPage);
		map.put("beginRow", beginRow);	
		map.put("SID", SID);
		log.debug("getCustomerList map: "+map);
		
		List<Map<String, Object>> customerList = customerMapper.selectCustomerList(map);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("customerList", customerList);
		returnMap.put("lastPage", lastPage);
		log.debug("getCustomerList returnmap: "+returnMap);
		
		return returnMap;
	}
	
	// 고객 휴면처리
	public void modifyCustomerActiveByScheduler() {
		log.debug("@@@ modifyCustomerActiveByScheduler() 실행!!");
		int row = customerMapper.updateCustomerActiveByScheduler();
		log.debug("@@@ 휴먼고객 처리 행수 :" +row);
	}
}
