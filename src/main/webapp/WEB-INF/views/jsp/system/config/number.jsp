<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<head>
    <meta charset="utf-8">
    <title>操作设置</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" href="<c:url value="/resources/system/config1/css/global.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/system/config1/css/auto279.css"/>">
    <style>
        body{
            background: transparent;
        }
    </style>
</head>
<body>
    <div class="page x-auto0">
        <div class="nav"></div>
        <div class="title"></div>
        <div class="offline" oncontextmenu="return(false)"></div>
        <div class="uprow" oncontextmenu="return(false)"></div>
        <div class="table">
            <div class="redLine x-auto1"></div>
            <div class="x-auto2">
                <table class="v-column x-auto3" border="1">
                    <thead>
                    <tr></tr>
                    <tr></tr>
                    </thead>
                    <tbody>
                    <tr></tr>
                    <tr></tr>
                    <tr></tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="downrow" oncontextmenu="return(false)"></div>
    </div>
    <div class="extrow" oncontextmenu="return(false)"></div>
    <div class="bgimg" style="">
        <div class="v-bgimg t-er-cont x-auto4" style="top: 0px;">
            <form id="form" action="<c:url value="/system/config/number"/>" method="post">
                <input type="text" name="startProductNumber" class="" style="position: absolute; top: 376px; left: 305px; width: 178px; height: 20px; border: 0px solid red;" />
                <input type="text" name="orderNumber" class="" style="position: absolute; top: 418px; left: 305px; width: 178px; height: 20px; border: 0px solid red;" />
                <%--<input type="text" name="startQuotationSerialNumber" class="" style="position: absolute; top: 459px; left: 265px; width: 175px; border: 0px solid red;" />--%>
                <a href="javascript:void(0);" id="confirm" style="position: absolute; display: block; width: 50px; height: 30px; top: 460px; left: 542px; border: 0px solid red;">
                    &nbsp;
                </a>
            </form>
        </div>
    </div>
    <div class="toolbar"></div>


    <script>

        $(function () {
            $("#confirm").click(function () {
                $("#form").submit();
            });
        })

    </script>

</body>
</html>