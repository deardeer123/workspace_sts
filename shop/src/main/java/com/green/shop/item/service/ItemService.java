package com.green.shop.item.service;

import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    //카테고리
    List<ItemVO> selectCateList();
    List<ItemVO> selectCateList2();
    //상품목록
    List<ItemVO> selcetItemList();
    //상품 상세보기
    ItemVO itemDetail(int itemCode);

    List<ItemVO> testItem();

    ItemImageVO selectImg(int imageCode);

//    //상품 목록(안쓸듯)
//    List<ItemImageVO> selectMainItemImageList();
}
