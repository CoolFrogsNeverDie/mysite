package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVO;

@Repository
public class BoardDAO {
		
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVO> getBoardList(){
		
		List<BoardVO> list = sqlSession.selectList("board.getBoardList");
		
		return list;
		
	}
	
	//---------------조회수 올리는 DAO
	
	public void hitCnt(int boardNum) {  
		
		int rows = sqlSession.update("board.hitCnt", boardNum);
		System.out.println("조회수 업데이트 완료! " +  rows);
	}
	
	
	//---------------
	
	public BoardVO getBoardByNum(int boardNum) {
		
		BoardVO vo = sqlSession.selectOne("board.getBoardByNum", boardNum);
		return vo;
	}
	
	//---------------

	public int updateBoard(BoardVO boardVO) {  
		int rows = sqlSession.update("board.updateBoard", boardVO);
		return rows;
	}
	
	//-----------------
	
	public int insertBoard(BoardVO boardVO) {  
		int rows = sqlSession.insert("board.insertBoard", boardVO);
		return rows;
	}
	//------------------
	
	public int deleteBoard(int boardNo) {   
		int rows = sqlSession.delete("board.deleteBoard", boardNo);
		return rows;
	}
	
	//------------------
	
	public List<BoardVO> getBoardByTitle(String boardTitle) {  
		List<BoardVO> searchList = sqlSession.selectList("board.selectByTitle", boardTitle);
		return searchList;
	}
}
