package com.celestegreen.chatTest.item.service;

import com.celestegreen.chatTest.item.vo.ItemCategoryVO;

import java.util.List;

public interface ItemService {
    List<ItemCategoryVO> selectItemCateList();
}
