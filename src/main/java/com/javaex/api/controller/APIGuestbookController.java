package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
	private GuestbookService guestbookService;
	
	//방명록 첫페이지(ajax)
	
	@RequestMapping(value = "/guestbookview2", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList2() {  
		
		System.out.println("오나요!");
		
		return "guestbook/addListAjax2";
	}
	
	//ajax 전체리스트 가져오기
	
	@ResponseBody
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public JasonResult list() {   
		
		List<GuestbookVO> list = guestbookService.getGuestbooklist();
		System.out.println("list in controller" + list);
		
		JasonResult jasonResult = new JasonResult();
		jasonResult.success(list);
		
		return jasonResult;
	}
	
	//jason으로 전송 후 등록
	@ResponseBody
	@RequestMapping(value = "/guestbookWrite2", method = {RequestMethod.GET, RequestMethod.POST})
	public JasonResult guestBookWriteAJAX2(@RequestBody GuestbookVO guestbookVO) {

		System.out.println("write2 in controller");
		System.out.println(guestbookVO);
		
		GuestbookVO Guestvo = guestbookService.addGuest(guestbookVO);
			
		JasonResult jasonResult = new JasonResult();
		jasonResult.success(Guestvo);
		
		
		return jasonResult;
	}
	
	
	
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
	
	
	//ajax 삭제
	
	@ResponseBody
	@RequestMapping(value = "/guestbookDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public JasonResult guestbookDelete (@ModelAttribute GuestbookVO guestbookVO) {  
		JasonResult jasonResult = new JasonResult();
		
		System.out.println(guestbookVO);

		int rows = guestbookService.deleteGuestbook(guestbookVO);

		jasonResult.success(rows);
		
		return jasonResult;
	}
	
	
}
