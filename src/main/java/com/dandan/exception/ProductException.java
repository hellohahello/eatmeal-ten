package com.dandan.exception;

import com.dandan.Enum.ProductStatusEnum;
import lombok.Data;

/**
 * @author 一笑奈何
 * @create 2018-11-28 15:00
 * @desc
 **/
@Data
public class ProductException extends RuntimeException{
    private Integer code;
    public ProductException(ProductStatusEnum productStatusEnum){
        super(productStatusEnum.getMessage());
        this.code=productStatusEnum.getCode();
    }
    ProductException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
