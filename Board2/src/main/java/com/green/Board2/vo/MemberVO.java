package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberID;
    private String memberPW;
    private String memberName;
    private String gender;
    private String memberEmail;
    private String isAdmin;
}
