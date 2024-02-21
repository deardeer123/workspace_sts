package com.example.FetchStudent.service;

import com.example.FetchStudent.vo.ClassVO;
import com.example.FetchStudent.vo.StuScoreVO;
import com.example.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {
    //학급 목록 조회
    List<ClassVO> selectClassList();
    //학생 목록 조회
    List<StuVO> selectStuList(ClassVO classVO);
    StuVO selectStu(StuVO stuVO);
    StuScoreVO selectStuScore(StuVO stuVO);
    //성적 상세
    void insertStuScore(StuVO stuVO);
    void updateStuScore(StuScoreVO stuScoreVO);
    void insertScore(StuScoreVO stuScoreVO);
}
