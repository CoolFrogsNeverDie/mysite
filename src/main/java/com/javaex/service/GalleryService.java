package com.javaex.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDAO;
import com.javaex.vo.UploadFileVO;

@Service
public class GalleryService {
	// 파일 패스
	String saveDir = "C:\\javaStudy\\upload";

	@Autowired
	GalleryDAO galleryDAO;

	// --------------------------갤러리 사진 리스트 -----------------------------------

	public List<UploadFileVO> getPhotoList() {

		List<UploadFileVO> list = galleryDAO.getGalleryList();
		System.out.println("list in service");

		return list;
	}

	// --------------------------갤러리 사진 삭제 -----------------------------------

	public int deletePost(UploadFileVO uploadFileVO) {

		System.out.println("delete in service");
		int row = galleryDAO.deletePost(uploadFileVO);

		return row;
	}

	// --------------------------갤러리 사진 업로드 -----------------------------------

	public int uploadPic(MultipartFile file,UploadFileVO uploadFileVO) {

		System.out.println("uploadPic in Service");
		System.out.println(file.getOriginalFilename());

		String orgName = file.getOriginalFilename();		// 원파일 이름
		String exName = orgName.substring(orgName.lastIndexOf("."));		// 확장자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;// 저장파일 이름
		String filePath = saveDir + "\\" + saveName;// 파일 패스
		Long fileSize = file.getSize();// 파일 사이즈

		// 파일 업로드 (하드 디스크에 저장), getByte()는 Byte 배열임
		try {
			file.transferTo(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		uploadFileVO.setFilePath(filePath);
		uploadFileVO.setOrgName(orgName);
		uploadFileVO.setSaveName(saveName);
		uploadFileVO.setFileSize(fileSize);
		
		int row = galleryDAO.insertFile(uploadFileVO);
		
		return row;
	}

}
