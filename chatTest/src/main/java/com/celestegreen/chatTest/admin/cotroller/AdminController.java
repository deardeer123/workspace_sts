package com.celestegreen.chatTest.admin.cotroller;

import com.celestegreen.chatTest.item.service.ItemService;
import com.celestegreen.chatTest.menu.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name="menuService")
    MenuService menuService;
    @Resource(name="itemService")
    ItemService itemService;

    @GetMapping("/home")
    public String adminHome(Model model){
        //관리자 메뉴 불러오기위한 코드
        model.addAttribute("headerMenuList",menuService.selectMenuList("adminHeader"));

        //선택한 메뉴를 볼드 처리하고 싶어서 만든것
        //생각해보니깐 메뉴 바뀔수 도 있는게 이게맞나
        int selectMenuIndex = menuService.selectMenuIndex("home");
        System.out.println(selectMenuIndex);
        model.addAttribute("selectMenuIndex",selectMenuIndex);

        System.out.println("관리자 홈으로");
        return "content/admin/admin_home";
    }

    //상품
    @GetMapping("/item")
    public String addItem(Model model){
        //관리자 헤더메뉴 불러오기위한 코드
        model.addAttribute("headerMenuList",menuService.selectMenuList("adminHeader"));

        //상품의 사이드 메뉴를 불러오기 위한 코드
        model.addAttribute("sideMenuList",menuService.selectMenuList("adminSide"));

        //선택한 헤더 메뉴를 볼드 처리하고 싶어서 만든것
        int selectMenuIndex = menuService.selectMenuIndex("item");
        model.addAttribute("selectMenuIndex",selectMenuIndex);

        //선택한 사이드 메뉴를 활성화 시키기 위한 코드
        int selectSideMenuIndex = menuService.selectMenuIndex("addItem");
        model.addAttribute("selectSideMenuIndex", selectSideMenuIndex);

        System.out.println("상품 페이지로 이동");
        //갈때 일단 카테고리 목록 들고감
        model.addAttribute("cateList", itemService.selectItemCateList());
        return "content/admin/admin_addItem";
    }


    //판매현황
    @GetMapping("/salesStatus")
    public String salesState(Model model){
        //관리자 메뉴 불러오기위한 코드
        model.addAttribute("headerMenuList",menuService.selectMenuList("adminHeader"));

        //선택한 헤더 메뉴를 볼드 처리하고 싶어서 만든것
        int selectMenuIndex = menuService.selectMenuIndex("salesStatus");
        model.addAttribute("selectMenuIndex",selectMenuIndex);

        System.out.println("판매 현황 페이지로 변경");
        return "content/admin/admin_salesStatus";
    }
}
