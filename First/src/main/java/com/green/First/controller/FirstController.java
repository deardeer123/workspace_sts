package com.green.First.controller;

import com.green.First.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//어노테이션 anothation
//관제탑
public class FirstController {
    @GetMapping("main")
    public String main(){
        return "first";
    }
    @GetMapping("second")
    //@RequestParam http에서 넘어오는 데이터를 받을때 사용
    public String second(@RequestParam(name="name") String name,
                         @RequestParam(name="age") int age){
        System.out.println("@@@@@@@@@@@@@" + name);
        System.out.println("@@@@@@@@@@@@@" + age);
        return "abc";
    }
    @GetMapping("third")
    //넘어오는 데이터의 이름과 동일한 변수를 가진 클래스로 객체로 데이터를 받을 수 있다.
    public String third(MemberVO memberVO, Model model){
        System.out.println(memberVO.toString());
        //데이터를 받았으니 데이터를 보내주어야함
        //model.addAttribute("member", memberVO);
        //memberVO 클래스를 "member" 이름으로 보냄
        model.addAttribute("score", 80);

        return "abc";
    }

}
