package com.celestegreen.chatTest.user.controller;

import com.celestegreen.chatTest.menu.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name="menuService")
    MenuService menuService;
    @GetMapping("/home")
    public String userHome(Model model){
        //사용자 페이지

        //사용사 사이드메뉴
        String menuType = "userSide";
        System.out.println(menuService.selectMenuList(menuType));
        model.addAttribute("sideMenuList", menuService.selectMenuList(menuType));

        //처음들어갈때는 첫번째 사이드 메뉴를 선택
        int selectMenuIndex = 1;
        model.addAttribute("selectMenuIndex",selectMenuIndex);

        return "content/user/user_test1";
    }
}
