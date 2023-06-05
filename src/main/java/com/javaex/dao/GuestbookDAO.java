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
	
	
	public int insertGuest(GuestbookVO guestbookVO) {   
		System.out.println("insertGuest in DAO!");
		System.out.println("inDAO : " +guestbookVO);
		int rows = sqlSession.insert("guestbook.insertSelectKey", guestbookVO);
		System.out.println("실행이 된 후에 vo에 num 있는지 확인 : " +guestbookVO);

		return rows;
	}
	
	public GuestbookVO selectGuestBookByNum(int guestbookNum) {   
		
		GuestbookVO vo = sqlSession.selectOne("guestbook.selectByNum", guestbookNum);
		
		return vo;
	}
	
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
		return rows;
	}
}
