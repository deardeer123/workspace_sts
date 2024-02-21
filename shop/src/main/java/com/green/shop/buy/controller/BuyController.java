package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyService;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.buy.vo.TestVO;
import com.green.shop.cart.service.CartService;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {
    @Resource(name ="buyService")
    BuyService buyService;

    @Resource(name="cartService")
    CartService cartService;


    @GetMapping("/buyCarts")
    public String buyCarts(CartVO cartVO, HttpSession session){
        //구매 상세 테이블에 insert할 ITEM_CODE, BUY_CNT, TOTAL_PRICE 조회
        List<CartViewVO> list =  cartService.selectCartListForBuy(cartVO);

        //구매자 ID
        MemberVO memberVO1 = (MemberVO) session.getAttribute("memberInfo");
        System.out.println(memberVO1.getMemberId());

        //구매 총 가격 (BUY_PRICE)
        int buyPrice = 0;
        for(CartViewVO e : list){
            buyPrice = buyPrice + e.getTotalPrice();
        }
        System.out.println(buyPrice);

        //buycode 가져오기
        int buyCode = buyService.getBuyCode();
        BuyVO buyVO = new BuyVO();

        //가져온 buycode VO에 넣기
        buyVO.setBuyCode(buyService.getBuyCode());
        //세션에 있는 memberid값 vo에 넣기
        buyVO.setMemberId(memberVO1.getMemberId());
        //vo에 아까 구한 buyPrice(총가격 상품구매상세정보에 있는 총금액 다 더한거) 넣기
        buyVO.setBuyPrice(buyPrice);

        //buyVO에 있는 buyDetailVOList 객체 생성!!
        List<BuyDetailVO> buyDetailVOList = new ArrayList<>();


//        private int buyDetailCode; 필요없음 알아서 참
//        private int itemCode; item_code
//        private int buyCnt; 이건 cart_cnt
//        private int totalPrice; total_price
//        private int buyCode; 이건 따로 쿼리로 구할 수 있음

        for(CartViewVO e : list){
            //하나씩 넣어야지
            BuyDetailVO buyDetailVO = new BuyDetailVO();
            buyDetailVO.setBuyCode(buyService.getBuyCode()); //buycode
            buyDetailVO.setItemCode(e.getItemCode()); //itemcode
            buyDetailVO.setTotalPrice(e.getTotalPrice()); //totalprice
            buyDetailVO.setBuyCnt(e.getCartCnt()); //이건 수량 cart_cnt = buyCnt;
            buyDetailVOList.add(buyDetailVO); //만들어놓은 리스트에 집어넣기
        }
        //만들어놓은 상품구매상세정보 리스트를 buyVO에 넣기
        buyVO.setBuyDetailVOList(buyDetailVOList);

        for(BuyDetailVO e : buyDetailVOList){
            //확인
            System.out.println(e);
        }

        System.out.println("여기까진 옴?");
        buyService.insertBuy(buyVO, cartVO);

        //buyService.insertBuyDetail(buyVO);



        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/buyCarts1")
    public void buyCarts1(List<TestVO> testList){

        System.out.println(testList);

        for(TestVO e : testList){
            System.out.println(e);
        }

    }

    @PostMapping("/buycart")
    public String buyCart(BuyDetailVO buyDetailVO, HttpSession session){
        System.out.println(buyDetailVO);
        MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
        String memberId = memberVO.getMemberId();

        BuyVO buyVO = new BuyVO();
        List<BuyDetailVO> buyDetailVOList = new ArrayList<>();
        buyDetailVOList.add(buyDetailVO);
        buyVO.setBuyCode(buyService.getBuyCode());
        buyVO.setMemberId(memberId);
        buyVO.setBuyPrice(buyDetailVO.getTotalPrice());
        buyVO.setBuyDetailVOList(buyDetailVOList);

        buyService.insertBuy1(buyVO);

        return "redirect:/";
    }

    @PostMapping("/insertbuy")
    public String insertBuy(BuyDetailVO buyDetailVO, BuyVO buyVO, HttpSession session){

        int buyCode = buyService.getBuyCode();
        buyVO.setBuyCode(buyCode);

        MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
        String memberId = memberVO.getMemberId();
        buyVO.setMemberId(memberId);


        buyVO.setBuyPrice(buyDetailVO.getTotalPrice());


        buyService.insertBuy2(buyVO, buyDetailVO);
        return "redirect:/";
    }
    //구매이력페이지
    @GetMapping("/history")
    public String history(Model model, HttpSession session){
        //@RequestParam(name = "page", require=false, defaultValue = "history")
        //따로 설정하지 않으면 true!!!!
        //defaultValue = "history" 기본값으로 histoty 주겠다는 뜻임!!!
        String page = "history";
        MemberVO memberVO1 = (MemberVO)session.getAttribute("memberInfo");
        //buyVO.setMemberId(memberVO1.getMemberId());
        List<BuyVO> buyList = buyService.selectBuyList(memberVO1.getMemberId());
        System.out.println(buyList);
        for(BuyVO e : buyList){

            System.out.print("buyVO : ");
            System.out.println(e);
            System.out.println("-----------------------------------------------------");

            List<BuyDetailVO> buyDetailVOList = e.getBuyDetailVOList();
            for(BuyDetailVO e2 : buyDetailVOList){

                System.out.print("buyDetailVO : ");
                System.out.println(e2);

                ItemVO e3 = e2.getItemVO();
                System.out.print("itemVO : ");
                System.out.println(e3);
                List<ItemImageVO> e4 = e3.getItemImageList();
                for(ItemImageVO e5 : e4){
                    System.out.println(e5);
                }
                if(buyDetailVOList.size()!=1){
                    System.out.println();
                }

            }
            System.out.println("-----------------------------------------------------");
            System.out.println();
            //buyVOlist.get(1).getDetailVOList.get(2).getItemVO.getItemImageVOList.get(0).getAttachedFileName
        }
        System.out.println(buyList.size());

        model.addAttribute("page", page);
        model.addAttribute("buyList", buyList);
        return "content/buy/history";
    }
}
