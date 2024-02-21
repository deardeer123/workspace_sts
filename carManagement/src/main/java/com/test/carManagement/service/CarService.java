package com.test.carManagement.service;

import com.test.carManagement.vo.CarVO;
import com.test.carManagement.vo.SalesVO;

import java.util.List;

public interface CarService {
    //등록된 차량 찾기
    List<CarVO> selectCarList();
    //차량 등록하기
    void insertCar(CarVO carVO);
    //판매정보 등록하기
    void insertSaleInfo(SalesVO salesVO);
    //판매정보 확인하기
    List<SalesVO> selectSalesList();
}
