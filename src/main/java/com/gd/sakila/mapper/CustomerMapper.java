package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Customer;

@Mapper
public interface CustomerMapper {
	int insertCustomer(Customer customer);
	List<Map<String, Object>> selectRentalListByCustomer(int customerId);
	Map<String, Object> selectCustomerOne(int customerId);
	List<Map<String, Object>> selectCustomerList(Map<String, Object> map);
	int totalCustomer(Map<String, Object> totalMap);
	int updateCustomerActiveByScheduler();
}
