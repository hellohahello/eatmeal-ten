package com.dandan.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:50
 * @desc
 **/
@Data
@Entity
@DynamicUpdate
public class OrderDetail {
//详情id
    @Id
    private String detailId;
    /*
    masterId
     */
    private String orderId;
    /*
    产品id
     */
    private String productId;
    /*
    名称
     */
    private String productName;
    /*
    价格
     */
    private BigDecimal productPrice;
    /*
    订单上的产品数量
     */
    private Integer productQuantity;
    /*
    图片
     */
    private String productIcon;
    /*
    创建时间
     */
    private Date createTime;
    /*
    更新时间
     */
    private Date updateTime;
}
