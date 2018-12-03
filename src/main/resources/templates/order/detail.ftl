<html>
<meta charset="UTF-8">
<title>详情</title>
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <#--订单id和总金额-->
        <div class="col-md-4 column">
            <table class="table table-bordered table-hover">
                <thead>
            <tr>
                <th>订单id</th>
                <th>订单总金额</th>
            </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDetail.orderId}</td>
                    <td>${orderDetail.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <#--订单详情表数据-->
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品id</th>
                    <td>商品名称</td>
                    <td>商品价格</td>
                    <td>产品数量</td>
                    <td>商品总额</td>
                </tr>
                </thead>
                <tbody>
                <#list orderDetail.orderDetailList as orderDto2 >
                <tr class="success">
                    <td>${orderDto2.productId}</td>
                    <td>${orderDto2.productName}</td>
                    <td>${orderDto2.productPrice}</td>
                    <td>${orderDto2.productQuantity}</td>
                    <#--总价是个数*单价-->
                    <td>${orderDto2.productQuantity*orderDto2.productPrice}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
<#--完结订单 -->
           <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                       <#if orderDetail.getOrderMessage().message="新下单">
                           <ul class="pull-right">
                               <a type="button" class="btn btn-success btn-default" href="/sell/seller/order/finish?orderId=${orderDetail.orderId}">完结</a>
                           <#--取消订单====只有新下单状态才能有取消按钮和完结按钮-->
                               <a  href="/sell/seller/order/cancel?orderId=${orderDetail.orderId}" type="button" class="btn btn-default btn-danger">取消</a>
                           </ul>
                           <#else >
                           <#--否则就不显示-->
                       </#if>
                    </div>
                </div>
            </div>

    </div>
</div>
</body>
</html>