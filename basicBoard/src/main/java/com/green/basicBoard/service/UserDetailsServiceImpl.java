package com.green.basicBoard.service;

import com.green.basicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//UserDetailService는 시큐리티가 알아서 만들어줌
//스프링 시큐리티가 제공하는 로그인 기능을 구현
//UserDetailsService 인터페이스를 상속해서 구현
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "boardService")
    private BasicBoardService boardService;


    // 시큐리티가 알아서 로그인할 때 호출하는 메소드!!
    // 매개변수로 전달되는 문자열을 로그인을 시도하는 회원의 id가 됨!!
    // 그래서 로그인 html에서 입력하는 id input 태그의 name 속성값을
    // 반드시 username으로 설정해야 id 값이 넘어옴 .
    // input 태그의 name 속성 값을 password로 지정하면
    // 해당 input 태그의 데이터를 비밀번호로 간주 
    // 근데 이런거 안 해깔리게 바꿔줄 수 있음 (SecurityConfig에서 설정)

    //로그인을 실행하는 url이 호출되면 아래의 메소드가 자동으로 실행됨
    //로그인 하려는 사람의 로그인에 필요한 정보를 조회해서 시큐리티 한테 전달
    //하는 코드를 작성
    @Override
    public UserDetails loadUserByUsername(String username){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //로그인 하려는 회원의 정보 조회
        MemberVO member = boardService.login(username);

        if(member == null){
//            시큐리티가 아이디 검색하는데 못 얻었을때 나오는 오류임
            throw new BadCredentialsException("error");
        }

        //조회한 정보를 UserDetails 타입으로 변환
        UserDetails userInfo = User.builder()
                .username(member.getMemberId())
                //비밀번호를 시큐리티에게 전달하면
                //기본적으로 시큐리티는 비밀번호가 암호화되어 있다고 판단.
                //{noop}을 비빌번호 앞에 문자열로 추가하면 암호화 되어 있지 않다는 것을 알려줌
                .password(member.getMemberPw())
                .roles(member.getMemberRoll())
                .build();

        return userInfo;
    }
}
