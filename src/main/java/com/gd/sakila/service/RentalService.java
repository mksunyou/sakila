package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.RentalMapper;
import com.gd.sakila.vo.Rental;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RentalService {
	@Autowired RentalMapper rentalMapper;
	// 미반납 목록
	public List<Map<String, Object>> getNotReturnList(int storeId) {
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		List<Map<String, Object>> notReturnList = rentalMapper.selectNotReturnList(map);
		return notReturnList;
	}
	
	//대여
	public Map<String, Object> addRental(Rental rental) {
		
		rentalMapper.insertRental(rental);
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", rental.getCustomerId());
		map.put("inventoryId", rental.getInventoryId());
		map.put("storeId", rental.getStaffId());
		log.debug("map" + map);
		
		return map;
	}
}
