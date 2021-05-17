package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {
	@Autowired BoardMapper boardMapper;
	@Autowired CommentMapper commentMapper;
	
	// board 수정  액션
	public int modifyBoard(Board board ) {
		log.debug("modifyBoard() param "+board.toString());
		return boardMapper.updateBoard(board);
	}
	
	// board 삭제 액션
	public int removeBoard(Board board) {
		log.debug(board.toString()); //boardId, Pw 확인 디버그.
		return boardMapper.deleteBoard(board);
	}
	
	// board 추가 액션
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	
	// board 상세보기, 수정 폼
	public Map<String, Object> getBoardOne(int boardId) {
		// 1)상세보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("boardMap"+boardMap);
		// 2)댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("list size():"+commentList.size());
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardMap", boardMap);
		map.put("commentList", commentList);
		
		return map;
				
	}
	
	// board 리스트 출력
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String searchWord) {
		//1
		int boardTotal = boardMapper.selectTotal(searchWord); // searchWord
		
		/*
		 * 아래와 같은 식
		int lastPage = boardTotal / rowPerPage;
		if(boardTotal % rowPerPage != 0) {
			lastPage++;
		}	
		*/	
		int lastPage = (int)Math.ceil((double)boardTotal / rowPerPage);
		
		//2
		Page page = new Page();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		
		//3
		List<Board> boardList = boardMapper.selectBoardList(page); // Page
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lastPage", lastPage);
		map.put("boardList", boardList);
		return map;
	}
}
