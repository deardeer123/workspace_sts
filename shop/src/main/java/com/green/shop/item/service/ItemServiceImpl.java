package com.green.shop.item.service;

import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public List<ItemVO> selectCateList() {

        return sqlSession.selectList("itemMapper.cateList");
    }

    @Override
    public List<ItemVO> selectCateList2() {

        return sqlSession.selectList("itemMapper.cateList2");
    }


    @Override
    public List<ItemVO> selcetItemList() {

        return sqlSession.selectList("itemMapper.itemList");
    }

    @Override
    public ItemVO itemDetail(int itemCode) {

        return sqlSession.selectOne("itemMapper.itemDetail", itemCode);
    }
    //테스트 입니다.
    @Override
    public List<ItemVO> testItem() {
        return sqlSession.selectList("itemMapper.testItem");
    }

    @Override
    public ItemImageVO selectImg(int imageCode) {
        return sqlSession.selectOne("itemMapper.selectImg",imageCode);
    }

//    @Override
//    public List<ItemImageVO> selectMainItemImageList() {
//        return sqlSession.selectList("itemMapper.mainImageList");
//    }

}

