package com.dandan.repository;

import com.dandan.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:54
 * @desc
 * @Version 1.0
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderIdIn(String orderId);
}
