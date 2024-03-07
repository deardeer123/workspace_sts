package com.green.basicBoard.service;

import com.green.basicBoard.service.BasicBoardService;
import com.green.basicBoard.vo.BasicBoardVO;
import com.green.basicBoard.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("boardService")
public class BasicBoardImpl implements BasicBoardService {
    @Autowired
    SqlSessionTemplate sqlSession;
    @Override
    public List<BasicBoardVO> selectBoardList() {
        return sqlSession.selectList("basicBoardMapper.selectBoardList");
    }

    @Override
    public void insertBoard(BasicBoardVO basicBoardVO) {
        sqlSession.insert("basicBoardMapper.insertBoard",basicBoardVO );
    }

    @Override
    public BasicBoardVO selectBoard(int boardNum) {
        return sqlSession.selectOne("basicBoardMapper.selectBoard", boardNum);
    }

    @Override
    public void updateCnt(int boardNum) {
        sqlSession.update("basicBoardMapper.updateCnt", boardNum);
    }

    @Override
    public void modifyboard(BasicBoardVO basicBoardVO) {
        sqlSession.update("modifyboard", basicBoardVO);
    }

    @Override
    public void deleteboard(int boardNum) {
        sqlSession.delete("deleteboard",boardNum);
    }

    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("memberMapper.join",memberVO);
    }

    @Override
    public MemberVO login(String memberId) {
        return sqlSession.selectOne("memberMapper.login",memberId);
    }


}
