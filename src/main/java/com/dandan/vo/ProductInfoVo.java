package com.dandan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 一笑奈何
 * @create 2018-11-28 17:50
 * @desc
 **/
@Data
public class ProductInfoVo {
    //id
    @JsonProperty("id")
    protected String productId;
//    名称
    @JsonProperty("name")
    private String productName;
//    价格
    @JsonProperty("price")
    private BigDecimal productPrice;
//    描述
    @JsonProperty("desc")
    private String productDescription;
    //图片
    @JsonProperty("icon")
    private String productIcon;
}
