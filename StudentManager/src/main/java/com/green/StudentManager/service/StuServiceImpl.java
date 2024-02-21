package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("aaa")
public class StuServiceImpl implements StuService{
    @Autowired
    SqlSessionTemplate sqlSession;
    @Override
    public int insertStu(StuVO stuVO) {
        return sqlSession.insert("insertStu", stuVO);
    }

    @Override
    public List<StuVO> selectStuList() {
        return sqlSession.selectList("selectStuList");
    }

    @Override
    public StuVO selectStu(int stuNo) {
        return sqlSession.selectOne("selectStu", stuNo);
    }

    @Override
    public void deleteStu(int stuNo) {
        sqlSession.delete("deleteStu",stuNo);
    }
}
