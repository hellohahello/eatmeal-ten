<!DOCTYPE html>
<html lang="en">

<body>

<#--最外围-->
<div id="wrapper" class="toggled">

<#--左侧导航栏====引入nav.ftl-->
<#include "../common/nav.ftl">

    <#--显示的内容-->
    <div id="page-content-wrapper">

        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-condensed table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>买家姓名</th>
                            <th>买家电话</th>
                            <th>买家地址</th>
                            <th>买家openId</th>
                            <th>订单金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDtoPage.getContent() as orderDto>
                        <tr>
                            <td>${orderDto.orderId}</td>
                            <td>${orderDto.buyerName}</td>
                            <td>${orderDto.buyerPhone}</td>
                            <td>${orderDto.buyerAddress}</td>
                            <td>${orderDto.buyerOpenid}</td>
                            <td>${orderDto.orderAmount}</td>
                            <td>${orderDto.getOrderMessage().message}</td>
                            <td>${orderDto.getPayMessage().message}</td>
                            <td>${orderDto.createTime}</td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a>
                            </td>
                            <td>
                                <#if orderDto.getOrderMessage().message=="新下单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}&page=${currentPage}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                <#--分页-->
                    <ul class="pagination pull-right">
                        <#if currentPage gt 1>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
                        </li>
                        <#else >
                        <li class="disabled">
                            <a href="">上一页</a>
                        </li>
                        </#if>
                    <#--分页====遍历总页数动态获取======1..表示从第一页开始-->
                    <#list 1..orderDtoPage.getTotalPages() as index>
                    <#--如果是当前页,则用属性"disabled"  设置为不可点击-->
                        <#if currentPage==index>
                    <li class="disabled">
                    <#--每一页用index表示-->
                        <a href="#">${index}</a>
                    </li>
                        <#else >
                    <li>
                    <#--每一页用index表示-->
                        <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                    </li>
                        </#if>

                    </#list>
            <#if currentPage lt orderDtoPage.getTotalPages()>
            <li> <a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
            <#else >
            <li class="disabled"><a href="#">下一页</a></li>
            </#if>

                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>