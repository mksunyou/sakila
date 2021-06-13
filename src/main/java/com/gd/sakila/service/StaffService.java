package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.mapper.StaffViewListMapper;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffViewList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class StaffService {
	@Autowired StaffMapper staffMapper; // DI, @Autowired가 없으면 --> NullPointException 발생...
	@Autowired StaffViewListMapper staffViewListMapper;
	
	// staff 추가
	public int addStaff(Staff staff) {
		staffMapper.insertStaff(staff);
		return staff.getStaffId();
	}
	
	// staff 상세보기
	public Map<String, Object> getStaffOne(int staffId) {
		Map<String, Object> map = staffMapper.selectStaffOne(staffId);
		log.debug("map: "+map);
		return map;
	}
	
	// 로그인 staff
	public Staff login(Staff staff) {
		log.debug("login() param staff: "+staff);
		return staffMapper.selectStaffByLogin(staff); // null or staff객체
	}
	
	// staff 리스트 출력
	public List<StaffViewList> getStaffList() {
		return staffViewListMapper.selectStaffList(null);
	}
}
