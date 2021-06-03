package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.InventoryMapper;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryService {
	@Autowired InventoryMapper inventoryMapper;
	public Map<String, Object> getInventoryList(int rowPerPage, int currentPage, String title, Double storeId) {
		// 1. inventory개수
		Map<String, Object> totalMap = new HashMap<>();
		totalMap.put("title",title);
		totalMap.put("storeId",storeId);
		
		int inventoryTotal = inventoryMapper.totalInventory(totalMap);
		
		int lastPage = (int)Math.ceil((double)inventoryTotal / rowPerPage); // Math.ceil은 올림함수
		log.debug("inventoryTotal: "+inventoryTotal);
		log.debug("lastPage: "+lastPage);
		
		// 2. 페이징
		Page page = new Page();
		int beginRow = (currentPage-1)*rowPerPage;
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		log.debug("page: "+page);
		
		// 3. List
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("totalMap", totalMap);
		paramMap.put("currentPage",currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("lastPage", lastPage);
		
		List<Map<String, Object>> inventoryList = inventoryMapper.selectInventoryList(paramMap);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("inventoryList", inventoryList);
		returnMap.put("lastPage",lastPage);
		
		return returnMap;
	}
}
