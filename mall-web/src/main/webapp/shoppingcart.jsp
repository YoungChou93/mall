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
            $.post("${pageContext.request.contextPath}/cart/list.action", function (result) {
                if (result.success) {
                    items = result.data;
                    show();
                } else {
                    alert(result.errormsg + result.errorinfo);
                }
            }, 'json');
        }

        function show() {
            $("#tablebody").empty();
            var number = 0;
            $.each(items, function (index, e) {
                number = index + 1;
                var divstring = "<tr>" +
                    "<th scope='row'>" + number + "</th>" +
                    "<td>" + e.gid.title + "</td>" +
                    "<td>" + formatNumber(e.number,index) + "</td>" +
                    "<td>" + e.gid.price + "</td></tr>"
                $("#tablebody").append(divstring);
            });
        }


        function formatNumber(number,index) {
            return " <span class='glyphicon glyphicon-minus'  onclick='sub("+index+")'></span>" +
                "<span id='number"+index+"'>"+number+"</span> " +
                "<span class='glyphicon glyphicon-plus'  onclick='addNumber("+index+")'></span>";
        }

        function addNumber(index) {
            var number=items[index].number;
            if(number<9999) {
                number++;
                items[index].number=number;
                $("#number"+index).text(number);
            }
        }

        function sub(index) {
            var number=items[index].number;
            if (number > 1) {
                number--;
                items[index].number=number;
                $("#number"+index).text(number);
            }
        }

        function goBack() {
            window.history.go(-1);
        }

        function buy() {
            $("#Dialog").modal('show');
        }

        function exectue() {
            var sidarray=[];
            var numberarray=[];

            for(var i=0;i<items.length;i++){
                sidarray.push(items[i].id);
                numberarray.push(items[i].number);
            }
            var sids=sidarray.join(",");
            var numbers=numberarray.join(",");

            if(sids==null || sids==""){
                alert("当前商品为空");
                return false;
            }

            $.post("${pageContext.request.contextPath}/bill/add.action",{
                sids :sids,
                numbers : numbers
            }, function (result) {
                if (result.success) {
                    $("#Dialog").modal('hide');
                    list();
                } else {
                    alert(result.errormsg + result.errorinfo);
                }
            }, 'json');
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
                <div class="panel panel-default" style="margin-top:10px;margin-bottom: 100px;">
                    <table class="table table-hover " id="localimage">
                        <thead>
                        <tr style="background-color: lightgray">
                            <th>序号</th>
                            <th>名称</th>
                            <th>数量</th>
                            <th>价格</th>
                        </tr>
                        </thead>
                        <tbody id="tablebody">
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="float: right;">
            <button class="btn btn-danger" onclick="goBack()">退出</button>
            <button class="btn btn-danger" onclick="buy()">购买</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="Dialog">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">系统提示</h4>
            </div>
            <div class="modal-body" id="DialogBody">
                <p style="font-size: 15px;">确定要购买吗？</p>
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-primary" onclick="exectue()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
</html>
