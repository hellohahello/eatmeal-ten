package com.dandan.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 一笑奈何
 * @create 2018-11-28 20:46
 * @desc 购物车
 **/
@Slf4j
@Data
public class CartDto {
    private String productId;
    private Integer productQuantity;

    public CartDto() {
    }

    public CartDto(String productId, Integer productQuantity) {

        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
