package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class StaffService {
	@Autowired StaffMapper staffMapper; // DI, @Autowired가 없으면 --> NullPointException 발생...
	
	// 로그인 staff
	public Staff login(Staff staff) {
		log.debug("login() param staff: "+staff);
		return staffMapper.selectStaffByLogin(staff); // null or staff객체
	}
	
	// staff 리스트 출력
	public Map<String, Object> getStaffList() {
		List<Staff> staffList = staffMapper.selectStaffList(null);		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("staffList", staffList);
		log.debug("getStaffList map: "+map);
		return map;
	}
}
