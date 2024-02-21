package com.green.Second.controller;

import com.sun.tools.javac.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@controller : 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring에서 인식!
@Controller
public class MemberController {
    //@GetMapping, @PostMapping
    //페이지 접속 정보
    //소괄호안의 글자와 localhost:8081 뒤의 글자가 일치하면
    //해당 메소드를 실행.
    //@PostMapping은 페이지 이동 방법 중에
    //form 태그로 이동하면서 form 태그의 method 속성값이
    //post일때만 실행됨.
    //get방식 : form태그의 메소드 속성값이 get일때,
    //a태그로 페이지가 이동될때. 주소창에 직접 url를 입력했을때


    @GetMapping("/")
    public String main(){
        //리턴되는 문자열은 마지막에 실행되는 html 파일명 의미.
        //html 파일은 templates 폴더 안에 존재해야함.
        return "first"; //first.html 실행.
    }
    //@RequestParam 어노테이션으로 html에서 데이터를 전달받을 수 있음.
    //해당 어노테이션 속성으로는 name, required, defaultValue가 있음
    //html로 데이터를 전달하기 위해서는 메소드 매개변수로 Model(interface)의 객체를 선언,
    @GetMapping("/second")
    public String second(@RequestParam(name = "addr",required = false, defaultValue="노숙자") String address,
                         @RequestParam(name = "age", required = false, defaultValue = "1") int age,
                         Model model){
        //required = false를 설정하면 입력값이 들어오지않아도 오류는 뜨지않지만
        //변수에 null 값이 들어가므로, null값이 들어가지 못하는 자료형은 조심해야함.
        //defaultValue를 설정하면 입력값이 들어오지만 않으면 기본설정값을 받음.
        System.out.println(address);
        System.out.println(age);

        //html로 데이터 전달하기
        //model.addAttribute("넘어가는 데이터의 이름", 넘겨주는 데이터);
        model.addAttribute("address", address);
        model.addAttribute("name", "홍길동");
        model.addAttribute("age", age);

        return "second";
    }

}
