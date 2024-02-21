package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;

@Getter
@Setter
@ToString

public class ItemVO {
    private int cateCode;
//    private String cateName;


    private int itemCode;
    private String itemName;
    private String itemPrice;
    private int itemStock;
    private String itemIntro;
    private String itemRegDate;
    //private String attachedFileName;
    private int itemStatus;
    private String strStatus;

    private ItemCategoryVO itemCategoryVO;
    private List<ItemImageVO> itemImageList;
}
