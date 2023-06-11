package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.JasonResult;
import com.javaex.vo.UploadFileVO;
import com.javaex.vo.UserVO;

@RequestMapping(value ="/gallery")
@Controller
public class GalleryController {
	
	@Autowired
	GalleryService galleryService; 
	
	// --------------------------갤러리 사진 리스트 -----------------------------------
	
	@RequestMapping(value ="/list", method = { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {  
		System.out.println("갤러리 리스트 test");
		
		List<UploadFileVO> list = galleryService.getPhotoList();
		
		System.out.println("contoroller에 오는 list 내역" + list.toString());
		
		model.addAttribute("list", list);
		
		
		return "gallery/list";
	}
	
	
	//--------------------------갤러리 사진 삭제 -----------------------------------
	
	@ResponseBody
	@RequestMapping(value ="/delete", method = { RequestMethod.GET, RequestMethod.POST})
	public JasonResult delete(@ModelAttribute UploadFileVO uploadFileVO) {  
		
			
		System.out.println("삭제할 file 정보" + uploadFileVO.toString());
		int row = galleryService.deletePost(uploadFileVO);
		System.out.println("삭제 성공했다." + row);
		JasonResult jasonResult = new JasonResult();
		jasonResult.success(row);
		
		return jasonResult;
	}
	
	
	//-------------------갤러리 파일 업로드 메서드------------------------------
	
	
	@RequestMapping(value ="/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String galleryUpload(@RequestParam("uploadPicture") MultipartFile file,
								@ModelAttribute UploadFileVO uploadFileVO) {
		
		
		int row = galleryService.uploadPic(file,uploadFileVO);
		System.out.println("다시 controller로 넘어온 row" + row);
		
		return "redirect:/gallery/list";
	}
}
