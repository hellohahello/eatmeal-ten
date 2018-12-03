package com.dandan.controller;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.dto.OrderDto;
import com.dandan.exception.OrderException;
import com.dandan.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 一笑奈何
 * @create 2018-11-29 15:41
 * @desc 卖家订单
 **/
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
   @Autowired
   private OrderServiceImpl orderService;
   /*
   参数:page表示当前页
        size,每页的个数
    */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size,Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
//        List<OrderDto> list = orderDtoPage.getContent();
        map.put("orderDtoPage",orderDtoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);

    }
    /*
    取消订单
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,Map<String,Object> map,@RequestParam(value = "page",defaultValue = "1")
                               Integer page){
        /*
        取消成功和取消失败各有一层逻辑视图
         */
        try {
            OrderDto find = orderService.findOne(orderId);
            if (find==null){return null;}
            OrderDto orderDto = orderService.cancel(find);
        }catch (OrderException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        /*
        删除成功跳转到成功页
         */
        map.put("msg",OrderStatusEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        map.put("deletePage",page);
        return new ModelAndView("common/success",map);
    }

    /*
    详情
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String,Object> map){
        OrderDto orderDto=null;
        try {
            orderDto= orderService.findOne(orderId);
            if (orderDto==null){
                return null;
            }
        }catch (OrderException e){
            /*
            找不到时的错误页并返回原页面
             */
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDetail",orderDto);
        return new ModelAndView("order/detail",map);
    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDto finish=null;
        try{
                OrderDto orderDto = orderService.findOne(orderId);
                 finish = orderService.finish(orderDto);
            }catch (OrderException e){
                /*
                当出异常的时候,跳转到异常界面
                msg:错误信息
                url:出错返回首页
                 */
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("common/error",map);
            }
            map.put("msg",OrderStatusEnum.ORDER_FINISHED.getMessage());
        map.put("url","/sell/seller/order/list");
//        完结只返回success就好
            return new ModelAndView("common/success",map);
    }

}