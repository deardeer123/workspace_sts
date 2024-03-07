package com.green.basicBoard.vo;

import lombok.*;

//생성자를 구현하는 어노테이션
//@NoArgsConstructor //매개변수가 없는 기본 생성자 생성
//@AllArgsConstructor // 멤버변수 모두를 매개변수로 받는 생성자 생성
// @RequiredArgsConstructor //필요한 멤버변수를 매개변수로 받는 생성자 생성
//기본 생성자, setter getter toString

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;


}

class BuilderTest{
    public void test(){
        //id를 java라는 초기값으로 갖는 객체 생성
        MemberVO v1 = MemberVO.builder()
                .memberId("java")
                .build();

        MemberVO v2 = MemberVO.builder()
                .memberId("java")
                .memberName("hong")
                .build();

        MemberVO v3 = MemberVO.builder()
                .memberId("java")
                .memberName("hong")
                .memberRoll("User")
                .build();
        MemberVO v4 = MemberVO.builder().build();
    }
}
