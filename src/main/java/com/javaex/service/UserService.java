package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDAO;
import com.javaex.vo.UserVO;

@Repository
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	// insertUser
	
	public int insertUser(UserVO userVO) { 
		System.out.println("Service insertUser!");
		
		return userDAO.insertUser(userVO);
	}
	
	//loginUser
	public UserVO loginUser(UserVO userVO) {
		System.out.println("Service loginUser!");
		
		return userDAO.loginUser(userVO);
		
	}
	
	//logoutUser
	public int modifyUser(UserVO userVO) {   
		System.out.println("Service logout!");
		
		return userDAO.updateUser(userVO);
	}
	
	public UserVO modifyinfo(int num) { 
		UserVO vo = userDAO.getUserInfoByNum(num);
		return vo;
	}
}
