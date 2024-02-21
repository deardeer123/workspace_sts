package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    List<BoardVO> selectBoardList(SearchVO searchVO);
    void insertBoard(BoardVO boardVO);
    BoardVO selectBoard(int boardNum);
    void updateCnt(int boardNum);
    void modifyBoard(BoardVO boardVo);
    void deleteBoard(int boardNum);
    List<BoardVO> searchTitle(String title);
    List<BoardVO> searchWriter(String writer);

    //게시글 수 조회
    int selectBoardCnt(SearchVO searchVO);
}
