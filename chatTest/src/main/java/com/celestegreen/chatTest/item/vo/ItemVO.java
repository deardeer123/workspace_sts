package com.celestegreen.chatTest.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemVO {
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String regDate;
    private int cateCode;
    private ItemImgVO itemImgVO;

}
