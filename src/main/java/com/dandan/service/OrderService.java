package com.dandan.service;

import com.dandan.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 一笑奈何
 * @create 2018-11-28 20:01
 * @desc
 * @Version 1.0
 **/
public interface OrderService {
    /*
    创建订单
     */
    OrderDto create(OrderDto orderDto);
    /*
    查询单个订单
     */
    OrderDto findOne(String orderId);
    /*
    根据openId
    查询全部订单
     */
    Page<OrderDto> findList(String openId,Pageable pageable);
    /*
    卖家端查询全部订单
     */
    Page<OrderDto> findList(Pageable pageable);
    /*
    取消订单
     */
    OrderDto cancel(OrderDto orderDto);
    /*
    支付订单
     */
    OrderDto paid(OrderDto orderDto);
    /*
    完结订单
     */
    OrderDto finish(OrderDto orderDto);
}
