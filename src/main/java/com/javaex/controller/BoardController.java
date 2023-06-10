package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	
	//--------------BoardList----------아무것도 선택하지 않았을 때의 BoardList 삭제 예정
	
	@RequestMapping(value ="/list" )
	public String boardList(Model model) {  
		System.out.println("boardList controller");
		
		List <BoardVO> list = boardService.getBoardList();
		model.addAttribute("boardList", list);
		return "board/list";
	}
	
	//--------------BoardListByPageNum --------페이지 선택 시 보이는 BoardList
	
	@RequestMapping(value = "/list/{no}")
	public String boardListByBoardNum(@PathVariable("no") int selectPage, Model model) {  
		
		//BoardList와 PagingVO 객체를 꺼내올 거임
		Map<String, Object> pageInfo = boardService.getBoardPagingInfo(selectPage);
		model.addAttribute("pagingInfo", pageInfo.get("pagingVO"));
		model.addAttribute("boardList", pageInfo.get("BoardList"));
		
		return "board/list";
	}
	
	//--------------------searchBoardByKeywordByPageNum(no searchOption)------------ 
	
	@RequestMapping(value = "/search")
	public String searchBoard(@RequestParam("keyword") String keyword, Model model){ 
		System.out.println("검색 :" + keyword);
		List<BoardVO> searchBoard = boardService.searchBoard(keyword);
		model.addAttribute("boardList", searchBoard);
		return "board/list/1";
	}

	//--------------readBoard(목록에서 Board 하나 골라서 선택)-------------------
	
	@RequestMapping(value = "/readBoard/{no}")
	public String readBoard(@PathVariable("no") int no, Model model) {  
		//조회수 올리는 거랑
		//getBoardInfo해오는거 필요함
		System.out.println(no);
		BoardVO selectBoard = boardService.boardClickAction(no);
		model.addAttribute("readBoard", selectBoard);
		return "board/read";
	}
	
	//-------------Board ModifyForm----------------BoardNo 으로 정보 땡겨오기
	
	@RequestMapping(value = "/modifyForm/{boardNo}")
	public String modifyForm(@PathVariable("boardNo") int boardNo, Model model, HttpSession session) {  
		UserVO userVO = (UserVO) session.getAttribute("authUser");
		BoardVO boardVO = boardService.getModifyBoard(boardNo);
		String url  ="redirect:/board/list";
		
		if(userVO != null) { 
			if(boardVO.getUserNo() == userVO.getNo()) {   //로그인 유저 정보와 보드 유저넘버 맞으면 수정폼으로 이동
				model.addAttribute("modifyBoard", boardVO);
				url = "board/modifyForm";
			}
		}
		return url;
	}
	
	//-------------Board Update-------------------------

	@RequestMapping(value = "/modify")
	public String modify(@ModelAttribute BoardVO boardVO) {
		
		System.out.println(boardVO);
		boardService.modifyBoard(boardVO);
		return "redirect:/board/list";
	}
	
	//-----------Board InsertForm --------------------------
	
	@RequestMapping(value = "/writeForm")
	public String insertForm(HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("authUser");
		String url = "redirect:/board/list"; //로그인 안 되어 있으면 list 페이지로 이동
		
		if(userVO != null) { //로그인 되어 있으면 write 페이지 이동
		url = "board/writeForm";
		}
		return url;
	}
	
	//-----------Board Insert --------------------------//등록해야 함
	
	@RequestMapping(value = "/write")
	public String insert(HttpSession session,@ModelAttribute BoardVO boardVO) {
		UserVO userVO = (UserVO) session.getAttribute("authUser"); 
		boardVO.setUserNo(userVO.getNo());
		boardService.writeBoard(boardVO);
		System.out.println(boardVO);
		System.out.println("write Controller!");
		return "redirect:/board/list/1";
	}
	
	//------------Board delete--------------------------
	
	@RequestMapping(value = "/delete")
	public String delete(@ModelAttribute BoardVO boardVO, HttpSession session) {  
		System.out.println("delete controller : " + boardVO.getNo() + boardVO.getUserNo());
		UserVO authUserVO = (UserVO) session.getAttribute("authUser");
		
		if(authUserVO != null) { 
			if(authUserVO.getNo() == boardVO.getUserNo()) {   //로그인 유저 정보와 보드 유저넘버 맞으면 삭제 이동
				boardService.deleteBoard(boardVO.getNo());
			}
		}

		return "redirect:/board/list/1";
	}
	
	
}
