package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDAO;
import com.javaex.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardVO> getBoardList(){
	
		System.out.println("boardList Service");
		
		List<BoardVO> list = boardDAO.getBoardList();
		
		return list;
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
