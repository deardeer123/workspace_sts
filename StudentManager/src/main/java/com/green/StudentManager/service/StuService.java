package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {
    //학생 등록
    //select 리턴 : 매법 바뀜.
    //insert, delete, update : void, 혹은 int(정석)

    //리턴타입 ㅣ 쿼리 실행 결과를 받아옴
    //insert, update, delete 쿼리실행 결과가
    //명채의 행이 삽입,삭제,수정 되었는지를 보여줌
    // 그래서 결과 리턴은 무조건 int
    int insertStu(StuVO stuVO);
    List<StuVO> selectStuList();
    StuVO selectStu(int stuNo);

    void deleteStu(int stuNo);


}
