<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" href="<c:url value="/resources/system/set-password/css/global.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/system/set-password/css/auto280.css"/>">
    <style>
        body {
            background: transparent;
        }

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
                   style="position: absolute; width: 134px; height: 18px; top: 145px; left: 394px; border: 1px solid #000;"/>
            <input type="text" id="newAdminPassword" name="newAdminPassword" value=""
                   style="position: absolute; width: 134px; height: 18px; top: 207px; left: 394px; border: 1px solid #000;"/>

            <input type="text" name="oldStandardPassword" value="${user.standardPassword}"
                   style="position: absolute; width: 134px; height: 18px; top: 269px; left: 394px; border: 1px solid #000;"/>
            <input type="text" id="newStandardPassword" name="newStandardPassword" value=""
                   style="position: absolute; width: 134px; height: 18px; top: 331px; left: 394px; border: 1px solid #000;"/>

            <a href="javascript:void(0);" id="confirm"
               style="position: absolute; display: block; width: 50px; height: 30px; top: 488px; left: 587px; border: 0px solid #000;">
                &nbsp;
            </a>

        </form>
    </div>
</div>
<div class="toolbar"></div>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    var error = '${param.error}';
    $(function () {
        if (error != '') {
            dialogAlert("#dialog-alert", {
                textContent: '权限不足',
                onClose: function () {
                }
            });
        }


        $("#confirm").click(function () {
            var reg = /^.*[0-9]+.*[a-zA-Z]+.*$|^.*[a-zA-Z]+.*[0-9]+.*$/;
            var newAdminPassword = $.trim($("#newAdminPassword").val());
            var newStandardPassword = $.trim($("#newStandardPassword").val());
            if (newAdminPassword.length > 0 && (newAdminPassword.length < 6 || newAdminPassword.length > 12)) {
                dialogAlert("#dialog-alert", {
                    textContent: "密码长度必须为6-12位英文和数字混合",
                    onClose: function () {
                    }
                });
                return false;
            }
            if (newStandardPassword.length > 0 && (newStandardPassword.length < 6 || newStandardPassword.length > 12)) {
                dialogAlert("#dialog-alert", {
                    textContent: "密码长度必须为6-12位英文和数字混合",
                    onClose: function () {
                    }
                });
                return false;
            }
            if (newAdminPassword.length > 0 && newStandardPassword.length > 0 && !(reg.test(newAdminPassword) && reg.test(newStandardPassword))) {
                dialogAlert("#dialog-alert", {
                    textContent: "密码格式错误",
                    onClose: function () {
                    }
                });
                return false;
            }
            $("#form").submit();

        });
    })

</script>

</body>
</html>