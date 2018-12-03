<html>
<meta charset="UTF-8">
<title>错误页</title>
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-warning">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                 出错啦...
                </h4> <strong>${msg}!!</strong> <a href="${url}" class="alert-link">2秒后自动返回删除操作页</a>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout('location.href="${url}"',2000)
</script>
</body>
</html>