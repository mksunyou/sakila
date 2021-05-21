package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Staff;
/*
 * @Component, @Repository, @Service, @Controller --> Bean --> spring.getBean(클래스타입), @AutoWired <-- Dependency Injection
 * @Mapper : mybatis의 에노테이션 --> @Repository의 역할 --> Mapper+interface --> 구현클래스 자동으로 생성
 */
@Mapper //mapper.xml을 찾아서 메서드명과 mapper의 id명이 같으면 둘을 합쳐서 메서드를 구현클래스 생성시 오버라이딩
public interface StaffMapper {
	Staff selectStaffByLogin(Staff staff);
	List<Staff> selectStaffList(Staff staff);
}
