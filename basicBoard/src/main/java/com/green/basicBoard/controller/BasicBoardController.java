package com.green.basicBoard.controller;

import com.green.basicBoard.service.BasicBoardService;
import com.green.basicBoard.vo.BasicBoardVO;
import com.green.basicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.ldap.BasicControl;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasicBoardController {
    @Resource(name = "boardService")
    private BasicBoardService basicBoardService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/test1")
    public String test1(Model model){
        System.out.println("test1 실행");
        model.addAttribute("age", 30);
        model.addAttribute("name", "hong");
        model.addAttribute("member", new MemberVO());

        return "/board_list";
    }

    @GetMapping("/test2")
    public String test2(){
        System.out.println("test2 실행");
        return "/board_list";
    }

    @GetMapping("/test3")
    public String test3(){
        System.out.println("test3 실행");
        return "/board_list";
    }

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

        //encode = 매개변수로 전달된 문자열을 암호화
        String s1 = encoder.encode("java");
        System.out.println(encoder.encode("java"));

        System.out.println(encoder.matches("java", s1));
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

    @GetMapping("/admin")
    public String admin(){
        return "/admin";
    }
    @GetMapping("/manager")
    public String manager(){
        return "/manager";
    }

    @GetMapping("/deny")
    public String deny(){
        return  "/deny";
    }

    @GetMapping("/sample")
    public String sample(){
        return "/security_sample";
    }

    //로그인 정보 받아오기
    @GetMapping("/sec")
    public String securitySample(Authentication authentication){

        //1. 로그인 정보를 받아오기 위해서 매개변수에 Authentication 자료형 추가
//        2. 로그인 정보 받아오기
        User user =(User)authentication.getPrincipal();

        //로그인한 회원의 아이디,비번, 권한ㅏ[
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        List<GrantedAuthority> list = new ArrayList<>(user.getAuthorities());
        for(GrantedAuthority e : list){
            System.out.println(e);
        }
        return "redirect:/";
    }
}
