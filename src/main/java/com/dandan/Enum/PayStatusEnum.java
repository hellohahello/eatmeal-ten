package com.dandan.Enum;

import lombok.Getter;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:48
 * @desc
 **/
@Getter
public enum  PayStatusEnum implements CodeEnum{
    PAY_STATUS_ERROR(500,"支付状态不正确"),
    SUCCESS_PAY(2,"支付成功"),
    WAIT_PAY(1,"等待支付"),
    NOT_PAY(0,"未支付");
    private Integer code;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;
}
