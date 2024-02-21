package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StudentSerivceImpl implements StudentService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertStudent() {
        //인터페이스 메소드 구현

        sqlSession.insert("insertStudent");

        //DB 작업 : 조회, 삽입, 삭제, 수정
        //삽입 sqlSession.insert("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //삭제 sqlSession.delete("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //수정 sqlSession.update("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //조회 sqlSession.select("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //조회 sqlSession.selectList("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //조회 결과 데이터가 0행 혹은 1행일 경우.
        //조회 sqlSession.selectOne("실행할 쿼리 id", 넣어도 되고 안넣어도 될때가 있음.[쿼리에서 빈 값을 채울 데이터])
        //조회할 때마다 조회 되는 행의 개수가 매번 다를 경우.
    }
    @Override
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) {
        //빈칸 채울 데이터 -> 매개변수
        sqlSession.delete("delete",stuNo);
    }
    @Override
    public String selectName(){
        return sqlSession.selectOne("selectName");
    }
    @Override
    public int selectScore(){
        return sqlSession.selectOne("selectScore");
    }
    @Override
    public StudentVO selectStu(){
        return sqlSession.selectOne("selectStu");
    }
    public List<StudentVO> selectStuList(){
        return sqlSession.selectList("selectStuList");
    }
}
