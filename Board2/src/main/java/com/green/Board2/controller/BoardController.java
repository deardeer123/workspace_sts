package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.ReplyService;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    //목록조회, 글쓰기, 상세조회, 수정, 삭제
    @Resource(name = "boardServiceMariaDB")
    BoardService boardService;

    @Resource(name="replyServiceMariDB")
    ReplyService replyService;

    @RequestMapping("/list")
    public String list(SearchVO searchVO, Model model){

        System.out.println(searchVO.getNowPage());


        //전체 데이터 수
        int totalDataCnt = boardService.selectBoardCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        //페이지 정보 세팅

        searchVO.setPageInfo();
        System.out.println(searchVO.getEndPage());
        System.out.println(searchVO.getTotalPageCnt());

        model.addAttribute("boardList",boardService.selectBoardList(searchVO));
        return "board_list";
    }
    @GetMapping("/write")
    public String goWriteForm(){
        return "board_write_form";
    }
    @PostMapping("/write")
    public String write(HttpSession session, BoardVO boardVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        boardVO.setWriter(loginInfo.getMemberID());
        boardService.insertBoard(boardVO);
        return "redirect:/board/list";
    }
    @GetMapping("/info")
    public String info(BoardVO boardVO, Model model){
        //상세보기
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardService.selectBoard(boardVO.getBoardNum()));
        boardService.updateCnt(boardVO.getBoardNum());
        model.addAttribute("board",boardService.selectBoard(boardVO.getBoardNum()));
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardVO.getBoardNum());
        System.out.println(boardVO.getBoardNum());
        //댓글도 불러와야함!!!
        model.addAttribute("replyList", replyService.selectReplyList(boardVO.getBoardNum()));

        return "board_detail";
    }
    @GetMapping("/modifyBoard")
    public String modifyBoard(BoardVO boardVO, Model model){
        model.addAttribute("board", boardService.selectBoard(boardVO.getBoardNum()));
        return "modify_board_form";
    }
    @PostMapping("/modifyBoard")
    public String modify(BoardVO boardVO, Model model){
        boardService.modifyBoard(boardVO);
        return "redirect:/board/info?boardNum="+boardVO.getBoardNum();
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/board/list";
    }


    @GetMapping("/searchTitle")
    public String searchBoard(BoardVO boardVO, Model model){
        model.addAttribute("boardList",boardService.searchTitle(boardVO.getTitle()));
        return "search_board";
    }
    @GetMapping("/searchWriter")
    public String searchWriter(BoardVO boardVO, Model model) {
        model.addAttribute("boardList", boardService.searchWriter(boardVO.getWriter()));
        return "search_board";
    }


}
