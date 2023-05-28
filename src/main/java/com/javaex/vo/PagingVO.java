package com.javaex.vo;

public class PagingVO {
	
	private int selectPage; //선택된 페이지
	private int totalCnt; //총 저장된 글의 수
	private int finalPage; //마지막 페이지
	private int BoardNumStart; //해당 페이지에서 보여질 게시글의 시작 rownum
	private int BoardNumEnd; //해당 페이지에서 보여질 게시글의 마지막 rownum
	private final int VIEW_PAGE = 5; //한 페이지당 노출될 글의 수
	
	public PagingVO() {
		super();
	}
	
	public PagingVO(int selectPage, int totalCnt) { 
		this.selectPage = selectPage;
		this.totalCnt = totalCnt;
		this.finalPage = calculPage(selectPage,totalCnt);
		this.BoardNumStart = getStartPage(selectPage);
		this.BoardNumEnd = getStartPage(selectPage)+4;
	}
	
	private int calculPage(int selectPage, int totalCnt) {
		int finalPage = (int)(Math.ceil((double)(totalCnt/VIEW_PAGE)));
		return finalPage;
	}
	
	private int getStartPage(int selectPage) {  
		return (selectPage - 1) * 5 + 1;
	}

	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
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

	public int getBoardNumstart() {
		return BoardNumStart;
	}

	public void setBoardNumstart(int boardNumstart) {
		BoardNumStart = boardNumstart;
	}

	public int getBoardNumEnd() {
		return BoardNumEnd;
	}

	public void setBoardNumEnd(int boardNumEnd) {
		BoardNumEnd = boardNumEnd;
	}

	@Override
	public String toString() {
		return "PagingVO [selectPage=" + selectPage + ", totalCnt=" + totalCnt + ", finalPage=" + finalPage
				+ ", BoardNumstart=" + BoardNumStart + ", BoardNumEnd=" + BoardNumEnd + ", VIEW_PAGE=" + VIEW_PAGE
				+ "]";
	}
	
	
}
