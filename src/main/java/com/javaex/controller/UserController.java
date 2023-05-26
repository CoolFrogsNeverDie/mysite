package com.javaex.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//------------------user joinForm----------------------------------------------
	
	
	@RequestMapping(value = "/joinForm" , method = RequestMethod.GET)
	public String joinForm() { 
		System.out.println("joinForm controller start!");
		
		return "user/joinForm";
	}
	
	//------------------user join Success----------------------------------------------

	
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join(@ModelAttribute UserVO userVO) { 
		 System.out.println("join Start!");
		 int cnt = userService.insertUser(userVO);
		 
		 
		 if(cnt > 0) { 
			 return "user/joinOk";			 
		 }else { 
			 return "redirect:/user/joinForm";
		 }	 
	}

	//------------------user loginForm ----------------------------------------------
	
	
	@RequestMapping(value = "/loginForm" , method = RequestMethod.GET)
	public String loginForm() { 
		System.out.println("loinForm Start!");
			return "user/loginForm";			 
	}

	//------------------user login----------------------------------------------
	
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login(@ModelAttribute UserVO userVO, HttpSession session) { 
		System.out.println(userVO);
		UserVO authUser = userService.loginUser(userVO);

		if (authUser == null) { //로그인 성공 vo.getId()로 비교하려고 했는데 애초에 vo가 null이면 조건이 성립되지 않기때문에 vo로 비교했어야 했다.. ㅈㅇㅇㅃ가 발견하심 ㄷㄷ
			return "redirect:/user/loginForm?result=fail";			 			
		}else {  
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}
	}
	
	//------------------user logout---------------------------------------------
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";			 		
	}
	
	
	
	//------------------user logout---------------------------------------------
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public String modifyForm(HttpSession session, Model model) {
		UserVO user = (UserVO) session.getAttribute("authUser");
		if(user == null) { 
			return "redirect:/login";			 					
		}else {  			
			UserVO user2 = userService.modifyinfo(user.getNo());
			model.addAttribute("modifyFormInfo", user2);
			return "user/modifyForm";			 		
		}
	}
	
	
	//------------------user modify!! ---------------------------------------------
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@ModelAttribute UserVO userVO, HttpSession session) {
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		userVO.setNo(authUser.getNo());
		
		if(userService.modifyUser(userVO) > 0) {   
			authUser.setName(userVO.getName());
			session.setAttribute("authUser", authUser);
		}
		
		return "redirect:/user/modifyForm";
	}
	
	
	
	
	
}
