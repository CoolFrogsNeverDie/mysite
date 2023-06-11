package com.javaex.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping(value ="/fileupload")
public class FileUploadController {
	
	@Autowired
	FileUploadService fileUploadService;
	
	
	
	@RequestMapping(value ="/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() { 
		System.out.println("Form()");
		return "fileupload/form";
	}
	
	
	
	@RequestMapping(value ="/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileUpload(@RequestParam("file") MultipartFile file, Model model) { 
		System.out.println("upload()");
		System.out.println(file.toString());
		System.out.println(file.getOriginalFilename());
		 
		
		String saveName = fileUploadService.restore(file);
		model.addAttribute("saveName", saveName);

		
		return "fileupload/result";
	}
	
	
	
}
