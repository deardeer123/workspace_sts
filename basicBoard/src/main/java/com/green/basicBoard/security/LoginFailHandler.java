package com.green.basicBoard.security;

import com.green.basicBoard.service.BasicBoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
//로그인 실패 시 실행하는 클래스

//클래스 객체 자동생성
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {


    //AuthenticationFailureHandler라는 인터페이스를 구현한 클래스를 생성하면
    //onAuthenticationFailure()라는 메소드를 정의해야한다
    // 이 메서드가 로그인 실패 시 자동 호출된다.

    // 로그인 실패시 자동 실행될 클래스
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("로그인실패@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String errorMsg="";
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMsg ="아이디 또는 비밀번호가 틀립니다.";
        } else if(exception instanceof UsernameNotFoundException){
            errorMsg ="존재하지 않는 사용자 입니다.";
        } else{
            errorMsg ="알수 없는 이유로 로그인 실패했습니다. 관리자에게 문의하세요";
        }

        //한글 인코딩 변환
        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");

        //이동할 페이지
        response.sendRedirect("/loginForm?errorMsg="+errorMsg);
    }
}
