package com.javaex.vo;


public class PagingVO {
	
	private int selectPage; //선택된 페이지
	private int startPageNum; // 보여질 시작 페이지 넘버
	private int endPageNum; // 보여질 마지막 페이지 넘버
	private int totalCnt; //총 저장된 글의 수
	private final int VIEW_PAGE = 5; //한 페이지당 노출될 글의 수
	private int finalPage; //마지막 페이지
	private int boardNumStart; //해당 페이지에서 보여질 게시글의 시작 rownum
	private int boardNumEnd; //해당 페이지에서 보여질 게시글의 마지막 rownum
	private int cntPageNum = 10; // 보여질 인덱스 넘버의 수
	
	public PagingVO() {}

	public PagingVO(int selectPage, int totalCnt) {
		this.selectPage = selectPage;
		this.totalCnt = totalCnt;
		//lastPage
		calcFinalPage(totalCnt, this.cntPageNum);
		calcStartEndPage(selectPage,this.cntPageNum);
		calcStartEnd(selectPage);
	}
	
	//제일 마지막 페이지
	public void calcFinalPage(int totalCnt, int cntPageNum) {   
		this.finalPage =  ((int)Math.ceil((double)totalCnt/VIEW_PAGE));
	}
	
	public void calcStartEndPage(int selectPage, int cntPageNum) {  
		this.endPageNum = ((int)Math.ceil((double)selectPage/cntPageNum)) * cntPageNum;
		if(this.endPageNum > this.finalPage) { 
			this.endPageNum = this.finalPage;
		}
		this.startPageNum = this.endPageNum - cntPageNum + 1;
		if(this.startPageNum < 1) { 
			this.startPageNum = 1;
		}
	}
	
	public void calcStartEnd(int selectPage) {  
		this.boardNumEnd = selectPage * this.VIEW_PAGE;
		this.boardNumStart = this.boardNumEnd - this.VIEW_PAGE + 1;
	}

	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getFinalPage() {
		return finalPage;
	}

	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}

	public int getBoardNumStart() {
		return boardNumStart;
	}

	public void setBoardNumStart(int boardNumStart) {
		this.boardNumStart = boardNumStart;
	}

	public int getBoardNumEnd() {
		return boardNumEnd;
	}

	public void setBoardNumEnd(int boardNumEnd) {
		this.boardNumEnd = boardNumEnd;
	}

	public int getCntPageNum() {
		return cntPageNum;
	}

	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}

	public int getVIEW_PAGE() {
		return VIEW_PAGE;
	}

	@Override
	public String toString() {
		return "PagingVO [selectPage=" + selectPage + ", startPageNum=" + startPageNum + ", endPageNum=" + endPageNum
				+ ", totalCnt=" + totalCnt + ", VIEW_PAGE=" + VIEW_PAGE + ", finalPage=" + finalPage
				+ ", boardNumStart=" + boardNumStart + ", boardNumEnd=" + boardNumEnd + ", cntPageNum=" + cntPageNum
				+ "]";
	}

	
	
}
