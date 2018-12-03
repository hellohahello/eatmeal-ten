package com.dandan.repository;

import com.dandan.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:27
 * @desc
 * @Version 1.0
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /*
    根据openId查询
     */
    Page<OrderMaster> findByBuyerOpenidIn(String openid, Pageable pageable);
}
