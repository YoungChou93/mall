<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.2.1.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>

    <title>登录</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width:100%;
            height:100%;
        }

        .banner {
            background-color: rgb(200, 200, 200);
            background-size: cover;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            width:100%;
            height:100%;
        }

        .banner-top {
            position: relative;
            top: 50px;
            width: 30%;
            padding: 20px 40px;
            background: rgba(228, 232, 246, 0.56);
            margin: 0 auto;
            min-width: 250px;
        }

        .error{
            font-family: 微软雅黑;
            font-size: 15px;
            color:red;
        }
    </style>
    <script type="text/javascript">
        $(function () {

            $("#emailError").css("display", "none");
            $("#passwordError").css("display", "none");
            $("#verifyCodeError").css("display", "none");
            /*
             * 2. 给注册按钮添加submit()事件，完成表单校验
             */
            $("#loginform").submit(function () {
                $("#msg").text("");
                var bool = true;
                if (!validateEmail()) {
                    bool = false;
                }

                if (!validatePassword()) {
                    bool = false;
                }
                return bool;
            });

            /*
             * 输入框得到焦点时隐藏错误信息
             */
            $("#inputEmail").focus(function () {
                $("#emailError").css("display", "none");
            });

            $("#inputPassword").focus(function () {
                $("#passwordError").css("display", "none");
            });

            /*
             *  输入框推动焦点时进行校验
             */
            $("#inputEmail").blur(function () {
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
        function validateEmail() {
            var bool = true;
            $("#emailError").css("display", "none");
            var value = $("#inputEmail").val();
            if (!value) {// 非空校验
                $("#emailError").css("display", "");
                $("#emailError").text("用户名不能为空！");
                bool = false;
            } else if (value.length < 2 || value.length > 40) {//长度校验
                $("#emailError").css("display", "");
                $("#emailError").text("账号长度在2 ~ 40之间！");
                bool = false;
            }
            return bool;
        }

        /*
         * 校验密码
         */
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

    </script>
</head>
<body>
<div class="banner">
    <div class="container">
        <h1 class="text-center" style="margin-top:5%;font-family: 微软雅黑"></h1>
        <div class="banner-top">
            <h2 class="text-center" style="font-family: 微软雅黑">登录</h2>
            <div class="tab-content" align="center">
                <div class="tab-pane fade in active" id="login">
                    <font color="red" id="msg">${errorMsg}</font>
                    <form class="form-horizontal" id="loginform" method="post" style="margin: 20px;"
                          action="${pageContext.request.contextPath}/user/login.action">
                        <div class="form-group">
                            <input type="text" name="username" class="form-control"
                                   id="inputEmail" placeholder="Username" value="">
                            <label id="emailError" class="error"></label>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control"
                                   id="inputPassword" placeholder="Password">
                            <label id="passwordError" class="error"></label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
