package com.dandan.Enum;

import lombok.Getter;

/**
 * @author 一笑奈何
 * @create 2018-11-28 14:45
 * @desc
 **/
@Getter
public enum ProductStatusEnum {
    PRODUCT_NOT_ENOUGH(1,"库存不足"),
    PRODUCT_NOT_EXIST(404,"商品不存在"),
ON_LINE(0,"在线")
    ;
private Integer code;
private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
