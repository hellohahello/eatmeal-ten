package com.dandan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 17:43
 * @desc
 **/
@Data
public class ProductCategoryVo {
        /*
        分类名称
         */
        @JsonProperty("name")
    protected String categoryName;
    /*
    分类type
     */
    @JsonProperty("type")
    protected Integer categoryType;
    /*
    productInfo集合
     */
    @JsonProperty("foods")
    protected List<ProductInfoVo> categoryFoods;
}
