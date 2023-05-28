package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVO;


@Controller
@RequestMapping("/Guestbook")
public class GuestbookController {
	
	@Autowired
	GuestbookService guestbookService;
	
	//guestBook Form , getBoardList 해서 받아와야 함.
	@RequestMapping(value = "/guestbookForm", method = RequestMethod.GET)
	public String guestBookView(Model model) {  
		
		List<GuestbookVO> list = guestbookService.getGuestbooklist();
		model.addAttribute("guestbookList", list);
		return "guestbook/addList";
	}
	
	//guestBookWirte
	@RequestMapping(value = "/guestbookWrite", method = RequestMethod.GET)
	public String guestBookWrite(@ModelAttribute GuestbookVO guestbookVO ) {  
		System.out.println("guestBookWrite");
		if(guestbookVO != null) {  
			guestbookService.guestBookWrite(guestbookVO);
		}
		
		return "redirect:/Guestbook/guestbookForm";
	}

	//guestbookdelete, getBook 만들어서 password 가져오기
	@RequestMapping(value = "/guestbookDeleteForm/{num}")
	public String guestbookDeleteForm(@PathVariable("num") int num, Model model) {  
		model.addAttribute("deleteGuestbookNum", num);
		return "guestbook/deleteForm";
	}
	
	//guestBookDelete------!
	@RequestMapping(value = "/guestbookDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public String guestbookDelete(@ModelAttribute GuestbookVO guestbookVO ) {  
		guestbookService.deleteGuestbook(guestbookVO);
		return "redirect:/Guestbook/guestbookForm";
	}
}
