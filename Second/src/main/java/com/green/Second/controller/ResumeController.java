package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {
    @GetMapping("/r1")
    public String first(){
        return "resume_1";
    }
    @PostMapping("/r2")
    public String second(@RequestParam(name = "name")String name,
                         @RequestParam(name = "tel")String tel,
                         Model model){

        System.out.println(name);
        System.out.println(tel);

        model.addAttribute("name",name);
        model.addAttribute("tel",tel);

        return "resume_2";
    }
    //커맨더 객체 있으면 데이터 다받음
    @PostMapping("/r3")
    public String third(ResumeVO resumeVO){
        System.out.println(resumeVO);
        return "resume_3";
    }

}
