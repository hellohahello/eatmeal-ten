package com.dandan.service.impl;

import com.dandan.Enum.ProductStatusEnum;
import com.dandan.dto.CartDto;
import com.dandan.entity.ProductInfo;
import com.dandan.exception.ProductException;
import com.dandan.repository.ProductInfoRepository;
import com.dandan.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 14:58
 * @desc
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if (productInfo==null){
            throw new ProductException(ProductStatusEnum.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void addStock(List<CartDto> cartDtoList) {
        /*
        遍历购物车
         */
        for (CartDto cartDto : cartDtoList) {
            String productId = cartDto.getProductId();
            ProductInfo productInfo = productInfoRepository.findOne(productId);
            if (productInfo==null){
                log.error("[添加库存],遍历购物车出错,productId={}",productId);
                throw new ProductException(ProductStatusEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void deleteStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            String productId = cartDto.getProductId();
            ProductInfo productInfo = productInfoRepository.findOne(productId);
            if (productInfo==null){
                log.error("[扣库存],遍历购物车出错,productId={}",productId);
                throw new ProductException(ProductStatusEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDto.getProductQuantity();
            if (result<0){
                throw new ProductException(ProductStatusEnum.PRODUCT_NOT_ENOUGH);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

//    @Override
//    public List<ProductInfo> findUpAll(Integer integer) {
//        productInfoRepository.findAll();
//    }
}
