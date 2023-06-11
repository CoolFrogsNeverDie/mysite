package com.javaex.service;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.UploadFileVO;

@Service
public class FileUploadService {
		//파일 패스
		String saveDir ="C:\\javaStudy\\upload"; //---> 리눅스는 다름..위치 표시가
	
	public String restore(MultipartFile file) { 
		System.out.println("restore in service");
		System.out.println(file.getOriginalFilename());
		
		//원파일 이름--> 왜냐면 재다운로드 시 다운로드할 때의 이름으로 줘야하기 때문임..
		String orgName = file.getOriginalFilename();
		System.out.println("원파일 이름 : "+orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("확장자 이름 : "+exName);
		System.out.println(orgName.lastIndexOf("."));
		
		//저장파일 이름(관리하는 파일명//중복이 있으면 안 되니까 유니크해야 함 시간관련 + 랜덤숫자)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+ exName; //static으로 선언되어 있음 
		System.out.println("저장된 이름 : "+saveName);
		
		//파일 패스
		String filePath =saveDir + "\\" +saveName;
		System.out.println("파일 위치 :" + filePath);
		
		
		//파일 사이즈
		Long fileSize = file.getSize();
		System.out.println("파일 사이즈 : "+fileSize);
		
		//파일 업로드 (하드 디스크에 저장), getByte()는 Byte 배열임
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//DB에 저장(알아서)
		//-->

		
		
		return saveName;
	}
	
	
	
}
