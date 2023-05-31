package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDAO;
import com.javaex.vo.BoardVO;
import com.javaex.vo.PagingVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	
	//---------------------All board List
	
	public List<BoardVO> getBoardList(){
	
		System.out.println("boardList Service");
		
		List<BoardVO> list = boardDAO.getBoardList();
		
		return list;
	}
	
	//--------------------BoardPagingInfo
	
	public Map<String, Object> getBoardPagingInfo(int selectPage){  
		PagingVO pagingVO = new PagingVO(selectPage,boardDAO.getTotalCnt());
		//paging 객체 생성함
		//객체 생성자로 알아서 페이징에 필요한 정보들 구성
		
		List<BoardVO> list = boardDAO.getBoardListByNum(pagingVO); //List로 원하는 정보 리스트 받아옴
		
		System.out.println("vo 객체 값 : " + pagingVO ); //vo객체 값 확인
		
		Map<String, Object> map = new HashMap<String, Object>();
		//맵에다가 담아
		map.put("pagingVO", pagingVO);
		map.put("BoardList", list);
		
		//맵으로 넘겨
		return map;
	}
	
	
	//-----------read Board ----------
	
	public BoardVO boardClickAction(int boardNum) {  
		boardDAO.hitCnt(boardNum);
		BoardVO vo = boardDAO.getBoardByNum(boardNum);
		return vo;
	}
	
	//---------------modifyForm 만들어주기
	
	public BoardVO getModifyBoard(int boardNum) {  
		BoardVO vo = boardDAO.getBoardByNum(boardNum);
		return vo;
	}
	
	//---------------------
	public int modifyBoard(BoardVO boardVO) {  
		int rows = boardDAO.updateBoard(boardVO);
		return rows;
	}
	
	//------------------------
	
	public int writeBoard(BoardVO boardVO) {   
		int rows = boardDAO.insertBoard(boardVO);
		return rows;
	}
	
	//----------------------------
	
	public int deleteBoard(int boardNo) {   
		int rows = boardDAO.deleteBoard(boardNo);
		return rows;
	}
	
	//----------------------------------
	
	public List<BoardVO> searchBoard(String keyword) {   
		List<BoardVO> searchList = boardDAO.getBoardByTitle(keyword);
		return searchList;
	}
}
