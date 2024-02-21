package com.green.shop.admin.service;


import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {
    //상품 등록 + 이미지 등록이애요
    void regItem(ItemVO itemVO);
    //없앨거 같은거
    int selectNextItemCode();
    //void regImage(List<ItemImageVO> itemImageVOList);


//    //상품이미지 등록
//    void insertImgs(ItemVO itemVO);

    //일단여기에 작성
    //관리자용 구매목록 확인
    List<BuyVO> selectAdminBuyList(SearchVO searchVO);
    //관리자용 구매목록 조회
    List<BuyVO> selectBuyList(SearchVO searchVO);
    List<BuyVO> selectBuyList2(SearchVO searchVO);

    //딸깍하면 구매상세내역 조회
    List<BuyVO> selectBuyDetailList(int buyCode);
    //조건검색
    List<BuyVO> searchAdminBuyList(SearchVO searchVO);
    //구매 상세 정보 조회 바꾼거
    BuyVO selectBuyDetail(int buyCode);

    //어차피 바뀔거 같지만 일단 여기에
    List<ItemVO> searchingForItemForUpdate();
    //바뀔거 2
    ItemVO searchingForItemForUpdate2(int itemCode);


    //상품 정보 변경 화면에서 상품 목록 조회
    List<ItemVO> selectItemList();
    //상품 상세 조회
    ItemVO selectItemDetail(int itemCode);

    //상품 상세 변경 바꿀듯
    void updateItemDetail(ItemVO itemVO);

    //새로 만든거 상품 정보 변경
//    void updateItem(ItemVO itemVO);
}
