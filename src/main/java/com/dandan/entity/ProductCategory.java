package com.dandan.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 一笑奈何
 * @create 2018-11-28 15:08
 * @desc
 **/
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
@Id
private Integer categoryId;
//分类名称
private String categoryName;
//类目
private Integer categoryType;
//    时间
protected Date createTime;
protected  Date updateTime;
}
