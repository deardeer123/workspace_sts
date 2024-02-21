package com.green.shop.admin.controller;



import com.green.shop.admin.service.AdminService;
import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.service.ItemService;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ItemCategoryVO;
import com.green.shop.item.vo.ItemImageVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.green.shop.util.ConstantVariable.UPLOAD_PATH;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name="adminService")
    private AdminServiceImpl adminService;
    @Resource(name="itemService")
    private ItemServiceImpl itemService;

    //상품 등록 페이지
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){

        model.addAttribute("page", 2);
        //System.out.println("아니 왜");
        //return "content/member/login";
        //System.out.println("될까?");
        model.addAttribute("cateList", itemService.selectCateList());
        return "content/admin/reg_item_form";
    }

    //상품등록 (관리자가 하는거)
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO,
                          @RequestParam(name="img1")MultipartFile img1 ,
                          @RequestParam(name="img2")MultipartFile[] img2){

        //다음에 들어갈 code 조회!!!

        int itemCode = adminService.selectNextItemCode();

        //상품 등록 쿼리 실행-----------------------
        itemVO.setItemCode(itemCode); //아이템 코드값도 같이 가져가세요
//        adminService.regItem(itemVO);

        //등록을 먼저시키고(예전꺼)
        //등록시킨놈의 아이템코드 가져오기
//        System.out.println(adminService.getItemCode());
//
//        int itemCode = adminService.getItemCode();

        //상품 사진 등록하기
        //현재 itemVO에는 상품명, 가격, 상품소개, 카테고리 코드 밖에 읍슴


        //메인 이미지 파일 하나 isMain = "Y";
        ItemImageVO mainImageVO = UploadUtil.uploadFile(img1);

        //이미지 등록시 채워줘야하는 모든 데이터를 리스트 생성해야함!!
        List<ItemImageVO> itemImageList;
        mainImageVO.setItemCode(itemCode);

        //이건 uploadutil 클래스에 잘 구현 되어있음 리턴값은 리스트임!!!
        itemImageList = UploadUtil.multiUploadFile(img2);

        //아이템 코드 넣어주기
        for(ItemImageVO itemImageVO : itemImageList){
            itemImageVO.setItemCode(itemCode);
        }
        //아까 만든 isMain이 "Y"인 ItemImageVO 넣어주기
        itemImageList.add(mainImageVO);
        //itemVO에 넣어주기
        itemVO.setItemImageList(itemImageList);

        for(ItemImageVO e : itemImageList){
            System.out.println(e);
        }

        //테스트
        //System.out.println(itemImageList);
        System.out.println(itemVO);

        adminService.regItem(itemVO);

        //이미지들 등록하기 !!!!
        //adminService.insertImgs(itemVO);

//        List<ItemImageVO> imageVOList = UploadUtil.multiUploadFile(img2, itemCode, "N");
//        System.out.println("여기까지는 오나?");
//        //System.out.println(imageVOList);
//        for(ItemImageVO itemImageVO : imageVOList){
//            System.out.println(itemImageVO);
//        }
//        imageVOList.add(UploadUtil.uploadFile(img1 , itemCode, "Y"));
//        System.out.println("여기까지는?");
//        for(ItemImageVO itemImageVO : imageVOList){
//            System.out.println(itemImageVO);
//        }
//        System.out.println("제발 되주세요!!!!");
//        adminService.regImage(imageVOList);


        //System.out.println(itemVO);

        return "redirect:/admin/regItemForm";
    }
    @GetMapping("/goAdminHistory")
    public String goAdminBuyList(Model model, SearchVO searchVO){
        String page = "adminHistory"; //이건 옆에 사이드메뉴 활성화할려고 만든것
        model.addAttribute("page", page);
        //관리자용 구매 목록 조회
        model.addAttribute("page", 1);

        //일단 날짜 설정을 해야되는데..
        searchVO.setSearchMinDate("2024-02-02");
        searchVO.setSearchMaxDate("2024-05-04");

        model.addAttribute("searchVO",searchVO);
        model.addAttribute("buyList",adminService.selectAdminBuyList(searchVO));

        return "content/admin/admin_history";
    }

    @ResponseBody
    @PostMapping("/adminHistory")
    public List<BuyVO> adminHistory(SearchVO searchVO){
        System.out.println(searchVO);
        System.out.println("여기까지 들어는 옵니까??");
        List<BuyVO> buyVOS;
        buyVOS = adminService.selectAdminBuyList(searchVO);
        return buyVOS;
    }

    @RequestMapping("/goAdminBuyList")
    public String goAdminBuyList1(Model model, SearchVO searchVO){
        //구매목록 조회 새로만든거임
        model.addAttribute("page", 6);
        //대충
//        searchVO.setSearchMinDate("2024-02-02");
//        searchVO.setSearchMaxDate("2024-05-04");
        Date today = new Date();//현재 잘짜
        SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd");
        searchVO.setSearchMaxDate(date.format(today).toString()); //현재 날짜 넣기

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(today);
        cal1.add(Calendar.MONTH, -1); // 월 연산
        searchVO.setSearchMinDate(date.format(cal1.getTime()).toString()); //현재날자에서 한달뺀 날짜 넣기

        System.out.println(searchVO);
        System.out.println(searchVO);
        System.out.println(searchVO);
        System.out.println(searchVO);

        model.addAttribute("searchVO",searchVO);
        System.out.println(adminService.selectBuyList(searchVO));
        List<BuyVO> buyVOList = adminService.selectBuyList(searchVO);
        model.addAttribute("buyList", buyVOList);
        return "content/admin/admin_history_copy";
    }

    @ResponseBody
    @PostMapping("buyListSearch1")
    public List<BuyVO> buyListSearch(SearchVO searchVO){
    //버튼 눌렀을떄 
        
        List<BuyVO> buyList;
        System.out.println(searchVO);
        System.out.println(searchVO);
        System.out.println(searchVO);
        System.out.println(searchVO);
        System.out.println(searchVO);
        buyList = adminService.selectBuyList(searchVO);
        System.out.println(buyList);
        return buyList;
    }

    //상세구매 내역 조회
    @ResponseBody
    @PostMapping("selectBuyDetail")
    public BuyVO buyDetailInfo1(@RequestParam(name ="buyCode")int buyCode){
        System.out.println("buyCode : " + buyCode);
        BuyVO buyVO = adminService.selectBuyDetail(buyCode);
        System.out.println(buyVO);

        return buyVO;
    }

    //상품 정보 변경
    @GetMapping("goUpdateItem")
    public String goUpdateItem(Model model, @RequestParam(name = "itemCode", required = false, defaultValue = "0")int itemCode){

        model.addAttribute("page", 4);
        model.addAttribute("updateItemCode",itemCode);

        System.out.println("상품 정보 변경 페이지로 이동합시다.");
        //가기전에 itemvo리스트좀 가져가고
        model.addAttribute("itemList", adminService.searchingForItemForUpdate());
        //카테고리도

        System.out.println(itemService.selectCateList());
        model.addAttribute("cateList", itemService.selectCateList());
        //테스트
        System.out.println(itemService.selectCateList2());

        //배운거 -strStatus가 들어가 있음
        model.addAttribute("itemList", adminService.selectItemList());



        return "content/admin/update_item";
    }

    //상품 정보 변경 화면의 상품 목록 테이블 행 클릭 시
    //상품의 상세 정보를 조회하는 비동기
    @ResponseBody
    @PostMapping("/updateItem")
    public Map<String, Object> updateItem(@RequestParam(name = "itemCode")int itemCode){
        System.out.println("자료 던져줍니다.");
        //return adminService.searchingForItemForUpdate2(itemCode);
        //상품 상세 정보 조회
        ItemVO itemDetail = adminService.selectItemDetail(itemCode);
        //카테고리 목록 조회
        //List<ItemVO> cateList = itemService.selectCateList();

        //이 두 데이터를 담을 수 있는 map 객체 생성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", itemService.selectCateList());
        return map;
    }

    @ResponseBody
    @PostMapping("/modalImg")
    public ItemImageVO modalImg(@RequestParam(name="imageCode")int imageCode){
        System.out.println("이미지 던져줍니다.");
        return itemService.selectImg(imageCode);
    }


    //상품 상세 변경하기
    @ResponseBody
    @PostMapping("/changeDetail")
    public List<ItemVO> changeDetail(ItemVO itemVO,
                                     @RequestParam(name="img1" , required = false)MultipartFile img1,
                                     @RequestParam(name="img2" , required = false)MultipartFile[] img2){
        System.out.println(itemVO);

        adminService.updateItemDetail(itemVO);
        return  adminService.selectItemList();
    }








    //상품 이미지 변경하기
    @ResponseBody
    @PostMapping("/changeImage")
    public ItemImageVO changeImage(@RequestParam(name="img1" , required = false)MultipartFile img1){

        return UploadUtil.uploadFile(img1);
    }

    //상품 이미지들 변경하기2
    @ResponseBody
    @PostMapping("/changeImage2")
    public List<ItemImageVO> changeImage2(@RequestParam(name="img2" , required = false)MultipartFile[] img2){

        return UploadUtil.multiUploadFile(img2);
    }


    //동기식 상품 상세 정보 변경하기
    @PostMapping("/updateItem1")
    public String updateItem1(ItemVO itemVO){
        adminService.updateItemDetail(itemVO);
        return "redirect:/admin/goUpdateItem?itemCode=" + itemVO.getItemCode();

    }
}
