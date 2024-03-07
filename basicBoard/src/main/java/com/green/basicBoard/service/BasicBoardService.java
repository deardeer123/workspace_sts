package com.green.basicBoard.service;

import com.green.basicBoard.vo.BasicBoardVO;
import com.green.basicBoard.vo.MemberVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasicBoardService {
    List<BasicBoardVO> selectBoardList();
    void insertBoard(BasicBoardVO basicBoardVO);
    BasicBoardVO selectBoard(int boardNum);
    void updateCnt(int boardNum);
    void modifyboard(BasicBoardVO basicBoardVO);
    void deleteboard(int boardNum);

    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(String memberId);
}
