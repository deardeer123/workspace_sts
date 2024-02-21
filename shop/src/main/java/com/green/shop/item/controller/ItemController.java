package com.green.shop.item.controller;

import com.green.shop.admin.service.AdminService;
import com.green.shop.item.service.ItemService;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.ConstantVariable;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name="itemService")
    private ItemService itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list1(Model model){
        //그냥 association 테스트
        for(ItemVO e : itemService.testItem()){
            System.out.println(e.getItemCategoryVO());
        }

        //상품 목록 조회
        System.out.println("왜 두번될까");
        model.addAttribute("itemList", itemService.selcetItemList());
        for(ItemVO itemVO : itemService.selcetItemList()){

            System.out.println(itemVO.getItemName() +" "+ itemVO.getItemImageList().get(0));
        }
        //model.addAttribute("mainImageList", itemService.selectMainItemImageList());
        //System.out.println(itemService.selcetItemList());
        //System.out.println(itemService.selcetItemList().get(0).getItemImageList().get(0).getAttachedFileName());
        return "content/item/item_list";
    }

    @GetMapping("/goDetail")
    public String goDetail(Model model, ItemVO itemVO){
        //상세보기
        System.out.println("왜 저거 위에꺼도 같이 될까");
        //System.out.println(itemVO.getItemCode());
        ItemVO itemVO1 = itemService.itemDetail(itemVO.getItemCode());
        System.out.println(itemVO1);
        System.out.println("------------------------------------------");
        System.out.println(itemVO1.getItemImageList().get(0));
        System.out.println("`````````````````````````````");
        System.out.println(itemVO1.getItemImageList().get(1));
        System.out.println("``````````````````````");
        model.addAttribute("itemVO",itemVO1);

        return "content/item/item_detail_1";
    }

}
