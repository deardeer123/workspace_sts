package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //장바구니에 물건 넣을때 만약에 동일 상품이 장바구니에 있을경우 그 상품의 수량을 변경
    //없으면 그냥 장바구니에 물건 추가
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCart(CartVO cartVO) {
        int check = sqlSession.selectOne("cartMapper.checkCart", cartVO);
        if(check >0){
            sqlSession.update("cartMapper.addCartCnt",cartVO);
        }else{
            sqlSession.insert("cartMapper.insertCart",cartVO);
        }
    }

    //장바구니 모록
    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartViewList", memberId);
    }

    //삭제
    @Override
    public void deleteCart(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteCart",cartVO);
    }

    //수량 변경
    @Override
    public void updateCartCnt(CartVO cartVO) {
        sqlSession.update("cartMapper.updateCartCnt",cartVO);
    }

    //바뀔듯
    @Override
    public int iNeedTotalPrice(CartVO cartVO) {
        return sqlSession.selectOne("cartMapper.iNeedTotalPrice",cartVO);
    }

    //선택삭쩨
    @Override
    public void choiceDelete(int[] cartCodes) {
        sqlSession.delete("cartMapper.choiceDelete",cartCodes);
    }

    @Override
    public void choiceDelete1(CartVO cartVO) {
        sqlSession.delete("cartMapper.choiceDelete1",cartVO);
    }

    //장바구니에 담긴 상품 구매를 위한 장바구니 목록 조회
    @Override
    public List<CartViewVO> selectCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectCartListForBuy", cartVO);
    }


//    //장바구니 확인
//    @Override
//    public int checkCart(CartVO cartVO) {
//        return sqlSession.selectOne("cartMapper.checkCart", cartVO);
//    }
//    //갯수 추가
//    @Override
//    public void addCartCnt(CartVO cartVO) {
//        sqlSession.update("cartMapper.addCartCnt",cartVO);
//    }


}
