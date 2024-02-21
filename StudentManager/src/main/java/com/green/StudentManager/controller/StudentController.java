package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuService;
import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Resource(name = "aaa")
    private StuService stuService;
    @GetMapping("/")
    public String stuList(Model model){
        System.out.println("학생정보목록 페이지 접속");
        //조회한 목록을 html로 전달
        model.addAttribute("stuList",stuService.selectStuList());
        return "stu_list";
    }
    @GetMapping("/regStu")
    public String regStu(){
        System.out.println("학생 등록 페이지 접속");
       return "reg_student";
    }
//getmapping이랑 postmapping 이랑 이름 겹쳐도 상관없음
//    근데 getmapping, postmapping 둘다 쓰면 안됨.
    @PostMapping("/regStu")
    public String reg(){
        //학생 등록
        //System.out.println(stuVO);
        System.out.println("등록되었나요?");

        return "redirect:/";
    }
    @GetMapping("/infoStu")
    public String info(@RequestParam(name="stuNo") int stuNo, Model model){
        System.out.println(stuNo);
        StuVO stu = stuService.selectStu(stuNo) ;
        System.out.println(stu);
        model.addAttribute("stuInfo",stu);
        //학생상세정보
        return "student_detail";
    }
//    @GetMapping("deleteStu")
//    public String delete(@RequestParam(name="stuNo") int stuNo){
//        System.out.println("++++++++++++++++++++++++++++++++++++++");
//        System.out.println(stuNo);
//        stuService.deleteStu(stuNo);
//        return "redirect:/";
//    }

    @GetMapping("deleteStu")
    public String delete(StuVO stuVO){
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println(stuVO.getStuNo());
        stuService.deleteStu(stuVO.getStuNo());
        return "redirect:/";
    }

}
