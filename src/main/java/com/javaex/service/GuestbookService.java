package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDAO;
import com.javaex.vo.GuestbookVO;

@Service
public class GuestbookService {
	
	@Autowired
	GuestbookDAO guestbookDAO;
	
	//------AJAX 방명록에서 사용!
	
	public GuestbookVO addGuest(GuestbookVO guestbookVO) {   
		System.out.println("addGuest in Service");
		System.out.println(guestbookVO);
			guestbookDAO.insertGuest(guestbookVO);  
			//insert + 매개변수로 활용되던 guestbookVO에 no 추가
			int insertGuestNum = guestbookVO.getNum();
			//guestbook에 추가된 no로 방명록 찾아오기
			GuestbookVO guestbookvo = guestbookDAO.selectGuestBookByNum(insertGuestNum);	
		
		return guestbookvo;
	}
	
	
	//----------방명록 불러오기
	
	public List<GuestbookVO> getGuestbooklist(){
		List<GuestbookVO> list = guestbookDAO.getGuestbookList();
		return list;
	}
	
	
	//----------방명록 작성
	
	public int guestBookWrite(GuestbookVO guestbookVO) { 
		System.out.println("guestbook write in service");
		int rows = guestbookDAO.insertGuestbook(guestbookVO);
		return rows;
	}
	
	//----------방명록 삭제 시 password 대조 후 삭제
	
	public int deleteGuestbook(GuestbookVO guestbookVO) {  
		int rows = guestbookDAO.deleteGuestbook(guestbookVO);
		return rows;
	}

}
