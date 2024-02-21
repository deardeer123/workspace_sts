package com.green.Board2.controller;

import com.green.Board2.service.ReplyService;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reply")
@Controller
public class ReplyController {
    @Resource(name="replyServiceMariDB")
    ReplyService replyService;
    @PostMapping("/insertReply")
    public String insertReply(ReplyVO replyVO, HttpSession session){
        MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
        replyVO.setWriter(vo.getMemberID());

        replyService.insertReply(replyVO);
        return "redirect:/board/info?boardNum="+replyVO.getBoardNum();
    }
    @GetMapping("/deleteReply")
    public String deleteReply(ReplyVO replyVO){
        System.out.println(replyVO);

        replyService.deleteReply(replyVO.getReplyNum());
        return "redirect:/board/info?boardNum="+replyVO.getBoardNum();
    }
}
