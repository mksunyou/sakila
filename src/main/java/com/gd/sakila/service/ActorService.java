package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.ActorMapper;
import com.gd.sakila.vo.Actor;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ActorService {
	@Autowired ActorMapper actorMapper;
	public int addActor(Actor actor) {
		log.debug("actor: "+actor);
		return actorMapper.insertActor(actor);
	}
	
	public Map<String, Object> getActorList(int currentPage, int rowPerPage, String actor) {
		// total 갯수
		Map<String, Object> totalMap = new HashMap<>();
		totalMap.put("actor", actor);
		
		int actorTotal=actorMapper.selectActorTotal(totalMap);
		
		int lastPage = (int)Math.ceil((double)actorTotal / rowPerPage);
		log.debug("actorTotal: "+actorTotal);
		log.debug("lastPage: "+lastPage);
		
		// Page
		Page page = new Page();
		int beginRow = (currentPage-1)*rowPerPage;
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		log.debug("page: "+page);
		
		// List
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("beginRow", beginRow);
		paraMap.put("currentPage", currentPage);
		paraMap.put("rowPerPage", rowPerPage);
		paraMap.put("actor", actor);
		log.debug("paraMap: "+paraMap);
		
		List<Map<String, Object>> actorList = actorMapper.selectActorInfoList(paraMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("actorTotal", actorTotal);
		map.put("lastPage", lastPage);
		map.put("actorList", actorList);
		log.debug("getActorList map: "+map);
		
		return map;
	}
}
