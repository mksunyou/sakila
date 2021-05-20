package com.gd.sakila.service;

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
	public Staff login(Staff staff) {
		log.debug("login() param staff: "+staff);
		return staffMapper.selectStaffByLogin(staff); // null or staff객체
	}
}
