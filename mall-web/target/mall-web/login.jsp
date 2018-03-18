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
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery.md5.js"></script>

    <title>登录</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width:100%;
            height:100%;
            background-color: whitesmoke;
        }

        .banner-top {
            position:absolute;
            top:50%;
            left: 50%;
            width:350px;
            height: 250px;
            padding: 20px 30px;
            background: rgba(228, 232, 246, 0.56);
            min-width: 250px;
            margin-left: -175px;
            margin-top: -175px;
        }

        .error{
            font-family: 微软雅黑;
            font-size: 15px;
            color:red;
        }
    </style>
    <script type="text/javascript">
        $(function () {

            $("#usernameError").css("display", "none");
            $("#passwordError").css("display", "none");
            $("#verifyCodeError").css("display", "none");

            /*
             * 输入框得到焦点时隐藏错误信息
             */
            $("#inputUsername").focus(function () {
                $("#usernameError").css("display", "none");
            });

            $("#inputPassword").focus(function () {
                $("#passwordError").css("display", "none");
            });

            /*
             *  输入框推动焦点时进行校验
             */
            $("#inputUsername").blur(function () {
                var inputName = $(this).attr("name");
                invokeValidateFunction(inputName);
            })

            $("#inputPassword").blur(function () {
                var inputName = $(this).attr("name");
                invokeValidateFunction(inputName);
            })

        });

        /*
         * 输入input名称，调用对应的validate方法。
         * 例如input名称为：loginname，那么调用validateLoginname()方法。
         */
        function invokeValidateFunction(inputName) {
            inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
            var functionName = "validate" + inputName;
            return eval(functionName + "()");
        }

        /*
         * 校验登录名
         */
        function validateUsername() {
            var bool = true;
            $("#usernameError").css("display", "none");
            var value = $("#inputUsername").val();
            if (!value) {// 非空校验
                $("#usernameError").css("display", "");
                $("#usernameError").text("用户名不能为空！");
                bool = false;
            } else if (value.length < 2 || value.length > 40) {//长度校验
                $("#usernameError").css("display", "");
                $("#usernameError").text("账号长度在2 ~ 40之间！");
                bool = false;
            }
            return bool;
        }

        function validatePassword() {
            var bool = true;
            $("#passwordError").css("display", "none");
            var value = $("#inputPassword").val();
            if (!value) {// 非空校验
                $("#passwordError").css("display", "");
                $("#passwordError").text("密码不能为空！");
                bool = false;
            } else if (value.length < 5 || value.length > 15) {//长度校验
                $("#passwordError").css("display", "");
                $("#passwordError").text("密码长度必须在5 ~ 15之间！");
                bool = false;
            }
            return bool;
        }

        function loginmall() {
            $("#msg").text("");
            if (!validateUsername()) {
               return  false;
            }
            if (!validatePassword()) {
                return  false;
            }

            $("#inputPassword").val($.md5($("#inputPassword").val()));

           $("#loginform").submit();
        }

    </script>
</head>
<body>
    <div class="container">
        <div class="banner-top">
            <h2 class="text-center" style="font-family: 微软雅黑">登录</h2>
            <font color="red" id="msg" style="font-size: 10px;float: right;">${errorMsg}</font>
            <div class="tab-content" align="center">
                <div class="tab-pane fade in active" id="login">
                    <form class="form-horizontal" id="loginform" method="post" style="margin: 20px;"
                          action="${pageContext.request.contextPath}/login.action">
                        <div class="form-group">
                            <input type="text" name="username" class="form-control"
                                   id="inputUsername" placeholder="Username" value="">
                            <label id="usernameError" class="error" style="font-size: 10px;"></label>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control"
                                   id="inputPassword" placeholder="Password">
                            <label id="passwordError" class="error" style="font-size: 10px;"></label>
                        </div>
                        <button type="button" class="btn btn-primary btn-block" onclick="loginmall()">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
