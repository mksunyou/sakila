package com.gd.sakila.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	private Board board;	
	private List<MultipartFile> boardfile; // 파일이 여러개 받을 수 있다 List or 배열.
}
