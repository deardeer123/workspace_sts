package com.green.basicBoard.interceptor;

import com.green.basicBoard.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

//Interceptor 클래스
//HandlerInterceptor 인터페으스를 구현한 클래스는 Interceptor 클래스로 인식
//해당 클래스에는 반복되는 기능을 정의
//반복되는 기능의 호출 되는 시점에 따라서
//HandlerInterceptor 인터페이스의 메서드를 오버라이딩

//preHandle() : controller 안의 메서드 실행 직전에 호출
//postHandle() : controller 안의 메서드 실행 후 html로 가기 전에 호출
//afterCompletion() : 컨트롤러의 안의 메서드 실행 후 html 까지 다 실행되면 호출.
public class PrintInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PrinterInterceptor : preHandle() 메소드 실행!!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PrinterInterceptor : postHandle() 메소드 실행!!");
        Map<String, Object> modelData = modelAndView.getModel();
        String name = Optional.ofNullable(modelData.get("name").toString()).orElse("name");
        MemberVO memberVO = Optional.ofNullable((MemberVO)modelData.get("member")).orElse(new MemberVO());
        modelAndView.addObject("addr", "울산");


    }
}
