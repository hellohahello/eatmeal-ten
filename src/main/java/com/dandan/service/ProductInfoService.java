package com.dandan.service;

import com.dandan.dto.CartDto;
import com.dandan.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 14:33
 * @desc
 * @Version 1.0
 **/
public interface ProductInfoService {
    //findOne
    ProductInfo findOne(String productId);

    //查询全部
    Page<ProductInfo> findAll(Pageable pageable);

    //插入
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void addStock(List<CartDto> cartDtoList);
    //减库存
   void deleteStock(List<CartDto> cartDtoList);
}
