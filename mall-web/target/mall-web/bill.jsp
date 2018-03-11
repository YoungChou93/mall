<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/bootstrap/js/jquery-2.2.1.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

    <title>添加</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width: 100%;
            height: 100%;
            font-family: 微软雅黑;
            background-color: whitesmoke;
        }
    </style>
    <script type="text/javascript">
        var items = null;

        $(function () {
            list();
        });

        function list() {
            $.post("${pageContext.request.contextPath}/bill/list.action", function (result) {
                if (result.success) {
                    items = result;
                    show();
                } else {
                    alert(result.errormsg + result.errorinfo);
                }
            }, 'json');
        }

        function show() {
            $("#tablebody").empty();
            var number = 0;
            $.each(items.data, function (index, e) {
                number = index + 1;
                var divstring = "<tr>" +
                    "<th scope='row'>" + number + "</th>" +
                    "<td><img style='width:80px;height: 80px;' src='" + formatImage(e)  + "'/></td>" +
                    "<td>" + e.title + "</td>" +
                    "<td>" + e.buytime + "</td>" +
                    "<td>" + e.number + "</td>" +
                    "<td>" + e.price + "</td></tr>"
                $("#tablebody").append(divstring);
            });
            $("#tablebody").append("<tr><td>"+items.total+"</td></tr>")
        }

        function formatImage(e) {
            if(e.imageurl!=null && e.imageurl!=""){
                return e.imageurl;
            }else{
                return "${pageContext.request.contextPath}/"+e.imagepath;
            }
        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div style="clear: left;">
                <ul class="nav nav-tabs" style="height: 40px;">
                    <li role="presentation" class="active"><h4>购物车</h4></li>
                </ul>
                <div class="panel panel-default" style="margin-top:10px;">
                    <table class="table table-hover " id="localimage">
                        <thead>
                        <tr style="background-color: lightgray">
                            <th>序号</th>
                            <th>图片</th>
                            <th>名称</th>
                            <th>时间</th>
                            <th>价格</th>
                        </tr>
                        </thead>
                        <tbody id="tablebody">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
