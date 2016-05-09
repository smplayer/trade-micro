<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>产品编辑</title>
    <link href="<c:url value="/resources/product/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .template{
            display: none;
        }

        .factory {
            border-bottom: 1px solid #000000;
        }

    </style>
</head>

<body>
<form action="<c:url value="/product/create"/>" method="post">
    <input type="hidden" name="factory-id" id="factory-id"/>
    厂名: <input type="text" value="" name="name" id="factoryName" />
    <a href="javascript:void(0);" id="findExistingFactory">查新</a>
    <br/>
    厂家品名: <input type="text" value="" name="factory-product-no" /><br/>
    公司品名: <input type="text" value="" name="company-product-no" /><br/>
    厂价: <input type="text" value="" name="factory-price" /><br/>
    纸箱规格: <input type="text" value="" name="carton-size" /><br/>

    <input id="confirm" type="submit" value="提交" />
</form>


<div id="dialog-factorySelector" style="position: absolute; top: 50%; left: 50%; display: none;">
    <div class="template">
        <div class="factory">

        </div>
    </div>
    <div class="content">

    </div>
    <div class="close">关闭</div>
</div>


<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/common/sm/sm.js"/>"></script>
<script>
    $(function () {
        $("#dialog-factorySelector .close").click(function () {
            $("#dialog-factorySelector").hide();
        });
        $(".factory").click(function () {
            alert($(this).html());
            $("#factory-id").val($(this).data("factory-id"));
        });

        $("#findExistingFactory").click(function (e) {
            e.preventDefault();
            var factoryName = $("#factoryName").val();
            $.get( "<c:url value="/factory/search"/>", {"name": factoryName}, function( data ) {
                $("#dialog-factorySelector .content").empty();
                $(data).each(function (i, factory) {
                    $("#dialog-factorySelector .content").append(
                            $("#dialog-factorySelector .template .factory").clone(true).data("factory-id", factory.id).html(factory.name)
                    );
                });
                $("#dialog-factorySelector").show();
            });
        });
    })
</script>

</body>
</html>
