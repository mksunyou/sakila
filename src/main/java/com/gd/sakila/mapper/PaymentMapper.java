package com.gd.sakila.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
	Map<String, Object> selectPaymentByCustomer(int customerId);
	Map<String, Object> selectPaymentByDay(Map<String, Object> map);
	Map<String, Object> selectPaymentByMonth();
}
