package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVO;
import com.javaex.vo.PagingVO;

@Repository
public class BoardDAO {
		
	@Autowired
	SqlSession sqlSession;
	
	
	//-----------------all boardList
	
	public List<BoardVO> getBoardList(){
		
		List<BoardVO> list = sqlSession.selectList("board.getBoardList");
		
		return list;
		
	}

	//-----------------get Board List By PageNumber()
	
	public List<BoardVO> getBoardListByNum(PagingVO pagingVO){
		
		List<BoardVO> list = sqlSession.selectList("board.getBoardListByNum", pagingVO);
		
		return list;
		
	}

	//---------------totalBoard cnt
	
	public int getTotalCnt(String keyword) {  
		int totalCnt  = sqlSession.selectOne("board.getBoardCnt", keyword);
		
		return totalCnt;
	}
	
	//---------------조회수 올리는 DAO
	
	public void hitCnt(int boardNum) {  
		
		int rows = sqlSession.update("board.hitCnt", boardNum);
		System.out.println("조회수 업데이트 완료! " +  rows);
	}
	
	
	//---------------getBoardByNum
	
	public BoardVO getBoardByNum(int boardNum) {
		
		BoardVO vo = sqlSession.selectOne("board.getBoardByNum", boardNum);
		return vo;
	}
	
	//---------------updateBoard

	public int updateBoard(BoardVO boardVO) {  
		int rows = sqlSession.update("board.updateBoard", boardVO);
		return rows;
	}
	
	//-----------------insert
	
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
