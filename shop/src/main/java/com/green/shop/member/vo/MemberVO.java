package com.green.shop.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String Gender;
    private String memberEmail;
    //private String memberEmail1;
    //private String memberEmail2;
    private String memberTel;
    //private String memberTel1;
    //private String memberTel2;
    //private String memberTel3;
    private String memberAddr;
    private String AddrDetail;

    private String postCode;
    private String joinDate;
    private String memberRoll;
}
