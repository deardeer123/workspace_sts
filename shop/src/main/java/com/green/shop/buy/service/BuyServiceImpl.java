package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService {
    @Autowired
    SqlSessionTemplate sqlSession;


    @Override
    public int getBuyCode() {
        return sqlSession.selectOne("buyMapper.getBuyCode");
    }

   @Override
   @Transactional(rollbackFor = Exception.class)
    public void insertBuy(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuy",buyVO); //상품구매정보 넣기
        sqlSession.insert("buyMapper.insertBuyDetail",buyVO); // 상품구매상세정보 넣기
        sqlSession.delete("cartMapper.choiceDelete1",cartVO);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuy1(BuyVO buyVO) {
        sqlSession.insert("buyMapper.insertBuy",buyVO); //상품구매정보 넣기
        sqlSession.insert("buyMapper.insertBuyDetail",buyVO); // 상품구매상세정보 넣기
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuy2(BuyVO buyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail1", buyDetailVO);
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        //구매기록 리스트

        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }

//    @Override
//    public void insertBuyDetail(BuyVO buyVO) {
//
//    }



}
