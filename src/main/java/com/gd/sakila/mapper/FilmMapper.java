package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilmMapper {
	int insertActorListByFilm(Map<String, Object> map);
	int deleteActorListByFilm(int filmId);
	List<Map<String, Object>> selectActorListByFilm(int filmId);
	List<Integer> selectFilmInStock(Map<String, Object> map);
	Map<String, Object> selectFilmOne(int filmId);
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);
	int selectTotal(Map<String, Object> totalMap);
}