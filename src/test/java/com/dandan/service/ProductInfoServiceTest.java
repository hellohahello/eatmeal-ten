package com.dandan.service;

import com.dandan.Enum.ProductStatusEnum;
import com.dandan.entity.ProductInfo;
import com.dandan.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {
@Autowired
private ProductInfoRepository productInfoRepository;
    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoRepository.findOne("001");
        Assert.assertNotEquals(null,productInfo);
    }

    @Test
    public void findList() {
        PageRequest pageRequest=new PageRequest(1,1);
        Page<ProductInfo> page = productInfoRepository.findAll(pageRequest);
        Assert.assertNotEquals(null,page);
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setCreateTime(new Date());
        productInfo.setProductName("李小霞");
        productInfo.setCategoryType(100);
        productInfo.setProductStock(100);
        productInfo.setProductId("003");
        productInfo.setProductPrice(new BigDecimal(1000));
        productInfo.setProductDescription("很好看啊...");
        productInfo.setProductIcon("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3156617520,1312214994&fm=27&gp=0.jpg");
        productInfo.setProductStatus(ProductStatusEnum.ON_LINE.getCode());
        productInfoRepository.save(productInfo);

    }
}