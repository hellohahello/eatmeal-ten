package com.dandan.repository;

import com.dandan.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 15:12
 * @desc
 * @Version 1.0
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> integerList);
}
