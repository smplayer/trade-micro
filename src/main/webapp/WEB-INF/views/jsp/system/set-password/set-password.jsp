<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="<c:url value="/resources/system/set-password/css/global.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/system/set-password/css/auto280.css"/>">
    <style>
        input[type=text] {
            background: transparent;
            text-align: center;
            font-size: 14px;
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
<div class="bgimg">
    <div class="v-bgimg t-er-cont x-auto4">
        <form id="form" action="<c:url value="/system/setPassword"/>" method="post">

            <input type="text" name="oldAdminPassword" value="${user.adminPassword}"
                   style="position: absolute; width: 120px; height: 18px; top: 145px; left: 400px; border: 1px solid red;" />
            <input type="text" name="newAdminPassword" value=""
                   style="position: absolute; width: 120px; height: 18px; top: 207px; left: 400px; border: 1px solid red;" />
            <input type="text" name="confirmNewAdminPassword" value=""
                   style="position: absolute; width: 120px; height: 18px; top: 262px; left: 400px; border: 1px solid red;" />

            <input type="text" name="oldStandardPassword" value="${user.standardPassword}"
                   style="position: absolute; width: 120px; height: 18px; top: 346px; left: 400px; border: 1px solid red;" />
            <input type="text" name="newStandardPassword" value=""
                   style="position: absolute; width: 120px; height: 18px; top: 408px; left: 400px; border: 1px solid red;" />
            <input type="text" name="confirmNewStandardPassword" value=""
                   style="position: absolute; width: 120px; height: 18px; top: 463px; left: 400px; border: 1px solid red;" />

            <a href="javascript:void(0);" id="confirm" style="position: absolute; display: block; width: 50px; height: 30px; top: 516px; left: 587px; border: 1px solid red;">
                &nbsp;
            </a>

        </form>
    </div>
</div>
<div class="toolbar"></div>


<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script>

    $(function () {
        $("#confirm").click(function () {
            $("#form").submit();
        });
    })

</script>

</body>
</html>