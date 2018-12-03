package com.dandan.repository;

import com.dandan.entity.OrderMaster;
import com.dandan.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    private final String OPENID="123456";
    String orderId=KeyUtils.getKey();
@Autowired
private OrderMasterRepository orderMasterRepository;
    @Test
    @Transactional
    public void save() {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("山东");
        orderMaster.setBuyerName("李朋");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("15265647912");
        orderMaster.setOrderAmount(new BigDecimal(1000));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMaster.setOrderId(orderId);
        orderMasterRepository.save(orderMaster);
        Assert.assertNotEquals(null,orderMaster);
    }
    @Test
    public void findByBuyerOpenidIn(){
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenidIn(OPENID, pageRequest);
        Assert.assertNotEquals(null,page);
    }
}