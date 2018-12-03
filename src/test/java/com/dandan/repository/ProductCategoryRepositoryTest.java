package com.dandan.repository;

import com.dandan.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
@Autowired
private ProductCategoryRepository productCategoryRepository;
    @Test
   public void save(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCreateTime(new Date());
        productCategory.setCategoryId(2);
        productCategory.setCategoryType(200);
        productCategory.setUpdateTime(new Date());
        productCategory.setCategoryName("超级女神");
        productCategoryRepository.save(productCategory);
    }
}