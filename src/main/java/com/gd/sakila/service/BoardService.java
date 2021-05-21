package com.gd.sakila.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;
import com.gd.sakila.vo.Boardfile;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {
	@Autowired BoardMapper boardMapper;
	@Autowired BoardfileMapper boardfileMapper;
	@Autowired CommentMapper commentMapper;
	
	// board 수정  액션
	public int modifyBoard(Board board ) {
		log.debug("modifyBoard() param "+board.toString());
		return boardMapper.updateBoard(board);
	}
	
	// 삭제 액션
	public int removeBoard(Board board) {
		log.debug("▶▶▶▶▶▶ removeBoard() param: "+ board.toString());
		
		// 1) 게시글삭제 FK를 지정하지 않은 경우
		int boardRow = boardMapper.deleteBoard(board);
		if(boardRow == 0) {
			return 0;
		}
		log.debug("▶▶▶▶▶▶ removeBoard() boardRow :"+boardRow);
		
		// 2) 댓글삭제
		int commentRow = commentMapper.deleteCommentByBoardId(board.getBoardId());
		log.debug("▶▶▶▶▶▶ removeBoard() commentRow :"+commentRow);
		
		// 3) 물리적 파일 삭제(/resource/안에 파일)
				List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(board.getBoardId());
				if(boardfileList != null) {
					for(Boardfile f : boardfileList) {
						File temp = new File(""); // 프로젝트 폴더에 빈파일이 만들어진다.
						String path = temp.getAbsolutePath(); // 프로젝트필드
						File file = new File(path+"\\src\\main\\webapp\\resource\\"+f.getBoardfileName());
						file.delete();
					}
				}
		
		// 4) 파일 테이블 행 삭제
		int boardfileRow = boardfileMapper.deleteBoardfileByBoardId(board.getBoardId());
		log.debug("▶▶▶▶▶▶ removeBoard() boardfileRow :"+boardfileRow);		

		return boardRow;
	}
	
	// board 추가 액션
	public void addBoard(BoardForm boardForm) {
		//boardForm --> board, boardfile로 나눠야함.
		log.debug("boardForm: "+boardForm);
		// 1)
		Board board = boardForm.getBoard();
		log.debug("board: "+board.getBoardId());
		boardMapper.insertBoard(board); 
		// 입력시 만들어진 key값을 리턴받아야 한다. -> 리턴받을 수가 없다. -> 매개변수board의 boardId값을 변경해준다.
		log.debug("boardId"+board.getBoardId());
		
		// 2)
		List<MultipartFile> list = boardForm.getBoardfile();
		if(list != null) {
			for(MultipartFile f : list) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardId(board.getBoardId()); // auto increment로 입력된 값
				
				String originalFileName = f.getOriginalFilename();
				int p = originalFileName.lastIndexOf("."); //test.txt면 4;
				String ext = originalFileName.substring(p).toLowerCase(); // .txt
				String prename = UUID.randomUUID().toString().replace("-","");
				
				String filename = prename+ext;
				boardfile.setBoardfileName(filename); // 이슈 >> 중복으로 인해 덮어쓰기 가능
				
				boardfile.setBoardfileSize(f.getSize());
				boardfile.setBoardfileType(f.getContentType());
				log.debug("boardfile :"+boardfile);
				// 2-1)
				boardfileMapper.insertBoardfile(boardfile);
				
				// 2-2)
				//파일을 저장
				try {
					File temp = new File(""); // 프로젝트 폴더에 빈파일이 만들어진다.
					String path = temp.getAbsolutePath(); // 프로젝트필드
					f.transferTo(new File(path+"\\src\\main\\webapp\\resource\\"+filename));
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}			
		}		
		//return boardMapper.insertBoard(board);
	}
	
	
	// board 상세보기, 수정 폼
	public Map<String, Object> getBoardOne(int boardId) {
		// 1)상세보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("boardMap"+boardMap);
		
		// 2)boardfile 목록
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(boardId);
		log.debug("boardfileList size(): "+boardfileList.size());
		
		// 3)댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("list size():"+commentList.size());
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardMap", boardMap);
		map.put("boardfileList", boardfileList);
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
