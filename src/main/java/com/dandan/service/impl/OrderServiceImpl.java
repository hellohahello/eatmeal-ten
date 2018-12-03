package com.dandan.service.impl;

import com.dandan.Enum.OrderStatusEnum;
import com.dandan.Enum.PayStatusEnum;
import com.dandan.Enum.ProductStatusEnum;
import com.dandan.convert.OrderMaster2OrderDto;
import com.dandan.dto.CartDto;
import com.dandan.dto.OrderDto;
import com.dandan.entity.OrderDetail;
import com.dandan.entity.OrderMaster;
import com.dandan.entity.ProductInfo;
import com.dandan.exception.OrderException;
import com.dandan.exception.ProductException;
import com.dandan.repository.OrderDetailRepository;
import com.dandan.repository.OrderMasterRepository;
import com.dandan.repository.ProductInfoRepository;
import com.dandan.service.OrderService;
import com.dandan.service.ProductInfoService;
import com.dandan.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 一笑奈何
 * @create 2018-11-28 20:17
 * @desc
 **/
@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    //订单主表id在一开始就应该生成了
   protected String orderId=KeyUtils.getKey();
    //订单总价
    private BigDecimal sumPrice=new BigDecimal(0);
    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
@Autowired
private ProductInfoRepository productInfoRepository;
    @Override
    public OrderDto create(OrderDto orderDto) {
    /*
    1. 从订单这个dto中先查询商品(单价,数量)
     */
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            String productId = orderDetail.getProductId();
            ProductInfo productInfo = productInfoRepository.findOne(productId);
            //没有查到的话
            if (productInfo==null){
                log.error("[创建订单]: 查询商品错误,商品不存在,productId={}",productId);
                throw new ProductException(ProductStatusEnum.PRODUCT_NOT_EXIST);
            }
             /*
    2.计算订单总价
     */
             sumPrice=productInfo.getProductPrice().multiply(BigDecimal.valueOf(orderDetail.getProductQuantity())).add(sumPrice);
              /*
    订单详情入库
     */
              orderDetail.setOrderId(orderId);
              orderDetail.setDetailId(KeyUtils.getKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            OrderDetail saveResult = detailRepository.save(orderDetail);
            if (saveResult==null){
                throw new OrderException(OrderStatusEnum.ORDER_UPDATE_DEFEAT);
            }
        }
        /*
    订单入库
     */

        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(sumPrice);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW_ORDER.getCode());
        orderMaster.setPayStatus(PayStatusEnum.NOT_PAY.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result==null){
            throw new OrderException(OrderStatusEnum.ORDER_UPDATE_DEFEAT);
        }

    /*
    最后扣库存
     */
    List<CartDto> cartDtoList=orderDetailList.stream().
            map(e->new CartDto(e.getProductId(),e.getProductQuantity())).
            collect(Collectors.toList());
    productInfoService.deleteStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        //每个orderId可能对应多个detail
        List<OrderDetail> orderDetailList = detailRepository.findByOrderIdIn(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(OrderStatusEnum.ORDER_DETAIL_NULL);
        }
        OrderDto orderDto = OrderMaster2OrderDto.convertClass(orderMaster);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {
        Page<OrderMaster> masterPage = orderMasterRepository.findByBuyerOpenidIn(openId, pageable);
        List<OrderMaster> orderMasterList = masterPage.getContent();
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convertList(orderMasterList);
        Page<OrderDto> orderDtoPage=new PageImpl<>(orderDtoList,pageable,masterPage.getTotalElements());
        return orderDtoPage;
    }


    @Override
    public OrderDto cancel(OrderDto orderDto) {
        /*
        只有新的才能删除
         */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("[取消]:取消错误,只有状态码为0的才能取消,code={} ",orderDto.getOrderStatus());
            throw new OrderException(OrderStatusEnum.CANCEL_ERROR);
        }
        /*
        如果是新的,则修改订单状态
         */
        orderDto.setOrderStatus(OrderStatusEnum.ORDER_CANCELED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master==null){
            log.error("[删除订单]:更新订单主表错误,master={}",master);
            throw new OrderException(OrderStatusEnum.ORDER_UPDATE_DEFEAT);
        }
       /*
       返回库存
        */
       if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
           log.error("[删除订单],订单详情为空,detail={}",orderDto);
           throw new OrderException(OrderStatusEnum.ORDER_STATUS_ERROR);
       }
       List<CartDto> cartDtoList=orderDto.getOrderDetailList().stream()
               .map(e->new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
       productInfoService.addStock(cartDtoList);
        //如果已支付,则需要退款
        // TODO
        return orderDto;
    }


    @Override
    public OrderDto finish(OrderDto orderDto) {
        /*
        判断订单状态
         */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("[完结]:完结错误,只有状态码为0的才能取消,code={} ",orderDto.getOrderStatus());
            throw new OrderException(OrderStatusEnum.ORDER_STATUS_ERROR);
        }
        /*
        修改订单状态
         */
        OrderMaster master=new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnum.ORDER_FINISHED.getCode());
        BeanUtils.copyProperties(orderDto,master);
        OrderMaster save = orderMasterRepository.save(master);
        if (save==null){
            log.error("[完结订单]:更新订单主表错误,master={}",master);
            throw new OrderException(OrderStatusEnum.ORDER_UPDATE_DEFEAT);
        }
        return orderDto;
    }


    @Override
    public OrderDto paid(OrderDto orderDto) {
        /*
        判断订单状态
         */
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("[支付订单],支付状态不正确,orderDto={}",orderDto.getOrderStatus());
            throw new OrderException(OrderStatusEnum.ORDER_STATUS_ERROR);
            }

        /*
        多了个判断支付状态
        只有等待支付的订单才能支付
         */
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT_PAY.getCode())){
            log.error("[支付订单],支付状态不正确,status={}",orderDto.getPayStatus());
            throw new OrderException(PayStatusEnum.PAY_STATUS_ERROR);
        }
        /*
        修改支付状态
         */
        orderDto.setPayStatus(PayStatusEnum.SUCCESS_PAY.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master==null){
            log.error("[支付订单]: 支付订单保存master失败,master={}",master);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();
        /*
        master集合化成DTO集合
         */
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convertList(orderMasterList);
        Page<OrderDto> orderDtoPage=new PageImpl<>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }
}
