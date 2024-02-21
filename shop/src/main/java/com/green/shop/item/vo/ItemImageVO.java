package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ItemImageVO {
    private int imageCode;
    private String origenFileName;
    private String attachedFileName;
    private String isMain;
    private int itemCode;
}
