package com.celestegreen.chatTest.menu.controller;

import com.celestegreen.chatTest.menu.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @Resource(name="menuService")
    MenuService menuService;

    @GetMapping("/")
    public String goMain(){
        return "content/home";
    }

    @GetMapping("/test1")
    public String test1(){
        //메뉴1
        System.out.println("메뉴1");
        return "content/test/test1";
    }
    @GetMapping("/test2")
    public String test2(){
        //메뉴2
        System.out.println("메뉴2");
        return "content/test/test2";
    }
    @GetMapping("/test3")
    public String test3(){
        //메뉴3
        System.out.println("메뉴3");
        return "content/test/test3";
    }

}
