package com.green.basicBoard.controller;

import com.green.basicBoard.service.BasicBoardService;
import com.green.basicBoard.vo.BasicBoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.ldap.BasicControl;
import java.util.List;

@Controller
public class BasicBoardController {
    @Resource(name = "aaa")
    private BasicBoardService basicBoardService;
    @GetMapping("/")
    public String start(Model model){
        //목록 페이지
        List<BasicBoardVO> basicBoardVO = basicBoardService.selectBoardList();
        /*if(basicBoardVO.size()==0){
            BasicBoardVO basicBoardVO1 = new BasicBoardVO();
            basicBoardVO1.setWriter("게시물이 없음");
            basicBoardVO.add(basicBoardVO1);
        }*/
        model.addAttribute("boardList", basicBoardVO);
        return "board_list";
    }
    @PostMapping("/writeBoard")
    public String write2(BasicBoardVO basicBoardVO){
        //등록
        System.out.println(basicBoardVO);
        System.out.println("등록됬나요?");
        basicBoardService.insertBoard(basicBoardVO);
        return "redirect:/";
    }
    @GetMapping("/writeBoard")
    public String write(){
        System.out.println("등록하러 가는중");
        //작성하는곳으로 페이지 이동
        return "board_write_form";
    }
    @GetMapping("/infoBoard")
    public String infoBoard(BasicBoardVO basicBoardVO, Model model){
        //System.out.println(basicBoardVO.getBoardNum());

        BasicBoardVO board = basicBoardService.selectBoard(basicBoardVO.getBoardNum());
        basicBoardService.updateCnt(basicBoardVO.getBoardNum());
        System.out.println(board);
        System.out.println(board.getBoardNum());
        model.addAttribute("basicBoardVO",board);
        return "board_detail";
    }
    @GetMapping("/back")
    public String back(){
        return "redirect:/";
    }
    @GetMapping("/modifyBoard")
    public String modify(BasicBoardVO basicBoardVO, Model model){
        System.out.println(basicBoardService.selectBoard(basicBoardVO.getBoardNum()));
        model.addAttribute("basicBoardVO",basicBoardService.selectBoard(basicBoardVO.getBoardNum()));
        return "update_form";
    }
    @GetMapping("/modifyBoard2")
    public String modify2(BasicBoardVO basicBoardVO, Model model){
        BasicBoardVO basicBoardVO1 = basicBoardService.selectBoard(basicBoardVO.getBoardNum());
        basicBoardVO1.setBoardTitle(basicBoardVO.getBoardTitle());
        basicBoardVO1.setBoardContent(basicBoardVO.getBoardContent());
        basicBoardService.modifyboard(basicBoardVO1);
        //basicBoardService.modifyboard(basicBoardVO);
        //model.addAttribute("board", basicBoardVO);

        return "redirect:/infoBoard?boardNum=" + basicBoardVO1.getBoardNum();
    }

    @GetMapping("/deleteBoard")
    public String delete(BasicBoardVO basicBoardVO){
        basicBoardService.deleteboard(basicBoardVO.getBoardNum());
        return "redirect:/";
    }

}
