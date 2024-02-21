package com.green.shop.study.fetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.cglib.core.Converter;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String main(){
        System.out.println("메인페이지로 감니다.");
        return "test/fetch/main";
    }
    @ResponseBody
    @PostMapping("/fetch1")
    public MemberVO fetch1(@RequestBody  MemberVO memberVO){
        System.out.println("fetch1 메소드 실행");
        System.out.println(memberVO.getAge());
        return memberVO;
    }

    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody(required = false) HashMap<String, String> map){
        System.out.println(map);
        System.out.println(map.get("id"));
        //map.put("age", "50");
        //System.out.println(map.get("age"));


        System.out.println("fetch2 메소드 실행22");

    }

    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody(required = false) ArrayList<MemberVO> memberVOList){
    //public void fetch3(@RequestBody(required = false) HashMap<> data){
        System.out.println(memberVOList);

        System.out.println("fetch3 메소드 실행333");

    }

    @ResponseBody
    @PostMapping("/fetch4")
    //public void fetch3(@RequestBody(required = false) ArrayList<MemberVO> memberVOList){
    public void fetch4(@RequestBody(required = false) HashMap<String, Object> data){
        System.out.println(data.get("cartCode").getClass());
        System.out.println(data.get("memberInfo").getClass());
        System.out.println(data.get("itemInfo").getClass());
        System.out.println(data);
        int cartCode1 = (int) data.get("cartCode");
        System.out.println(data.get("cartCode"));

        HashMap<String, String> map2 = (HashMap<String, String>)(data.get("memberInfo"));
        System.out.println(
                map2.get("memberId")
        );

        ObjectMapper objectMapper = new ObjectMapper();
        MemberVO memberVO1 = objectMapper.convertValue(data.get("memberInfo") , MemberVO.class);
        com.green.shop.member.vo.MemberVO memberVO2 = objectMapper.convertValue(data.get("memberInfo") , com.green.shop.member.vo.MemberVO.class);
        System.out.println(memberVO1.getMemberId());

        System.out.println("//////////////////////////////////////////////////////////");
        Map<String, Object> itemInfo = (Map<String, Object>) data.get("itemInfo");
        System.out.println(itemInfo);
        List<Object> imgList = (ArrayList<Object>)itemInfo.get("imgList");
        System.out.println(imgList.get(1));
        System.out.println(imgList.get(1).getClass());
        Map<String, Object> img = (HashMap<String, Object>) imgList.get(1);
        int imgCode = (int)img.get("imgCode");
        System.out.println("imgCode -> "+imgCode);
        System.out.println("/////////////////////////////////////////////////////////");
        ObjectMapper objectMapper1 = new ObjectMapper();
        MapDataVO mapDataVO1 = objectMapper1.convertValue(data, MapDataVO.class);
        System.out.println(mapDataVO1);






        System.out.println("fetch4 메소드 4444");

    }
}
