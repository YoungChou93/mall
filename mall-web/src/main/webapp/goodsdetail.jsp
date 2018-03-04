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
            width:100%;
            height:100%;
            font-family: 微软雅黑;
            background-color: whitesmoke;
        }

        .top{
            height: 350px;
            width: 100%;
        }

        .image{
            width: 350px;
            height: 350px;
            float: left;
            margin-right: 15px;
            margin-bottom: 15px;
        }

        .img{
            width: 100%;
            height: 100%;
        }

        .text{
            width:100%;
            margin: 20px;
            padding:10px;
        }


        .text h2 , p , div , span ,a{
            margin-top: 10px;
            margin-left: 5px;
        }
        .v-unit{
            padding-left:5px;
            padding-right: 5px;
            color:#999;
        }
        .v-value{
            color:#333;
            font-size:25px;
            font-weight: bold;
            color:#d22147;
        }
    </style>
    <script type="text/javascript">
        var number=1;
        $(function () {

            $("#add").click(function () {
                if(number<9999){
                    number++;
                    $("#number").text(number);
                }
            });
            $("#sub").click(function () {
                if(number>1){
                    number--;
                    $("#number").text(number);
                }
            });

        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="top">
            <div class="image">
                <c:choose>
                    <c:when test="${!empty goods.imageurl && goods.imageurl!=null}">
                        <img class="img" src="${goods.imageurl}" alt="${goods.title}" />
                    </c:when>
                    <c:otherwise>
                        <img class="img" src="${pageContext.request.contextPath}/${goods.imagepath}" alt="${goods.title}" />
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="text">
                <h2>${goods.title}</h2>
                <p>${goods.abstracts}</p>
                <div>
                    <span class="v-unit">¥</span><span class="v-value">${goods.price}</span>
                </div>
                <div class="form-inline">
                    <span>购买数量：</span>
                    <span class="glyphicon glyphicon-minus" id="sub"></span>
                    <span id="number">1</span>
                    <span class="glyphicon glyphicon-plus" id="add"></span>
                </div>
                <c:if test="${!empty user && sessionScope.user.type==0}">
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/goods/modify.action?id=${goods.id}"><i class="glyphicon glyphicon-pencil">编辑</i></a>
                </c:if>
            </div>
            </div>
            <div style="clear: left;">
            <ul class="nav nav-tabs" style="height: 40px;">
                <li role="presentation" class="active" ><h4>详细信息</h4></li>
            </ul>
                <p>${goods.content}</p>
            </div>
        </div>

    </div>
</div>

</body>
</html>
