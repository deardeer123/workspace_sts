package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service("memberServiceMariaDB")
public class MemberServiceImpl implements MemberService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public void joinMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.joinMember", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }
}
