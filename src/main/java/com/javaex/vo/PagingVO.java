package com.javaex.vo;

import java.util.Arrays;

public class PagingVO {
	
	private int selectPage; //선택된 페이지
	private int totalCnt; //총 저장된 글의 수
	private int finalPage; //마지막 페이지
	private int BoardNumStart; //해당 페이지에서 보여질 게시글의 시작 rownum
	private int BoardNumEnd; //해당 페이지에서 보여질 게시글의 마지막 rownum
	private final int VIEW_PAGE = 5; //한 페이지당 노출될 글의 수
	private int[] page = new int[10]; //나열될 page Number 배열
	private int oldPage;
	private int nextPage;
	
	public PagingVO() {
		super();
	}
	
	public PagingVO(int selectPage, int totalCnt) { 
		this.selectPage = selectPage;
		this.totalCnt = totalCnt;
		this.finalPage = calculPage(selectPage,totalCnt);
		this.BoardNumStart = getStartPage(selectPage);
		this.BoardNumEnd = getStartPage(selectPage)+4;
		this.page = pageSize(selectPage);
		this.oldPage = min(pageSize(selectPage));
		this.nextPage = max(pageSize(selectPage));
	}
	
	
	private int max(int[] array) { 
		int max=0;
		for(int a : array) {
			if(max < a) {
				max=a;
			}
		}
		return max + 1;
	}
	
	private int min(int[] array) { 
		int min=0;
		for(int a : array) {
			if(min < a) {
				min=a;
			}
		}
		if(min<1) {
			min = 1;
		}
		return min-1;
	}
	
	public int[] pageSize(int selectPage) { 
		int[] array = new int[10];
		
		if(selectPage <=10) {
			for(int i = 0; i<10; i++)
			array[i] = i+1;
		}else { 
			int listNum = ((selectPage/10)*10)+1;
			for(int i = 0; i<10; i++)
				array[i] = listNum++;			
		}
		return array;
	}
	
	private int calculPage(int selectPage, int totalCnt) {
		int finalPage = (int)(Math.ceil((double)(totalCnt/VIEW_PAGE)+1));
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

	
	
	public int[] getPage() {
		return page;
	}

	public void setPage(int[] page) {
		this.page = page;
	}
	
	
	
	public int getOldPage() {
		return oldPage;
	}

	public void setOldPage(int oldPage) {
		this.oldPage = oldPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "PagingVO [selectPage=" + selectPage + ", totalCnt=" + totalCnt + ", finalPage=" + finalPage
				+ ", BoardNumStart=" + BoardNumStart + ", BoardNumEnd=" + BoardNumEnd + ", VIEW_PAGE=" + VIEW_PAGE
				+ ", page=" + Arrays.toString(page) + ", oldPage=" + oldPage + ", nextPage=" + nextPage + "]";
	}

	
	
}
