package com.test.carManagement.controller;

import com.test.carManagement.service.CarService;
import com.test.carManagement.vo.CarVO;
import com.test.carManagement.vo.SalesVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {
    @Resource(name="carService")
    CarService carService;

    @GetMapping("/")
    public String goMain(){
        //첫 시작은 홈으로
        return "content/carManagement/home";
    }

    @GetMapping("/goHome")
    public String goHome(){
        //그말대로 홈으로 갑ㄴ디ㅏ.
        return "redirect:/";
    }




    @GetMapping("/goCarManagement")
    public String goCarManagement(Model model){
        //차량 관리 페이지로 이동합니다.
        //이동할때 차량목록을 가져가야 합니다.
        List<CarVO> carList = carService.selectCarList();
        System.out.println(carList);
        model.addAttribute("carList",carList);

        return "content/carManagement/car_management";
    }
    @PostMapping("/goReg")
    public String goReg(CarVO carVO){
        System.out.println(carVO);
        //차량 등록하고 다시 차량 관리 페이지 이동
        carService.insertCar(carVO);
        System.out.println("차량등록?");

        return "redirect:/goCarManagement";
    }

    @GetMapping("/goSalesRegForm")
    public String goCarRegForm(Model model){
        //판매 등록 페이지로 이동
        //이동할때 차량목록을 가져가야 합함
        List<CarVO> carList = carService.selectCarList();
        model.addAttribute("carList", carList);

        return "content/carManagement/sales_reg_form";
    }
    @PostMapping("/saleReg")
    public String saleReg(SalesVO salesVO){
        //등록하고 차량판메 리스트 페이지로 이동해야함
        //값 제대로 받아오나 확인
        System.out.println(salesVO);
        carService.insertSaleInfo(salesVO);
        return "redirect:/goSaleList";
    }


    @GetMapping("/goSaleList")
    public String goSaleList(Model model){
        //넘어갈때 판매정보들을 같이 들고가야함
        List<SalesVO> salesList = carService.selectSalesList();
        System.out.println(salesList); //확인
        for(SalesVO e : salesList){
            System.out.println(e);
        }
        model.addAttribute("salesList", salesList);
        return "content/carManagement/car_sales_list";
    }



}
