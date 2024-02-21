package com.green.Second.controller;

import com.green.Second.vo.TestVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @PostMapping("/test2")
    public String test2(@RequestParam(name = "name") String name,
                        @RequestParam(name = "tel") String tel, Model model){
        model.addAttribute("name", name);
        model.addAttribute("tel", tel);
        return "test2";
    }
    @PostMapping("/test3")
    public String test3(TestVO testVO,
                        Model model){
        System.out.println(testVO.getName());
        return "test3";
    }

}
