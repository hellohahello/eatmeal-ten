package com.dandan.service;

import com.dandan.entity.ProductCategory;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:06
 * @desc
 * @Version 1.0
 **/
public interface ProductCategoryService {
    /*
    发现单个
     */
    ProductCategory findOne(Integer categoryId);

    /*
    全部
     */
    List<ProductCategory> findAll();

    /*
    保存
     */
    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> integerList);
}
