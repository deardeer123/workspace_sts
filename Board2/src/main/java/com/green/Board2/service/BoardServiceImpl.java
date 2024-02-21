package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("boardServiceMariaDB")
public class BoardServiceImpl implements BoardService{
    @Autowired
    SqlSessionTemplate sqlSession;
    @Override
    public List<BoardVO> selectBoardList(SearchVO searchVO) {
        return sqlSession.selectList("selectBoardList", searchVO);
    }

    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("insertBoard",boardVO);
    }

    @Override
    public BoardVO selectBoard(int boardNum) {
        return sqlSession.selectOne("selectBoard",boardNum);
    }
    public void updateCnt(int boardNum){
        sqlSession.update("updateCnt", boardNum);
    }

    @Override
    public void modifyBoard(BoardVO boardVo) {
        sqlSession.update("modifyBoard",boardVo);
    }

    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("deleteBoard",boardNum);
    }

    @Override
    public List<BoardVO> searchTitle(String title) {
        return sqlSession.selectList("searchTitle",title);
    }

    @Override
    public List<BoardVO> searchWriter(String writer) {

        return sqlSession.selectList("boardMapper.searchWrite",writer);
    }

    @Override
    public int selectBoardCnt(SearchVO searchVO) {

        return sqlSession.selectOne("boardMapper.selectBoardCnt",searchVO);
    }


}
