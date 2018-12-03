package com.dandan.dto;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.Enum.PayStatusEnum;
import com.dandan.entity.OrderDetail;
import com.dandan.utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:59
 * @desc
 **/
@Data
public class OrderDto {
    private String orderId;
    /*
    买家姓名
     */
    private String buyerName;
    /*
    买家电话
     */
    private String buyerPhone;
    /*
    地址
     */
    private String buyerAddress;
    /*
    买家openId
     */
    private String buyerOpenid;
    /*
    订单总额
     */
    private BigDecimal orderAmount;

    /*
    订单状态,默认0新订单
     */
    private Integer orderStatus;
    /*
    支付状态,默认0未支付
     */
    private Integer payStatus;
    /*
    创建时间
     */
    private Date createTime;
    /*
    更新时间
     */
    private Date updateTime;
    /*
    订单详情
     */
    private List<OrderDetail> orderDetailList;
@JsonIgnore
    public  OrderStatusEnum getOrderMessage() {
    return EnumUtils.getMessage(orderStatus, OrderStatusEnum.class);
}
@JsonIgnore
    public PayStatusEnum getPayMessage(){
        return EnumUtils.getMessage(orderStatus,PayStatusEnum.class);
}
}
