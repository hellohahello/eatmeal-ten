package com.dandan.service.impl;

import com.dandan.dto.OrderDto;
import com.dandan.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    protected final String OPENID="123321";
    protected final String orderId="1543451731870";
@Autowired
private OrderServiceImpl orderService;
    @Test
    public void create() {
        OrderDto orderDto=new OrderDto();
        OrderDetail orderDetail=new OrderDetail();
        OrderDetail orderDetail1=new OrderDetail();
        orderDetail.setProductId("002");
        orderDetail1.setProductId("001");
        orderDetail.setProductQuantity(1);
        orderDetail1.setProductQuantity(1);
        orderDetail.setProductName("华宇超市女生呀");
        orderDetail1.setProductName("冯提莫呀");
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);
        orderDto.setBuyerName("我就是那个李朋呀!!!");
        orderDto.setBuyerOpenid("erhr2233");
        orderDto.setBuyerAddress("柘山镇三村");
        orderDto.setBuyerPhone("13858625");
        orderDto.setOrderDetailList(orderDetailList);
        orderService.create(orderDto);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne(orderId);
        Assert.assertNotEquals(null,orderDto.getOrderDetailList());
    }

    @Test
    public void findList() {
        PageRequest pageRequest=new PageRequest(0,3);
        Page<OrderDto> page = orderService.findList(OPENID, pageRequest);
        Assert.assertNotEquals(null,page);
    }

    @Test
    public void findList1() {
        PageRequest request=new PageRequest(0,5);
        Page<OrderDto> list = orderService.findList(request);
        Assert.assertNotEquals(null,list);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne(orderId);
        OrderDto cancel = orderService.cancel(orderDto);
        Assert.assertEquals(new Integer(1),cancel.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDto one = orderService.findOne(orderId);
        OrderDto paid = orderService.paid(one);
        Assert.assertEquals(new Integer(2),paid.getPayStatus());
    }

    @Test
    public void finish() {
    }
}