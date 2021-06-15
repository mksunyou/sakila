package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Film;

@Mapper
public interface FilmMapper {
	List<Map<String, Object>> selectBestsellerByFilm();
	List<Map<String, Object>> selectFilmListByRental();
	int updateFilmCategory(Map<String, Object> map);
	int updateFilm(Film film);
	List<Film> selectFilmListByInventory();
	int insertFilmCategory(Map<String, Object> map);
	int insertFilm(Film film);
	int insertActorListByFilm(Map<String, Object> map);
	int deleteActorListByFilm(int filmId);
	List<Map<String, Object>> selectActorListByFilm(int filmId);
	List<Integer> selectFilmInStock(Map<String, Object> map);
	Map<String, Object> selectFilmOne(int filmId);
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);
	int selectTotal(Map<String, Object> totalMap);
}
