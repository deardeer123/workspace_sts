package com.example.FetchStudent.service;

import com.example.FetchStudent.vo.ClassVO;
import com.example.FetchStudent.vo.StuScoreVO;
import com.example.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuServiceMariaDB")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public List<ClassVO> selectClassList() {
        return sqlSession.selectList("stuMapper.selectClassList");
    }

    @Override
    public List<StuVO> selectStuList(ClassVO classVO) {
        return sqlSession.selectList("stuMapper.selectStuList", classVO);
    }

    @Override
    public StuVO selectStu(StuVO stuVO) {
        return sqlSession.selectOne("stuMapper.selectStu", stuVO);
    }

    @Override
    public StuScoreVO selectStuScore(StuVO stuVO) {
        return sqlSession.selectOne("stuMapper.selectScore",stuVO);
    }

    @Override
    public void insertStuScore(StuVO stuVO) {
        sqlSession.insert("stuMapper.insertStuScore",stuVO);
    }

    @Override
    public void updateStuScore(StuScoreVO stuScoreVO) {
        sqlSession.update("stuMapper.updateScore",stuScoreVO);
    }

    @Override
    public void insertScore(StuScoreVO stuScoreVO) {
        sqlSession.insert("stuMapper.insertScore", stuScoreVO);
    }
}
