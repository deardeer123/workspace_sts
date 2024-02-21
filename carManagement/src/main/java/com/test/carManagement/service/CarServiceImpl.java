package com.test.carManagement.service;

import com.test.carManagement.vo.CarVO;
import com.test.carManagement.vo.SalesVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("carService")
public class CarServiceImpl implements CarService{
    @Resource
    SqlSessionTemplate sqlSession;
    //등록되어 있는 자동차 검색
    @Override
    public List<CarVO> selectCarList() {
        return sqlSession.selectList("carMapper.selectCarList");
    }
    //차량 등록하기
    @Override
    public void insertCar(CarVO carVO) {
        sqlSession.insert("carMapper.insertCar", carVO);
    }

    //판매정보등록하기
    @Override
    public void insertSaleInfo(SalesVO salesVO) {
        sqlSession.insert("carMapper.insertSaleInfo",salesVO);
    }
    //판매정보확인하기
    @Override
    public List<SalesVO> selectSalesList() {
        return sqlSession.selectList("carMapper.selectSalesList");
    }
}
