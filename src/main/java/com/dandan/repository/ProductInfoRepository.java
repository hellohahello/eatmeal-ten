package com.dandan.repository;

import com.dandan.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 14:29
 * @desc
 * @Version 1.0
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    //根据分类查询
    List<ProductInfo> findByCategoryTypeIn(Integer categoryType);
    //在线商品
    List<ProductInfo> findByProductStatusIn(Integer integer);
}
