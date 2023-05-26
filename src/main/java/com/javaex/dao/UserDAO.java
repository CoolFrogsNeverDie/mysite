package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVO userVO) { 
		
		return sqlSession.insert("user.insertUser", userVO);
	}
	
	public UserVO loginUser(UserVO userVO) { 
		
		
		return sqlSession.selectOne("user.loginUser", userVO);
	}
	
	public int updateUser(UserVO userVO) {   
		
		int rows = sqlSession.update("user.updateUser", userVO);
		
		return rows;
	}
	
	public UserVO getUserInfoByNum(int num) { 
		UserVO vo = sqlSession.selectOne("user.getUserInfoByNum", num);
		return vo;
	}
}
