package com.green.shop.util;

import com.green.shop.item.vo.ItemImageVO;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtil{

    //파일의 확장자를 문자열로 리턴하는 메소드
    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));

    }
    //uuid를 통한 파일명 생성
    public static String getUUID(){
        return UUID.randomUUID().toString();

    }


    //단일 파일 업로드 메소드 바뀌기전엔 리턴값은 ItemImageVO 매개변수 , int itemCode, String isMain
    public static ItemImageVO uploadFile(MultipartFile upLoadFile) {
        ItemImageVO itemImageVO = null;

        if (upLoadFile.getOriginalFilename().isEmpty()) {
            System.out.println("사진등록 안했어요");
            return itemImageVO;
        } else {
            try {
                itemImageVO = new ItemImageVO();

                //확장자?
                String extension = getExtension(upLoadFile.getOriginalFilename());

                //파일네임(무작위로 생성한 이름에 .jpg 붙인것)
                String fileName = getUUID() + extension;

                itemImageVO.setOrigenFileName(upLoadFile.getOriginalFilename());
                itemImageVO.setAttachedFileName(fileName);
                itemImageVO.setIsMain("Y");

//                itemImageVO.setIsMain(isMain);
//                itemImageVO.setItemCode(itemCode);
                File file = new File(ConstantVariable.UPLOAD_PATH + fileName);
                //파일 객체 생성하기
                upLoadFile.transferTo(file);
                //넣기
                //return itemImageVO;

            } catch (Exception e) {
                System.out.println("예외발생");
                e.printStackTrace();
            }
        }
        return itemImageVO;
        //return itemImageVO;
    }

    //다중 첨부 메소드 리턴값은 List<ItemImageVO> <매개변수 int itemCode, String isMain 엿음
    public static List<ItemImageVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ItemImageVO> itemImageVOList = null;
        ItemImageVO itemImageVO = null;
        if(uploadFiles[0].isEmpty()){
            System.out.println("사진들이 비어있어요");
            return itemImageVOList;
        } else {
            itemImageVOList = new ArrayList<>();
            for (MultipartFile uploadFile : uploadFiles) {
                //매개변수에 , itemCode, isMain 들어 있었음!!
                itemImageVO = uploadFile(uploadFile);
                if(itemImageVO != null){
                    itemImageVO.setIsMain("N");
                    itemImageVOList.add(itemImageVO);
                }
            }
        }
        return itemImageVOList;
    }

}
