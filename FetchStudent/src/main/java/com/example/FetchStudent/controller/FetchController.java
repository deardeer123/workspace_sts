package com.example.FetchStudent.controller;

import com.example.FetchStudent.vo.StuVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//비동기 통신 학습용 컨트롤러
// javascript -> fetch
// jquery -> ajax
// react -> axios
//

@Controller
@RequestMapping("/study")
public class FetchController {

    @GetMapping("/t1")
    public String t1() {
        return "fetch_test";
    }
    // 해당 메소드는 비동기 통신을 사용하기 때문에
    // 메소드의 마지막 return으로 페이진 전환 하지 않겠다를 스프링에게 알림
    @ResponseBody
    @PostMapping("/t2")
    public String t2(@RequestParam(name = "name")String name,
                     @RequestParam(name="age")int age){
        System.out.println("t1 메소드 실행");
        System.out.println("name = "+ name);
        System.out.println("age = " + age);
        //hello.html로 이동한다는 말아님 then구문으로 갈때 가져갈 데이터임
        return "HELLO!!!!!!!!!!!!";
    }
    @ResponseBody
    @PostMapping("/t3")
    public StuVO t3(StuVO stuVO1){
        System.out.println("t3 메소드실행!!!!!!!!!!!1");
        System.out.println(stuVO1);
        StuVO stuVO = new StuVO();
        stuVO.setStuName("김자바");
        stuVO.setStuNum(1);
        stuVO.setClassCode(1);
        stuVO.setClassName("자바반");
        return stuVO;
    }

    @ResponseBody
    @PostMapping("/t4")
    public List<StuVO> t4(){
        System.out.println("t4 메소드ㅡㅡㅡㅡㅡㅡㅡ");
        List<StuVO> list = new ArrayList<>();

        for(int i=1; i<6; i++){
            StuVO stuVO = new StuVO();
            stuVO.setStuName("김자바_" + i + "호");
            stuVO.setStuNum(i);
            stuVO.setClassCode(1+i);
            stuVO.setClassName("자바반_" +i);
            list.add(stuVO);
        }
        return list;
    }
}
