package com.green.Board2.controller;

import com.green.Board2.service.MemberService;
import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") //GET매핑이랑 비슷한거
public class MemberController {
    @Resource(name = "memberServiceMariaDB")
    private MemberServiceImpl memberService;

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/list";
    }

    @GetMapping("/loginForm")
    public String loginForm(HttpSession session){
        session.invalidate();
        //로그인 페이지 이동
        return "login";
    }
    @GetMapping("/join")
    public  String join(){
        return "join";
        //회원가입하러감
    }

    @PostMapping("/join")
    public String joinMember(MemberVO memberVO){
        //회원가입 !!!!
        System.out.println(memberVO);
        memberService.joinMember(memberVO);
        return "redirect:/member/loginForm";
    }
    @PostMapping("/login")
    public String login(HttpSession session, MemberVO memberVO) {
        MemberVO memberInfo = memberService.login(memberVO);
        if(memberInfo != null){
            //로그인 정보가 조회가 됐으면 세션에 정보를 저장
            session.setAttribute("loginInfo",memberService.login(memberVO));
            System.out.println("@@@@@@@@@@@@@@@@@@@");
        }else{
            System.out.println("왜 못들고 오는데");
        }
        return "login_result";
    }


}
