package com.test.carManagement.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CarVO {
    private int modelNum;
    private String modelName;
    private int modelPrice;
    private String modelManufacturer;
}
