package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVO;

@Repository
public class GuestbookDAO {

	@Autowired
	SqlSession sqlSession;
	
	//getbookList
	
	public  List<GuestbookVO> getGuestbookList(){ 
		List<GuestbookVO> list = sqlSession.selectList("guestbook.getGuestbookList");
		System.out.println(list);
		return list;
	}


	//insertGuestbook
	public int insertGuestbook(GuestbookVO guestbookVO) { 
		int rows = sqlSession.insert("guestbook.insertGuestbook", guestbookVO);
		return rows;
	}
	
	public int deleteGuestbook(GuestbookVO guestbookVO) {  
		int rows = sqlSession.delete("guestbook.deleteGuestbook", guestbookVO);
		return 0;
	}
}
