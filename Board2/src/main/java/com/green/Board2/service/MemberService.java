package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void joinMember(MemberVO memberVO);
    MemberVO login(MemberVO memberVO);
}
