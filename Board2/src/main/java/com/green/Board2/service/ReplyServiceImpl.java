package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyServiceMariDB")
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public List<ReplyVO> selectReplyList(int boardNum) {
        return sqlSession.selectList("selectReplyList",boardNum);
    }
    @Override
    public ReplyVO selectReply(int boardNum) {
        return sqlSession.selectOne("replyMapper.selectReply",boardNum);
    }
    @Override
    public void insertReply(ReplyVO replyVO) {
        sqlSession.insert("replyMapper.insertReply",replyVO);
    }

    @Override
    public void deleteReply(int replyNum) {
        sqlSession.delete("deleteReply",replyNum);
    }

    @Override
    public void updateReply(int replyNum) {
        sqlSession.update("updateReply",replyNum);
    }
}
