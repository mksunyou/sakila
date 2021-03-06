package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.Page;

//selectCountryList 쿼리를 찾기 시작. interface의 자식클래스를 만듬. 안에 selectCountryList메서드를 오버라이딩해서 List를 만듬.
@Mapper  
public interface CountryMapper {
	List<Country> selectCountry();
	List<Country> selectCountryList(Page page);
	int selectCountryTotal();
}
