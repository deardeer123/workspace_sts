package com.green.Board2.controller;

import com.green.Board2.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {
    @GetMapping("/page1")
    public String page1(HttpSession session){
        //세션에 데이터 저장하기
        session.setAttribute("name", "java");
        session.setAttribute("age", 20);
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberName("이름");
        session.setAttribute("member", memberVO);
        //세션 유지시간 설정 :초단위
        session.setMaxInactiveInterval(3);
        System.out.println("페이지1로 넘어가요");

        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인
        String name = (String)session.getAttribute("name");
        System.out.println((String)session.getAttribute("name"));
        int a = (Integer)session.getAttribute("age");
        MemberVO memberVO = (MemberVO)session.getAttribute("member");

        System.out.println("페이지2로 넘어감");
        session.removeAttribute("name");
        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        System.out.println("페이지3");
        return "page3";
    }
}
