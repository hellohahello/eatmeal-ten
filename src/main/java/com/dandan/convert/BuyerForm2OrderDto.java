package com.dandan.convert;

import com.dandan.dto.OrderDto;
import com.dandan.entity.OrderDetail;
import com.dandan.form.BuyerForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-29 11:18
 * @desc
 **/
@Data
public class BuyerForm2OrderDto {
    public  static OrderDto convertClass(BuyerForm buyerForm){
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerAddress(buyerForm.getAddress());
        orderDto.setBuyerPhone(buyerForm.getPhone());
        orderDto.setBuyerOpenid(buyerForm.getOpenid());
        orderDto.setBuyerName(buyerForm.getName());
        /*
        gson字符串转成list
         */
        Gson gson=new Gson();
        List<OrderDetail> orderDetailList=gson.fromJson(buyerForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
