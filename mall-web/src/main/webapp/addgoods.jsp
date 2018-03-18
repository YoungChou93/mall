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

        .img{
            width:200px;
            height:200px;
            float: right;
            border: double;
            position: absolute;
            top:80px;
            right:20px;
        }
    </style>
    <script type="text/javascript">
        var gid=null;
        $(function () {
            $("#imageone").click(function () {
                $("#imagepath").show();
                $("#imageurl").hide();
            });
            $("#imagetwo").click(function () {
                $("#imagepath").hide();
                $("#imageurl").show();

            });

            $("#imageurl").blur(function () {
                $("#aimage").attr('src',$("#imageurl").val());
            })
        });


        function addGoods() {
            var form=document.getElementById("addform");
            var fd =new FormData(form);
            exectue("${pageContext.request.contextPath}/goods/add.action",fd);

        }

        function updateGoods() {
            var form=document.getElementById("addform");
            var fd =new FormData(form);
            fd.append("id",${goods.id});
            exectue("${pageContext.request.contextPath}/goods/update.action",fd);
        }

        function exectue(url,fd) {
            if(null==$("#title").val() || ""==$("#title").val()){
                alert("标题不能为空！");
                return false;
            }

            if(null==$("#price").val() || ""==$("#price").val()){
                alert("价格不能为空！");
                return false;
            }

            if(null==$("#abstracts").val() || ""==$("#abstracts").val()){
                alert("摘要不能为空！");
                return false;
            }

            if(null==$("#content").val() || ""==$("#content").val()){
                alert("正文不能为空！");
                return false;
            }

            if(($("#imageurl").val()==null || $("#imageurl").val()=="" )&& ($("#imagepath").val()==null || $("#imagepath").val()=="")){
                alert("图片不能为空！");
                return false;
            }

            if($("#title").val().length<2 || $("#title").val().length>80){
                alert("标题长度在2~80之间！");
                return false;
            }

            if($("#abstracts").val().length<2 || $("#abstracts").val().length>140){
                alert("摘要长度在2~140之间！");
                return false;
            }

            if($("#content").val().length<2 || $("#content").val().length>1000){
                alert("正文长度在2~1000之间！");
                return false;
            }

            if($("#price").val()>100000 || $("#price").val()<0){
                alert("价格在0~100000之间");
                return false;
            }

            if($("#imageurl").val().length>200){
                alert("图片链接超过长度！");
                return false;
            }


            $.ajax({
                url: url,
                type: 'POST',
                cache: false,
                data: fd,
                processData: false,
                contentType: false,
                dataType: "json",
                beforeSend: function () {

                },
                success: function (data) {
                    $('#waitDialog').modal('hide');
                    $("#waitDialogBody").empty();
                    if (data.success) {
                        gid=data.id;
                        var optionStr ="<p>操作成功！</p>";
                        $("#waitDialogBody").append(optionStr);
                        $("#title").val("");
                        $("#price").val("");
                        $("#abstracts").val("");
                        $("#content").val("");
                    } else {
                        var optionStr ="<p>失败！"+data.errormsg+"</p>";
                        $("#waitDialogBody").append(optionStr);
                    }
                    $('#waitDialog').modal('show');
                }
            });
        }


        function lookDetail(){
            if(gid==null){
                return false;
            }
            window.location.href = "${pageContext.request.contextPath}/goods/getdetail.action?id="+gid;
        }

        function backIndex(){
            window.location.href = "${pageContext.request.contextPath}/index.jsp";
        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <ul class="nav nav-tabs" style="margin-bottom: 20px;">
                <c:choose>
                    <c:when test="${!empty goods}">
                        <li role="presentation" class="active"><h3>内容编辑</h3></li>
                    </c:when>
                    <c:otherwise>
                        <li role="presentation" class="active"><h3>内容发布</h3></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        <form id="addform">
            <div class="form-group form-inline">
                <label >标题:</label>
                <input type="text" name="title" id="title" class="form-control" value="${goods.title}"/>
            </div>
            <div class="form-group form-inline">
                <label>价格:</label>
                <input type="number" name="price" id="price" class="form-control" value="${goods.price}"/>
            </div>
            <div class="form-group form-inline">
                <input type="radio" name="image"  checked="checked" id="imageone"/>上传图片
                <input type="radio" name="image" id="imagetwo"/>图片url
            </div>
            <div class="form-group">
                <input type="file" name="pic" class="form-control" id="imagepath" style="width: 60%;"/>
                <input type="text" name="imageurl" class="form-control" id="imageurl" style="width: 60%;display: none;" value="${goods.imageurl}"/>
            </div>

            <div class="form-group ">
                <label>摘要:</label>
                <input type="text" name="abstracts" id="abstracts" class="form-control" value="${goods.abstracts}"/>
            </div>
            <div class="form-group ">
                <label>正文:</label>
                <textarea  class="form-control" name="content" id="content" style="height: 150px;" value="">${goods.content}</textarea>
            </div>
            <c:choose>
                <c:when test="${!empty goods}">
                    <button type="button" onclick="updateGoods()" class="btn btn-primary" >保存</button>
                    <c:choose>
                        <c:when test="${!empty goods.imageurl}">
                            <img class="img" id="aimage" src="${goods.imageurl}" alt=""/>
                        </c:when>
                        <c:otherwise>
                            <img class="img" id="aimage" src="${pageContext.request.contextPath}/${goods.imagepath}" alt=""/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <button type="button" onclick="addGoods()" class="btn btn-primary" >发布</button>
                    <img class="img" id="aimage" src="" alt=""/>
                </c:otherwise>
            </c:choose>

        </form>

</div>
    </div>
</div>

<div class="modal fade " tabindex="-1" role="dialog" id="waitDialog">
    <div class="modal-dialog modal-sm" role="document" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">系统提示</h4>
            </div>
            <div class="modal-body" id="waitDialogBody">
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-primary" onclick="lookDetail()">查看内容</button>
                <button type="button" class="btn btn-primary" onclick="backIndex()">返回首页</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
</html>
