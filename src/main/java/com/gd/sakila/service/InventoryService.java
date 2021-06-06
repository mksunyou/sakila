package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.InventoryMapper;
import com.gd.sakila.vo.Inventory;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryService {
	@Autowired InventoryMapper inventoryMapper;
	
	// 재고 삭제
	public int removeInventory(Inventory inventory) {
		int row = inventoryMapper.deleteInventory(inventory);
		log.debug("delete row: "+ row);
		return row;
	}
	
	// 재고 추가
	public int addInventory(Inventory inventory) {
		int row = inventoryMapper.insertInventory(inventory);
		log.debug("insert row:"+row);
		return row;
	}
	
	// inventoryList
	public Map<String, Object> getInventoryList(int rowPerPage, int currentPage, String title) {
		// 1. inventory개수
		int inventoryTotal = inventoryMapper.totalInventory(title);
		
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
		paramMap.put("title", title);
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
