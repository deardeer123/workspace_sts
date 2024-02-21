package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;

import java.util.List;

public interface StudentService {

    //인터페이스는 메소드 정의 하는곳. 기능 정의
    //어떤 기능이 있는지 어떻게 구현할지는 구현XXXX
    //학생 한 명을 저장하는 기능
    public void insertStudent();
    //학생 한 명을 삭제하는 기능
    public void deleteStudent();
    //입력받은 값으로 학생 삭제.
    void delete(int stuNo);
    //학번이 1번인 학생 이름 조회
    String selectName();
    //학번이 1번인 학생의 점수 조회
    int selectScore();
    //학번이 1번인 학생 조회
    StudentVO selectStu();
    //모든 학생 정보 조회
    List<StudentVO> selectStuList();
}
