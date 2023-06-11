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
	
	
	//---------------------Board Paging class
	
	public Map<String, Object> getPagingBoard(int crtPage, String keyword){  
		
		
		
		//선택한 페이지 음수면 1페이지로 처리
		crtPage = (crtPage >=1) ? crtPage : 1 ;
		
		//////////////////////////////////////////////////////////////
		//리스트가져오기
		//////////////////////////////////////////////////////////////
		
		//페이지당 글 갯수
		int listCnt = 10;
		
		//rownum 시작글 번호, 끝 번호
		int startRnum = (crtPage -1)*10+1;
		int endRnum = (startRnum+listCnt)-1;
		
		Map<String, Object> pagingInfo = new HashMap<>();
		pagingInfo.put("startRnum", startRnum);
		pagingInfo.put("endRnum", endRnum);
		pagingInfo.put("keyword", keyword);
		
		List<BoardVO> list = boardDAO.selectList(pagingInfo);

		
		//////////////////////////////////////////////////////////////
		//페이징 계산
		//////////////////////////////////////////////////////////////
		//전체 글 갯수
		int totalCount = boardDAO.getBoardCnt(keyword);
		System.out.println("넘어온 keyword : " + keyword);
		System.out.println("total Count 갯수 : " + totalCount);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		//1 ---> 5
		//2 ---> 5
		//3 ---> 5
		//6 ---> 10
		
		//끝 버튼 번호
		int endPageBtnNo = (int)(Math.ceil((double)crtPage  / pageBtnCount) * pageBtnCount);
		
		//시작버튼 번호
		int StartPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
		
		
		//다음 화살표---true(그린다.) or false(안 그린다.)
		boolean next = false;
		
		if(endPageBtnNo * listCnt <totalCount) { 
			next = true;
		}else {
			next = false;
			//끝 버튼 번호를 다시 지정
			endPageBtnNo = (int)(Math.ceil((double)totalCount/listCnt));
		}
		
		//이전 화살표
		boolean prev = false;
		if(StartPageBtnNo != 1) {  
			prev = true;
		}
		
		//맵으로 만들기
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boardList", list);
		pMap.put("prev", prev);
		pMap.put("StartPageBtnNo", StartPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("keyword", keyword);
		
		System.out.println("만들어진 map : " + pMap);
		
		
		
		return pMap;
	}
	
	
	
	//---------------------All board List
	
	public List<BoardVO> getBoardList(){
	
		System.out.println("boardList Service");
		
		List<BoardVO> list = boardDAO.getBoardList();
		
		return list;
	}
	
	//--------------------BoardPagingInfo
	
//	public Map<String, Object> getBoardPagingInfo(int selectPage, String keyword){  
//		PagingVO pagingVO = new PagingVO(selectPage,boardDAO.getTotalCnt(keyword),keyword);
//		//paging 객체 생성함
//		//객체 생성자로 알아서 페이징에 필요한 정보들 구성
//		
//		List<BoardVO> list = boardDAO.getBoardListByNum(pagingVO); //List로 원하는 정보 리스트 받아옴
//		
//		System.out.println("vo 객체 값 : " + pagingVO ); //vo객체 값 확인
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		//맵에다가 담아
//		map.put("pagingVO", pagingVO);
//		map.put("BoardList", list);
//		
//		//맵으로 넘겨
//		return map;
//	}
	
	
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
