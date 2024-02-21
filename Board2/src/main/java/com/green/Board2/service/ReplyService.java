package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReplyService {
    List<ReplyVO> selectReplyList(int boardNum);
    ReplyVO selectReply(int boardNum);
    void insertReply(ReplyVO replyVO);
    void deleteReply(int replyNum);
    void updateReply(int replyNum);
}
