package com.dandan.repository;

import com.dandan.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
@Autowired
private OrderDetailRepository orderDetailRepository;
    @Test
    public void findByOrderIdIn() {
        List<OrderDetail> list = orderDetailRepository.findByOrderIdIn("1543405518019");
        Assert.assertNotEquals(null,list);
    }
}