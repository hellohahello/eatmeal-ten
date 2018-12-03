package com.dandan.entity;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.Enum.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:18
 * @desc
 **/
@Data
@Entity
@DynamicUpdate
public class OrderMaster {
//    订单id
    @Id
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
    private Integer orderStatus=OrderStatusEnum.NEW_ORDER.getCode();
        /*
        支付状态,默认0未支付
         */
        private Integer payStatus=PayStatusEnum.NOT_PAY.getCode();
        /*
        创建时间
         */
        private Date createTime;
        /*
        更新时间
         */
        private Date updateTime;
}
