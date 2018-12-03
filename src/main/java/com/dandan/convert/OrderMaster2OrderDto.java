package com.dandan.convert;

import com.dandan.dto.OrderDto;
import com.dandan.entity.OrderMaster;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 一笑奈何
 * @create 2018-11-29 8:23
 * @desc
 **/
@Data
public class OrderMaster2OrderDto {
    /*
    类的转化
     */
    public static OrderDto convertClass(OrderMaster orderMaster){
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }
    /*
    由类到集合间的转化
     */
    public static List<OrderDto> convertList(List<OrderMaster> orderMasterList){
        List<OrderDto> orderDtoList=orderMasterList.stream().map(e->convertClass(e)).collect(Collectors.toList());
        return orderDtoList;
    }
}
