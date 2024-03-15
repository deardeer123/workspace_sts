package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@ToString
public class MemberVO {
    private String id;
    private String name;
    private int age;
}
