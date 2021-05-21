package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int deleteBoardfileOne(int boardfileId);
	int deleteBoardfileByBoardId(int boardId);
	int insertBoardfile(Boardfile boardfile);
	List<Boardfile> selectBoardfileByBoardId(int boardId);
}
