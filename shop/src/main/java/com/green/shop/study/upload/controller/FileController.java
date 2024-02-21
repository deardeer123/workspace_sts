package com.green.shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//파일 업로드, 다운로드, 학습용 컨트롤러
@Controller
@RequestMapping("/file")

//파일 첨부 페이지로 이동
public class FileController {
    @GetMapping("/uploadForm")
    public String uploadForm(){
        return "test/upload/upload_form";
    }
    //파일 업로드
    //MultipartFile img1 이미지파일 받기위한 객체?
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "img1") MultipartFile img1 ,
                         @RequestParam(name = "img2") MultipartFile img2){
        //첨부한 파일명 확인!!
        System.out.println(img1.getOriginalFilename());

        //파일 저장할때 똑같은 이름의 파일이 있으면 덮어써버림!!!!!!!!!


        //업로드 경로
        //resources/static/upload 폴더에 저장
        String upLoadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        //파일 업로드
        //자바.io.file 패키지 선택!!!!!!!!!!!!!
        //매개변수로 저장될 파일경로 + 첨부파일명 입력!!
        String filename = upLoadPath + img1.getOriginalFilename();
        //액션 더보기하기 하고 두번째꺼 선택
        try {
            File file = new File(filename);
            img1.transferTo(file);
            //transferTo는 오류가 발생할 가능성이 매우 높은 메소드임
            //그러므로 try catch문으로 예외처리는 해줘야함
        } catch (IOException e) {
            System.out.println("파일 첨부 중 오류발생!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            throw new RuntimeException(e);
        }
        //두번째 파일 업로드
        //원본 파일의 확장자만 추출
        String secondOriginFileName = img2.getOriginalFilename();
        int index = secondOriginFileName.lastIndexOf(".");
        //제일 마지막에 있는 .가 있는 인덱스 번호 알려줌!!!!!!!!!!!!!!!
        String img2extension = secondOriginFileName.substring(index);
        System.out.println(secondOriginFileName);
        System.out.println("img2 의 확장자는 " + img2extension + "입니다/");
        //System.out.println(name2);s
        //서버에 저장할 파일명 생성

        String uuid = UUID.randomUUID().toString();
        String attachFilename = uuid + img2extension;


        try{
            File file2 = new File(upLoadPath + attachFilename);
            img2.transferTo(file2);
        }catch (Exception e){
            System.out.println("파일첨부중 오류발생!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        //특수문자를 문자열로 취급하기 위해선 \작성하믄댐
        String str = "\"안녕\"";

        return "redirect:/file/uploadForm";
    }

    //다중 첨부 페이지로 이동
    @GetMapping("/multiForm")
    public String multiForm(){
        return "test/upload/multi_form";
    }

    @PostMapping("/multi")
    public String multi(@RequestParam(name = "imgs")MultipartFile[] imgs ){
        String upLoadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        for(MultipartFile img : imgs){
            System.out.println(img.getOriginalFilename());
            //확장자 추출
            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            String attachedFileName = UUID.randomUUID() + extension;
            try{
                File file = new File(upLoadPath+attachedFileName);
                img.transferTo(file);
            }
            catch(Exception e){
                System.out.println("오류발생!!!!!!!!!!");
                e.printStackTrace();
            }
        }

        //첨부할 파일 경로



        return "redirect:/file/multiForm";
    }
}
