package com.green.basicBoard.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
//로그인 성공 시 실행되는 클래스
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    //로그인 성공 시 자동으로 호출되는 메소드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //로그인 성공시 이동할 페이지
        setDefaultTargetUrl("/");


        ///////////////////////////////
//        로그인 하고나서 처리하고 싶은 내용들!!
//        세션에 데이터 저장

        HttpSession session = request.getSession();
        //session.setAttribute();
        //session.getAttribute();
        ///////////////////////////////

        //로그인 정보를 통한 로직 구성
        User user = (User) authentication.getPrincipal();
        List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());
        List<String> aList = new ArrayList<>();

        for(GrantedAuthority e : authoList){
            aList.add(e.getAuthority());
        }

        boolean b = aList.contains("ADMIN");

        if(b){
            String targetUrl = "/";
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }

        //이전 페이지 혹은 가려는 페이지가 있는 경우
        if(savedRequest != null){
            //원래가려고 하는 페이지 정보
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }

        System.out.println("로그인성공@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //response.sendRedirect("/");
    }
}
