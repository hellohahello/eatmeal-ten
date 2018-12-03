package com.dandan.service.impl;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.dto.OrderDto;
import com.dandan.exception.OrderException;
import com.dandan.service.BuyerService;
import com.dandan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 一笑奈何
 * @create 2018-11-29 14:58
 * @desc
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDto findOneOrder(String openId, String orderId) {
        return checkUser(openId,orderId);
    }

    @Override
    public OrderDto delete(String openId, String orderId) {
        OrderDto orderDto = checkUser(openId, orderId);
        /*
        如果要删除的没查到
         */
        if (orderDto==null){
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new OrderException(OrderStatusEnum.ORDER_NOT_EXIST);
        }
        return  orderService.cancel(orderDto);

    }
    public OrderDto checkUser(String openid,String  orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto==null){return  null;}
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[查询]: 请先验证您的身份正确,orderDto={}",orderDto);
            throw new OrderException(OrderStatusEnum.ORDER_HOST_ERROR);
        }
        return orderDto;
    }
}
