package com.green.DbTest.controller;

import com.green.DbTest.service.StudentSerivceImpl;
import com.green.DbTest.service.StudentService;
import com.green.DbTest.vo.StudentVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    //객체 만들어짐
    @Resource(name ="stuService")
    private StudentService studentSerivce;
    @GetMapping("/")
    public String insertTest(){
        //studentSerivce.insertStudent();
        //studentSerivce.deleteStudent();
        String name = studentSerivce.selectName();
        int score = studentSerivce.selectScore();
        System.out.println(studentSerivce.selectStu().toString());
        System.out.println(name);
        System.out.println(score);
        for(StudentVO e : studentSerivce.selectStuList()){
            System.out.println(e);
        }
        return "insert";
    }
    @GetMapping("/delete")
    public String deleteTest(){
        //studentSerivce.deleteStudent();
        return "delete";
    }
    @PostMapping("/deleteResult")
    public String deleteResult(@RequestParam(name="stuNo") int stuNo){
        //학생 삭제 기능
        studentSerivce.delete(stuNo);
        return "delete_Result";
    }

}
