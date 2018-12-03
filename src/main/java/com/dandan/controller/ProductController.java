package com.dandan.controller;

import com.dandan.Enum.ProductStatusEnum;
import com.dandan.entity.ProductCategory;
import com.dandan.entity.ProductInfo;
import com.dandan.repository.ProductCategoryRepository;
import com.dandan.repository.ProductInfoRepository;
import com.dandan.utils.ResultVoUtils;
import com.dandan.vo.ProductCategoryVo;
import com.dandan.vo.ProductInfoVo;
import com.dandan.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author 一笑奈何
 * @create 2018-11-28 17:58
 * @desc
 **/
@Controller
@RequestMapping("/buyer/product")
public class ProductController {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductCategoryRepository categoryRepository;
    @GetMapping("/list")
    @ResponseBody
    public ResultVo resultVo(){
//        给客户展现所有在线的
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusIn(ProductStatusEnum.ON_LINE.getCode());
        //类目表的dao:查询category集合
        List<Integer> integerList = productInfoList.stream().
                map(e -> e.getCategoryType()).collect(Collectors.toList());
//        根据List<Integer>查询categoryType集合
        List<ProductCategory> categoryList = categoryRepository.findByCategoryTypeIn(integerList);
        List<ProductCategoryVo> categoryVoList=new Vector<>();
        for (ProductCategory productCategory : categoryList) {
            /*
            数据拼接
             */
            ProductCategoryVo productCategoryVo=new ProductCategoryVo();
            productCategoryVo.setCategoryName(productCategory.getCategoryName());
            productCategoryVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList=new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
//                只有属于同一类目才可以
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productCategoryVo.setCategoryFoods(productInfoVoList);
            categoryVoList.add(productCategoryVo);
        }
        ResultVo resultVo=new ResultVo();
        return ResultVoUtils.success(categoryVoList);
    }
}
