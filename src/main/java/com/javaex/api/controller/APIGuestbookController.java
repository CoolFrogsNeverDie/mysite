package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVO;
import com.javaex.vo.JasonResult;

@RequestMapping("/api/Guestbook")
@Controller
public class APIGuestbookController {
	
	@Autowired
	GuestbookService guestbookService;
	
	//----------------AJAX 를 이용한 guestBookForm
	
	@RequestMapping(value = "/guestbookview", method = {RequestMethod.GET, RequestMethod.POST})
	public String guestBookViewAJAX(Model model) {  
		
		List<GuestbookVO> list = guestbookService.getGuestbooklist();
		model.addAttribute("guestbookList", list);
		return "guestbook/addListAjax";
	}
	
	//----------------AJAX 를 이용한 guestBook

	//guestBookWirte
	@ResponseBody//---> viewResolve 안 닿게 하고, jackson이 움직이게 함
	@RequestMapping(value = "/guestbookWrite", method = {RequestMethod.GET, RequestMethod.POST})
	public JasonResult guestBookWriteAJAX(@ModelAttribute GuestbookVO guestbookVO ) {
		System.out.println("controller로 넘어온 guestbook" + guestbookVO);
			
			//추가한 객체 리턴됨 JSON으로 리턴해야 한다.
			GuestbookVO Guestvo = guestbookService.addGuest(guestbookVO);
			System.out.println("다시 돌아온 VO in Controller" + Guestvo);
				
			JasonResult jasonResult = new JasonResult();
			jasonResult.success(Guestvo);
			
		return jasonResult;
	}
}
