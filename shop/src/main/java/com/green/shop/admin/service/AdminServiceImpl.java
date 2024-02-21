package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    @Transactional(rollbackFor = Exception.class)
    //이 어노테이션이 붙어 있는 메소드 내부의 쿼리 실행은
    //한꺼번에 됨 둘중 하나가 안되면 롤백함
    public void regItem(ItemVO itemVO) {
        //상품 등록 + 이미지 등록
        sqlSession.insert("adminMapper.regItem" , itemVO);
        sqlSession.insert("adminMapper.regImage", itemVO);
    }

    //다음 등록될 아이템코드 가져오기
    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    public List<BuyVO> selectAdminBuyList(SearchVO searchVO) {

        return sqlSession.selectList("buyMapper.selectAdminBuyList",searchVO);
    }

    //들어왔을떄 보여지는거
    @Override
    public List<BuyVO> selectBuyList(SearchVO searchVO) {

        return sqlSession.selectList("adminMapper.selectBuyList",searchVO);
    }

    @Override
    public List<BuyVO> selectBuyList2(SearchVO searchVO) {
        return sqlSession.selectList("adminMapper.selectBuyList2",searchVO);
    }

    //상세내역조회
    @Override
    public List<BuyVO> selectBuyDetailList(int buyCode) {
        return sqlSession.selectList("adminMapper.selectBuyDetailList", buyCode);
    }

    //새로 할 필요가 있나? 상세검색
    @Override
    public List<BuyVO> searchAdminBuyList(SearchVO searchVO) {
        return sqlSession.selectList("adminMapper.searchAdminBuyList",searchVO);
    }
    //상세내역조회2
    @Override
    public BuyVO selectBuyDetail(int buyCode) {
        return sqlSession.selectOne("adminMapper.selectBuyDetail",buyCode);
    }

    //어차피 바뀔거 같지만 일단 여기에/////////////////////////////////////////////
    @Override
    public List<ItemVO> searchingForItemForUpdate() {

        return sqlSession.selectList("adminMapper.searchingForItemForUpdate");
    }

    @Override
    public ItemVO searchingForItemForUpdate2(int itemCode) {
        return sqlSession.selectOne("adminMapper.searchingForItemForUpdate2",itemCode);
    }
    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("adminMapper.selectItemList");
    }

    @Override
    public ItemVO selectItemDetail(int itemCode) {
        return sqlSession.selectOne("adminMapper.selectItemDetail", itemCode);
    }

    @Override
    public void updateItemDetail(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateItemDetail", itemVO);
    }

//    @Override
//    public void updateItem(ItemVO itemVO) {
//        sqlSession.update("adminMapper.updateItem", itemVO);
//    }

//    @Override
//    public void regImage(List<ItemImageVO> itemImageVOList) {
//        sqlSession.insert("adminMapper.regImage", itemImageVOList);
//
//
//    }



}
