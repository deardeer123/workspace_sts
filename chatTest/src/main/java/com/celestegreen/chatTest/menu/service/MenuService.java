package com.celestegreen.chatTest.menu.service;

import com.celestegreen.chatTest.menu.vo.MenuVO;

import java.util.List;

public interface MenuService {

    //메뉴 인덱스 번호 찾기
    int selectMenuIndex(String pageName);

    //메뉴 불러오기
    List<MenuVO> selectMenuList(String menuType);


    //헤더 메뉴 찾기
    List<MenuVO> selectHeaderMenuList();
}
