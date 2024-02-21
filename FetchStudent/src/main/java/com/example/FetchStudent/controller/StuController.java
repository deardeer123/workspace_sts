package com.example.FetchStudent.controller;

import com.example.FetchStudent.service.StuServiceImpl;
import com.example.FetchStudent.vo.ClassVO;
import com.example.FetchStudent.vo.StuDetailVO;
import com.example.FetchStudent.vo.StuScoreVO;
import com.example.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StuController {
    @Resource(name = "stuServiceMariaDB")
    private StuServiceImpl stuService;
    @RequestMapping("/")
    public String main(ClassVO classVO, Model model){
        List<ClassVO> classList = stuService.selectClassList();
        List<StuVO> stuList = stuService.selectStuList(classVO);
        model.addAttribute("classList" , classList);
        model.addAttribute("stuList", stuList);
        System.out.println(classVO.getClassCode());
        //model.addAttribute("classVO", classVO);
        //학급 목록 데이터 조회 할것
        ;
        //System.out.println(stuService.selectClassList().get(0));

        //학생 모곩 데이엍도 조회

        return "stu_manage";
    }
    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(ClassVO classVO){

        return stuService.selectStuList(classVO);
    }

    @ResponseBody
    @PostMapping("/stuSelect")
    public StuDetailVO stuSelect(StuVO stuVO){
        //담을수 있는 클래스 객체 생성!!!!!!!!!!!!!!!!
        StuDetailVO stuDetailVO = new StuDetailVO();
        //넣자1
        stuDetailVO.setStuVO(stuService.selectStu(stuVO));
        System.out.println(stuService.selectStuScore(stuVO));

        if(stuService.selectStuScore(stuVO) == null){
            System.out.println("널값나오는데?");
            stuService.insertStuScore(stuVO); //만들고
            //넣자2
            stuDetailVO.setStuScoreVO(stuService.selectStuScore(stuVO));
        }else{
            System.out.println("값이 있네요");
            //넣자2
            stuDetailVO.setStuScoreVO(stuService.selectStuScore(stuVO));
        }
        System.out.println("확인하자");
        //stuDetailVO.setStuScoreVO(stuService.selectStuScore(stuVO));
        System.out.println(stuDetailVO);
        return stuDetailVO;
    }

    @ResponseBody
    @PostMapping("/saveScore")
    public StuScoreVO saveScore(StuScoreVO stuScoreVO){
        System.out.println(stuScoreVO);
        stuService.updateStuScore(stuScoreVO);
        StuVO stuVO = new StuVO();
        stuVO.setStuNum(stuScoreVO.getStuNum());
        return stuService.selectStuScore(stuVO);
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(StuScoreVO stuScoreVO){
        System.out.println(stuScoreVO);
        stuService.updateStuScore(stuScoreVO);
    }
}
