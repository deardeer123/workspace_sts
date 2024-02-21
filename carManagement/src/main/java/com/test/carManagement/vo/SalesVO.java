package com.test.carManagement.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesVO {
    private int salesNum;
    private String carBuyer;
    private String buyerTel;
    private String modelColor;
    private String salesDate;
    private  int modelNum;

    private CarVO carVO;

}
