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

    <title>添加</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width:100%;
            height:100%;
            font-family: 微软雅黑;
        }
    </style>
    <script type="text/javascript">

        $(function () {
            $("#imageone").click(function () {
                $("#imagepath").show();
                $("#imageurl").hide();
            });
            $("#imagetwo").click(function () {
                $("#imagepath").hide();
                $("#imageurl").show();

            });
        });


        function addGoods() {

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

            var form=document.getElementById("addform");
            var fd =new FormData(form);

            $.ajax({
                url: "${pageContext.request.contextPath}/goods/add.action",
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
                    if (data.errormsg==null) {
                        var optionStr ="<p>发布成功！</p>";
                        $("#waitDialogBody").append(optionStr);
                        $("#title").val("");
                        $("#price").val("");
                        $("#abstracts").val("");
                        $("#content").val("");
                    } else {
                        var optionStr ="<p>发布失败！+data.errormsg</p>";
                        $("#waitDialogBody").append(optionStr);
                    }
                    $('#waitDialog').modal('show');
                }
            });


        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
        <form id="addform">
            <div class="form-group form-inline">
                <label >标题:</label>
                <input type="text" name="title" id="title" class="form-control"/>
            </div>
            <div class="form-group form-inline">
                <input type="radio" name="image"  checked="checked" id="imageone"/>上传图片
                <input type="radio" name="image" id="imagetwo"/>图片url
            </div>
            <div class="form-group">
                <input type="file" name="pic" class="form-control" id="imagepath"/>
                <input type="text" name="imageurl" class="form-control" id="imageurl" style="display: none"/>
            </div>
            <div class="form-group form-inline">
                <label>价格:</label>
                <input type="number" name="price" id="price" class="form-control"/>
            </div>
            <div class="form-group ">
                <label>摘要:</label>
                <input type="text" name="abstracts" id="abstracts" class="form-control"/>
            </div>
            <div class="form-group ">
                <label>正文:</label>
                <textarea  class="form-control" name="content" id="content" style="height: 150px;"></textarea>
            </div>
            <button type="button" onclick="addGoods()" class="btn btn-primary" >发布</button>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
</html>
