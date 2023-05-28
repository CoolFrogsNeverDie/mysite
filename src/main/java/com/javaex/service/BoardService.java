package com.javaex.service;

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
	
	public List<BoardVO> getBoardList(){
	
		System.out.println("boardList Service");
		
		List<BoardVO> list = boardDAO.getBoardList();
		
		return list;
	}
	
	//BoardInfo
	public Map<String, Object> getBoardInfo(int selectPage){  
		PagingVO pagingVO = new PagingVO(selectPage,boardDAO.getTotalCnt());
		boardDAO.getBoardListByNum(pagingVO);
		List<BoardVO> list = boardDAO.getBoardListByNum(pagingVO);
		System.out.println("Service of BoardInfo in Service");
		System.out.println(pagingVO.toString());
		System.out.println(list.toString());
		
		return null;
	}
	
	
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
