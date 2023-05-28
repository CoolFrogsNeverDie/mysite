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
	private int oldPage; //이전 페이지 뷰로 이동
	private int nextPage; //다음 페이지 뷰로 이동
	
	public PagingVO() {
		super();
	}
	
	public PagingVO(int selectPage, int totalCnt) { 
		this.selectPage = selectPage;
		this.totalCnt = totalCnt;
		this.finalPage = calculPage(selectPage,totalCnt);
		this.BoardNumStart = getStartBoardNum(selectPage, totalCnt);
		this.BoardNumEnd = getEndBoardNum(selectPage,totalCnt);
		this.page = pageSize(selectPage);
		this.oldPage = getOldPage(pageSize(selectPage));
		this.nextPage = getNextPage(pageSize(selectPage),calculPage(selectPage,totalCnt));
	}
	
	
	//---------다음페이지 '>' 값 계산
	
	private int getNextPage(int[] array, int maxPage) { 
		int NextPage=0;
		for(int a : array) {
			if(NextPage < a) {
				NextPage=a;//배열에서 가장 큰 값 얻어오기
			}
			NextPage+=1;
		}
		if(NextPage > maxPage) { //마지막 페이지보다 다음페이지 값이 더 크면?
			NextPage= maxPage; //마지막 페이지값을 max에 할당함
		}
		return NextPage; //
	}
	
	//---------이전 페이지 '<' 값 계산
	
	private int getOldPage(int[] array) { 
		int oldPage=0;
		for(int a : array) {
			if(oldPage > a) {
				oldPage=a;
			}
		}
		if(oldPage<1) {
			oldPage = 2;
		}
		return oldPage-1;
	}
	
	
	//---------나열될 번호 인덱스 배열로 생성
	
	public int[] pageSize(int selectPage) { 
		int[] array = new int[10];
		
		if(selectPage <=10) { //선택한 페이지가 10 이하면 1~10까지
			for(int i = 0; i<10; i++)
			array[i] = i+1;
		}else { //선택한 페이지가 11이상이면~
			int listNum = ((selectPage/10)*10)+1;
			for(int i = 0; i<10; i++)
				array[i] = listNum++;			
		}
		return array;
	}
	
	//---------마지막 페이지 계산
	
	private int calculPage(int selectPage, int totalCnt) {
		int finalPage = (int)(Math.ceil((double)totalCnt/VIEW_PAGE));
		return finalPage;
	}
	
	//---------해당 페이지에서 시작되는 board의 rownum	
	
	private int getStartBoardNum(int selectPage, int totalCnt) {
		return totalCnt - (VIEW_PAGE *(selectPage -1));
	}
	
	//---------해당 페이지에서 끝나는 board의 rownum	

	private int getEndBoardNum(int selectPage, int totalCnt) { 
		int startPage = getStartBoardNum(selectPage, totalCnt);
		int endPage = startPage -4; //시작 페이지 -4
		if(endPage <0) { //지만~ endPage가 0이하면 rownum 1로 설정
			endPage = 1;
		}
		return endPage;
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
