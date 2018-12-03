package com.dandan.Enum;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    ORDER_CANCEL_SUCCESS(666,"取消成功"),
    ORDER_NOT_EXIST(404,"订单不存在"),
    ORDER_HOST_ERROR(503,"该订单不属于您哦"),
    ORDER_DETAIL_NULL(502,"订单详情为空"),
    ORDER_STATUS_ERROR(501,"订单状态不正确"),
    ORDER_UPDATE_DEFEAT(500,"订单更新失败"),
    CANCEL_ERROR(400,"订单取消失败"),
    ORDER_FINISHED(2,"已完结"),
    ORDER_CANCELED(1,"已取消"),
    NEW_ORDER(0,"新下单");
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
