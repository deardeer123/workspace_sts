package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapDataInfo memberInfo;
    private MapDataItemInfo itemInfo;
    private int a;
    private int b;

}
@Getter
@Setter
@ToString
class MapDataInfo{
    private String memberId;
    private String memberName;
}
@Getter
@Setter
@ToString
class MapDataItemInfo{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private List<MapDataItemImg> imgList;
}
@Getter
@Setter
@ToString
class MapDataItemImg{
    private String imgName;
    private int imgCode;
}
