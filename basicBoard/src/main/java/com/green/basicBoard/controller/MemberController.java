package com.green.basicBoard.controller;

import com.green.basicBoard.service.BasicBoardService;
import com.green.basicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Resource(name = "boardService")
    private BasicBoardService basicBoardService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    //회원 가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){

        return "join";
    }
    //회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        String pw = encoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(pw);
        basicBoardService.join(memberVO);
        return "redirect:/loginForm";
    }
    // 로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(@RequestParam(name = "errorMsg", required = false, defaultValue = "success")String errorMsg, Model model){
        model.addAttribute("errorMsg", errorMsg);
        return "login";
    }
    //로그인
//    @PostMapping("/login")
//    public String login(HttpSession session, MemberVO memberVO){
//        MemberVO loginInfo = basicBoardService.login(memberVO);
//        if(loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//
//        return "redirect:/";
//    }
    //로그아웃
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute("loginInfo");
//
//        return "redirect:/";
//    }
}
