package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {
    void insertCart(CartVO cartVO);

    List<CartViewVO> selectCartList(String MemberId);
    //삭쩨
    void deleteCart(CartVO cartVO);

    //장바구니 상품 수량 변경
    void updateCartCnt(CartVO cartVO);
    //이거 무조건 바뀐다.
    int iNeedTotalPrice(CartVO cartVO);
    //선택 삭제
    void choiceDelete(int[] cartCodes);

    void choiceDelete1(CartVO cartVO);

    //장바구니에 담긴 상품 구매를 위한 장바구니 목록 조회
    List<CartViewVO> selectCartListForBuy(CartVO cartVO);

    //장바구니에 동일한 상품이 담겨있는지 확인
    //int checkCart(CartVO cartVO);

    //상품 구매 개수 추가
    //void addCartCnt(CartVO cartVO);
}
