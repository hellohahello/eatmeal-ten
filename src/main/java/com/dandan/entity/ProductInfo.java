package com.dandan.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 一笑奈何
 * @create 2018-11-28 14:20
 * @desc
 **/
@Entity
@DynamicUpdate
@Data
public class ProductInfo {
    @Id
    //产品id
    private String productId;
//产品名称
    private String productName;
//    价格
    protected BigDecimal productPrice;
//    库存
    private Integer productStock;
//    描述
    private String productDescription;
//    图片
    private String productIcon;
//    状态
    private Integer productStatus;
//    类目
    private Integer categoryType;
//    创建时间
    private Date createTime;
//    更新时间
    private Date updateTime;
}
