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

    <title>主页</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width:100%;
            height:100%;
            font-family: 微软雅黑;
            background-color: whitesmoke;
            overflow-y:hidden;
        }
    </style>
    <script type="text/javascript">
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
    <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-collapse">
                        <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span>
                    </button>
                <%--    <a class="navbar-brand"
                       href="${pageContext.request.contextPath}/index.action"
                    > <img
                            src="${pageContext.request.contextPath}/res/img/logo2.png"
                            style="height: 50px; margin-top: -15px;" /></a>--%>
                </div>
                <div>
                    <div class="collapse navbar-collapse">

                        <ul class="nav navbar-nav">
                          <li id="homepage"><a href="${pageContext.request.contextPath}/index.jsp" target="main">首页</a></li>
                        </ul>

                        <!--已登陆-->
                        <c:if test="${!empty user}">
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                        data-toggle="dropdown">${sessionScope.user.username}<b class="caret"></b>
                                </a>
                                    <ul class="dropdown-menu ">
                                        <!--卖家-->
                                       <c:if test="${sessionScope.user.type==0}">
                                            <li><a href="${pageContext.request.contextPath}/addgoods.jsp"
                                                    target="main"><span class="glyphicon glyphicon-plus"></span>发布</a></li>

                                            <li class="divider"></li>
                                        </c:if>
                                        <!--买家-->
                                        <c:if test="${sessionScope.user.type==1}">
                                            <li><a href="${pageContext.request.contextPath}/bill.jsp" target="main">
                                                <span class="glyphicon glyphicon-yen"></span>财务</a></li>

                                            <li class="divider"></li>
                                            <li><a href="${pageContext.request.contextPath}/shoppingcart.jsp" target="main">
                                                <span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
                                            <li class="divider"></li>
                                        </c:if>
                                        <li><a
                                                href="${pageContext.request.contextPath}/logout.action"><span
                                                class="glyphicon glyphicon-off"></span>退出</a></li>
                                    </ul></li>

                            </ul>
                        </c:if>

                        <!--未登陆-->
                        <c:if test="${empty user}">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="${pageContext.request.contextPath}/login.jsp"  style="background-color: red" >登陆</a></li>
                            </ul>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

</nav>

<div style="width: 100%; height: 100%;margin-top:50px;margin-bottom: 36px;">
    <iframe frameborder="0" name="main" style="width: 100%; height: 100%;" src="${pageContext.request.contextPath}/index.jsp"></iframe>
</div>
<nav class="navbar-fixed-bottom" role="navigation" style="background-color: lightgray;height: 36px;text-align: center;padding: 8px;">
    <p style="font-size: 12px;font-family: 微软雅黑;color: grey;">Copyright © 2018. Zhou Yang All rights reserved.</p>
</nav>
</body>
</html>
