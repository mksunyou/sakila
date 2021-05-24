package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.Page;

@Mapper
public interface FilmMapper {
	List<Integer> selectFilmInStock(Map<String, Object> map);
	Map<String, Object> selectFilmOne(int filmId);
	List<Film> selectFilmList(Page pageparam);
	int selectTotal(String searchWord);
}
