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
    <title>首页</title>
    <style type="text/css">
        html, body {
            font-size: 100%;
            width: 100%;
            height: 100%;
            background-color: whitesmoke;
        }

        a:link{text-decoration:none;}
        a:visited{text-decoration:none;}
        a:hover{text-decoration:none;}
        a:active{text-decoration:none;}

        h3{
            margin:5px;
            font-size:18px;
        }
        .goods{
            width:100%;
            max-width: 260px;
            min-width: 180px;
            height: 350px;
            background-color: white;
            margin-bottom: 20px;
            box-shadow: 2px 2px 4px #888888;
        }
        .goods-bottom{
            margin: 10px;
            position: relative;
            bottom: 5px;
        }
        .image{
            width: 100%;
            height: 280px;
        }

        .img{
            width: 100%;
            height: 100%;
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

        .goods .had{
            float: right;
            text-align:center;
            font-size:15px;
            padding:10px;
        }


    </style>
    <script type="text/javascript">
        var items=null;
        var deleteIndex=-1;

        $(function () {
            list();
        });

function list(){
    $.post("${pageContext.request.contextPath}/goods/list.action", function (result) {
        if (result.success) {
            items=result.data;
            show();
        } else {
            alert(result.errormsg + result.errorinfo);
        }
    }, 'json');
}

        function show() {
            $("#content1").empty();
            $("#content2").empty();
            $("#content3").empty();
            $("#content4").empty();
            $.each(items,function (index,e) {
                var divstring="<div class='goods'>"+
                    "<a href='${pageContext.request.contextPath}/goods/getdetail.action?id="+e.id+"'>"+
                    "<div class='image'><img class='img' src='"+formatImage(e)+"' alt='...'></div>"+
                    "<h3>"+e.title+"</h3></a>"+
                    "<div class='goods-bottom'>"+
                    "<div><span class='v-unit'>¥</span><span class='v-value'>"+e.price+"</span>"+
                    formatHad(e,index)+
                    "</div>"+
                    "</div>  </div>";
                var i=index%4+1;
                $("#content"+i).append(divstring);
            });
        }

        function deleteItem(index) {
            deleteIndex=index;
            $('#Dialog').modal('show');
        }

        function exectue() {
            $.post("${pageContext.request.contextPath}/goods/delete.action",{
                id : items[deleteIndex].id
            },function (result) {
                if (result.success) {
                    $('#Dialog').modal('hide');
                    list();
                } else {
                    alert(result.errormsg + result.errorinfo);
                }
            }, 'json');
        }

        function formatImage(e) {
            if(e.imageurl!=null && e.imageurl!=""){
                return e.imageurl;
            }else{
                return "${pageContext.request.contextPath}/"+e.imagepath;
            }
        }

        function formatHad(e,index) {
            var show="已购买";
            var deleteit="";
            <c:if test="${!empty user && sessionScope.user.type==0}">
            show="已售出";
            deleteit="<button class='btn btn-default' style='float: right;' onclick='deleteItem("+index+")'>删除</button>"
            </c:if>
            if(e.number!=null && e.number!=0) {
                return "<span class='had'>"+show+"</span>";
            }else{
                return deleteit;
            }
        }
        
        function loadALL() {
            list();
            $("#all").addClass('active');
            $("#nobuy").removeClass('active');
        }

        function loadBuy() {
            $.post("${pageContext.request.contextPath}/goods/nobuy.action", function (result) {
                if (result.success) {
                    items=result.data;
                    show();
                } else {
                    alert(result.errormsg + result.errorinfo);
                }
            }, 'json');
            $("#nobuy").addClass('active');
            $("#all").removeClass('active');
        }
    </script>
</head>
<body>
<div >
    <c:if test="${!empty user && sessionScope.user.type==1}">
        <div class="row" style="margin-bottom: 10px;margin-top: 10px;">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active" id="all"><a  href="javascript:loadALL()">所有商品</a></li>
                    <li role="presentation" id="nobuy"><a  href="javascript:loadBuy()">未购买商品</a></li>
                </ul>
            </div>
        </div>
    </c:if>
    <div class="container-fluid" style="margin-top:20px;margin-bottom: 100px;">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-2" id="content1" ></div>
            <div class="col-md-2" id="content2"></div>
            <div class="col-md-2" id="content3" ></div>
            <div class="col-md-2" id="content4" ></div>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>
<div class="modal fade " tabindex="-1" role="dialog" id="Dialog">
<div class="modal-dialog modal-sm" role="document" >
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <h4 class="modal-title" id="mySmallModalLabel">系统提示</h4>
        </div>
        <div class="modal-body" id="waitDialogBody">
            <h3>确定要删除吗？</h3>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="exectue()">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div>
</body>
</html>
