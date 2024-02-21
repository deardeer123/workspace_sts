package com.celestegreen.chatTest.menu.service;

import com.celestegreen.chatTest.menu.vo.MenuVO;

import jakarta.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    SqlSessionTemplate sqlSession;


    @Override
    public int selectMenuIndex(String pageName) {
        return sqlSession.selectOne("menuMapper.selectMenuIndex", pageName);
    }

    @Override
    public List<MenuVO> selectMenuList(String menuType) {
        return sqlSession.selectList("menuMapper.selectUserSideMenuList", menuType);
    }


    @Override
    public List<MenuVO> selectHeaderMenuList() {
        //헤더 메뉴 찾기

        return sqlSession.selectList("menuMapper.selectHeaderMenuList");
    }
}
