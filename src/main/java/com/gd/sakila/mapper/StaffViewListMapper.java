package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.StaffViewList;

@Mapper
public interface StaffViewListMapper {
	List<StaffViewList> selectStaffList(StaffViewList staffViewList);
}
