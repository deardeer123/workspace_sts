package com.celestegreen.chatTest.util;

import com.celestegreen.chatTest.item.vo.ItemImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class imgUtil {
    //파일의 확장자를 문자열로 리턴하는 메소드
    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));

    }
    //uuid를 통한 파일명 생성
    public static String getUUID(){
        return UUID.randomUUID().toString();

    }

    public static ItemImgVO uploadFile(MultipartFile upLoadFile) {
        ItemImgVO itemImgVO = null;

        if (upLoadFile.getOriginalFilename().isEmpty()) {
            System.out.println("사진등록 안했어요");
            return itemImgVO;
        } else {
            try {
                itemImgVO = new ItemImgVO();

                //확장자?
                String extension = getExtension(upLoadFile.getOriginalFilename());

                //파일네임(무작위로 생성한 이름에 .jpg 붙인것)
                String fileName = getUUID() + extension;

                itemImgVO.setOrigenFileName(upLoadFile.getOriginalFilename());
                itemImgVO.setAttachedFileName(fileName);


//                itemImgVO.setIsMain(isMain);
//                itemImgVO.setItemCode(itemCode);
                File file = new File(ConstantVariable.UPLOAD_PATH + fileName);
                //파일 객체 생성하기
                upLoadFile.transferTo(file);
                //넣기
                //return itemImgVO;

            } catch (Exception e) {
                System.out.println("예외발생");
                e.printStackTrace();
            }
        }
        return itemImgVO;
        //return itemImgVO;
    }

}
