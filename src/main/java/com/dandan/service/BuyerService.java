package com.dandan.service;

import com.dandan.dto.OrderDto;

/**
 * @author 一笑奈何
 * @create 2018-11-29 14:57
 * @desc
 * @Version 1.0
 **/
public interface BuyerService {
    //发现一个订单
    OrderDto findOneOrder(String openId,String orderId);
    /*
    删除订单
     */
    OrderDto delete(String openId,String orderId);
}
