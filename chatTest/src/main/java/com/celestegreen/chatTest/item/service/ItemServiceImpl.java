package com.celestegreen.chatTest.item.service;

import com.celestegreen.chatTest.item.vo.ItemCategoryVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{

    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public List<ItemCategoryVO> selectItemCateList() {
        return sqlSession.selectList("selectItemCateList");
    }
}
