package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {
    //구매번호 구하기
    int getBuyCode();
    //상세 구매번호 넣기
    //void insertBuyDetail(BuyVO buyVO);
    //구매정보 넣기
    void insertBuy(BuyVO buyVO, CartVO cartVO);
    //하나 넣을때 쓰는거
    void insertBuy1(BuyVO buyVO);
    //하나 넣을때 쓰는거2
    void insertBuy2(BuyVO buyVO, BuyDetailVO buyDetailVO);

    //구매목록 조회(사용자용)
    //List<BuyVO> selectBuyList(BuyVO buyVO);
    List<BuyVO> selectBuyList(String memberId);
}
