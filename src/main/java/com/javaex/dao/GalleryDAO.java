package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UploadFileVO;

@Repository
public class GalleryDAO {

	@Autowired
	SqlSession sqlSession;

	
//------------------------갤러리 사진 리스트------------------------------
	
	public List<UploadFileVO> getGalleryList() {

		List<UploadFileVO> list = sqlSession.selectList("gallery.getGalleryList");

		return list;
	}

	
	
	
//------------------------갤러리 사진 삭제------------------------------

	public int deletePost(UploadFileVO uploadFileVO) {

		int row = sqlSession.delete("gallery.deletePost", uploadFileVO);
		return row;
	}

	
	
	
//------------------------갤러리 사진 등록------------------------------

	public int insertFile(UploadFileVO uploadFileVO) {

		System.out.println("insertFile in DAO");
		int row = sqlSession.insert("gallery.insertPhoto", uploadFileVO);

		System.out.println("DAO sqlSession 성공여부 : " + row);

		return row;
	}

}
