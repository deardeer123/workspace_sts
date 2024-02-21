package com.green.shop.member.controller;

import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    //회원가입
    @PostMapping("/join")
    public String joinMember(MemberVO memberVO){
        //문자열 치환 확인
        //System.out.println(memberVO.getMemberEmail().replace(",",""));
        //System.out.println(memberVO.getMemberTel().replace(",","-"));
        //변경하기 
        
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",",""));
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",","-"));
        //다시 확인
        System.out.println(memberVO);
        //쿼리 실행!!
        memberService.join(memberVO);
        return "redirect:/item/list";
    }
    @GetMapping("/login")
    public String loginForm(){

        return "content/member/login";
    }
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        System.out.println(memberVO);
        System.out.println(memberService.login(memberVO));
        MemberVO memberVO1 = memberService.login(memberVO);
        if(memberVO1 == null){
            System.out.println("없음");
        }else{
            //model.addAttribute("login", "success");
            System.out.println("있네요");
            session.setAttribute("memberInfo",memberVO1);
        }
        return "content/member/login_result";
    }

    //비동기 로그인
    @ResponseBody
    //비동기통신을 위한 어노테이션 (페이지 이동x)

    @PostMapping("/loginFetch")
    public String loginFetch(MemberVO memberVO, HttpSession session){

        //확인
        System.out.println(memberVO);
        MemberVO memberVO1 = memberService.login(memberVO);
        if(memberVO1 == null){
            System.out.println("정보 없어요");
            memberVO.setMemberId("");
            return memberVO.getMemberId();

        }else{
            System.out.println("정보 있어요");

            session.setAttribute("memberInfo",memberVO1);
            return memberVO1.getMemberId();
        }
    }
    //logout
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("memberInfo");
        return "redirect:/item/list";
    }
}
