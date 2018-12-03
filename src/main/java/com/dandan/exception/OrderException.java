package com.dandan.exception;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.Enum.PayStatusEnum;
import lombok.Data;

/**
 * @author 一笑奈何
 * @create 2018-11-28 20:40
 * @desc
 **/
@Data
public class OrderException extends RuntimeException {
    private Integer code;
    public OrderException(PayStatusEnum payStatusEnum){
        super(payStatusEnum.getMessage());
        this.code=payStatusEnum.getCode();
    }
    public OrderException(OrderStatusEnum orderStatusEnum){
            super(orderStatusEnum.getMessage());
            this.code=orderStatusEnum.getCode();
        }
    public OrderException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
