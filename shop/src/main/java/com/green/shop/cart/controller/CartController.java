package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartService;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name="cartService")
    private CartServiceImpl cartService;


    @ResponseBody
    @PostMapping("/insertCart")
    public void goCart(CartVO cartVO, HttpSession session){
        //장바구니에 물건 담기!!!!

        MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
        System.out.println(memberVO.getMemberId());
        System.out.println("-----------------------------------------");
        cartVO.setMemberId(memberVO.getMemberId());
        System.out.println(cartVO);
        //System.out.println(cartService.checkCart(cartVO)+"-------------------------------------------------");
        cartService.insertCart(cartVO);

        //        if(cartService.checkCart(cartVO)>0){
//            cartService.addCartCnt(cartVO);
//        }else{
//            cartService.insertCart(cartVO);
//        }

    }
    @GetMapping("/list")
    public String cartList(Model model, HttpSession session
                            ,@RequestParam(name = "page", required = false, defaultValue = "cartList") String page){
        //사이드 메뉴에 active 속성을 추가하기 위해서 page 데이터를 받고 다시 보냄!!
        //String page1 = "cartList";
        model.addAttribute("page", page);
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        //장바구니 목록조회
        int sum = 0;
        for(CartViewVO e : cartService.selectCartList(memberVO.getMemberId())){
            System.out.println(e);
            sum = sum + e.getTotalPrice();
        }
        model.addAttribute("cartViewList", cartService.selectCartList(memberVO.getMemberId()));
        System.out.println(cartService.selectCartList(memberVO.getMemberId()));
        model.addAttribute("finalPrice", sum);
        return "content/cart/cart_list";
    }
    @GetMapping("/delete")
    public String cartDelete(CartVO cartVO){
        System.out.println(cartVO);
        cartService.deleteCart(cartVO);
        return "redirect:/cart/list";
    }
    @ResponseBody
    @PostMapping("/changeCartCnt")
    public int changeCartCnt(CartVO cartVO){
        System.out.println(cartVO);
        //수량 바꾸고
        cartService.updateCartCnt(cartVO);
        //총가격만 필요해요
        return cartService.iNeedTotalPrice(cartVO);
    }

    @GetMapping("/choiceDelete")
    public String choiceDelete(@RequestParam(name="cartCodes")String cartCodes){


        String cartCodes1 = cartCodes.replaceFirst(",","");

        String[] strArr = cartCodes1.split(",");

        for(String e : strArr){
            System.out.println(e);
        }
        int[] arr = new int[strArr.length];

        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(strArr[i]);
        }
        System.out.println("-------------------------------------");
        for(int e : arr){
            System.out.println(e);
        }

        cartService.choiceDelete(arr);

        return "redirect:/cart/list";
    }

    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO){
        System.out.println(cartVO.getCartCodeList());
        for(int e : cartVO.getCartCodeList()){
            System.out.println(e);
        }
        cartService.choiceDelete1(cartVO);
        return  "redirect:/cart/list";
    }
}
