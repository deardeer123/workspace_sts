package com.green.basicBoard.security;

//스프링 시큐리티 인증, 인가에 대한 프로세스를 정의


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// /member/* /member/a , /member/b, /member/c ,/member/abd/a x
// /member/** /member/a , /member/b , /member/c , /member/a/b/c 
//인가할때 많이씀

//이 클래스가 시큐리티 설정파일임을 인지시켜주는 역할
// 객체 생성 어노테이션 (@Controller, @Service, @Configuration)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Bean
    public BCryptPasswordEncoder getEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    //객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        //csrf 공격에 대한 방어를 해지하겠다.
        //::는 메소드 참조 람다식
        security.csrf(AbstractHttpConfigurer::disable)
                //authorizeHttpRequests 메소드 안ㅇ서 인증 및 인가 관리
                .authorizeHttpRequests(
                   c -> {

                       c.anyRequest().permitAll();

                       //인증 및 인가 설정하는중
//                       c.requestMatchers(
//                               new AntPathRequestMatcher("/"),
//                               new AntPathRequestMatcher("/loginForm"),
//                               new AntPathRequestMatcher("/joinForm"),
//                               new AntPathRequestMatcher("/join"),
//                               new AntPathRequestMatcher("/login"),
//                               new AntPathRequestMatcher("/sample")
////                               new AntPathRequestMatcher("/member/**")
//
//                       ).permitAll()
//                       .requestMatchers(
//                               new AntPathRequestMatcher("/admin")
//                       ).hasRole("ADMIN")
//                       .requestMatchers(
//                               new AntPathRequestMatcher("/manager")
//                       ).hasRole("MANAGER")
//                           .requestMatchers(
//                                   new AntPathRequestMatcher("/boardWriteForm")
//                           ).hasAnyRole("USER","MANAGER")
//                       .anyRequest().authenticated();
                   }
                   //로그인 form을 할용해서 할 것이고
                        // 로그인 설정 내용도 포함
                ).formLogin(
                        formLogin -> {
                            //로그인 url 설정
                            formLogin.loginPage("/loginForm")
                                    //로그인 시 전달되는 id 및 비밀번호 name 속성값을 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")

                                    // 로그인 기능이 실행되는 url
                                    .loginProcessingUrl("/login")
                                    // 로그인 성공 시 이동할 url true 넣으면 무조건 / 으로감
                                    //false 인경우는 이전페이지로 가던가 설정한 url으로 감
                                    //.defaultSuccessUrl("/", false) //밑에 있는거 (handler) 넣으면 주석처리
                                    //.failureUrl("/loginForm")
                                    //successHandler 로그인 성공 시 실행시킬 클래스의 객체
                                    .successHandler(loginSuccessHandler)
                                    //failureHandler 로그인 실패 시 실행시킬 클래스의 객체
                                    .failureHandler(loginFailHandler);
                        }
                ).logout(
                        logout->{
                            //해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                            //로그아웃 url
                            logout.logoutUrl("/logout")
                                    //로그아웃 성공시 가는 url
                                    .logoutSuccessUrl("/")
                                    //세션값 날려버림
                                    .invalidateHttpSession(true);
                        }

                )
                .exceptionHandling(
                        ex->{
                            ex.accessDeniedPage("/deny");
                        }
                );


        return security.build();
    }
    //정적인 자원은 security가 인증 및 인가 관리에서 제외하도록 설정
    //정적인 파일 : .js , .css, 이미지
    //resources 폴더 밑에 있는 모든 파일들
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/upload/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/images/**")
        );
    }
}
