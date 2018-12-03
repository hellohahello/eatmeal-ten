package com.dandan.controller;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.convert.BuyerForm2OrderDto;
import com.dandan.dto.OrderDto;
import com.dandan.exception.OrderException;
import com.dandan.form.BuyerForm;
import com.dandan.service.BuyerService;
import com.dandan.service.OrderService;
import com.dandan.utils.ResultVoUtils;
import com.dandan.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 一笑奈何
 * @create 2018-11-29 10:59
 * @desc
 **/
@Controller
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private OrderService orderService;
    /*
    创建订单
     */
    @PostMapping("/create")
    @ResponseBody
    public ResultVo<Map<String,Object>> create(@Valid BuyerForm buyerForm, BindingResult bindingResult){
       /*
       先判断是否有错误
        */
       if (bindingResult.hasErrors()){
        log.error("[创建订单],请检查表单,bindingResult={}",bindingResult.getFieldError().getDefaultMessage());
        throw new OrderException(OrderStatusEnum.ORDER_STATUS_ERROR);
       }
        OrderDto orderDto = BuyerForm2OrderDto.convertClass(buyerForm);
        OrderDto create = orderService.create(orderDto);
        Map map=new HashMap();
        map.put("orderId",create.getOrderId());
        return ResultVoUtils.success(map);
    }

    /*
    订单列表
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultVo<List<OrderDto>> listResultVo(@RequestParam("openid")String openid,
                                                 @RequestParam("page")Integer page,@RequestParam("size")Integer size){
        PageRequest request=new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, request);
        List<OrderDto> orderDtoList = orderDtoPage.getContent();
        return ResultVoUtils.success(orderDtoList);

    }
    /*
    查询订单详情
     */
    @GetMapping("/detail")
    @ResponseBody
    public ResultVo<OrderDto> detail(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){ ;
        OrderDto orderDto = buyerService.findOneOrder(openid, orderId);
        return ResultVoUtils.success(orderDto);
    }
    @GetMapping("/cancel")
    @ResponseBody
    public ResultVo cancel(@RequestParam("orderId")String orderId,@RequestParam("openid")String openid){
        OrderDto orderDto = buyerService.delete(openid, orderId);
        return ResultVoUtils.success(null);
    }
}
