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
