<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登陆</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/index/css/index.css"/>"/>
    <style type="text/css">
        #password{
            font-size: 28px;
        }
    </style>
</head>

<body>
<div class="main">
    <form id="form" name="form" method="post" action="<c:url value="/user/login"/>">
        <table width="508" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="75" colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td width="285" height="55" align="right">账号：</td>
                <td width="223" align="left">
                    <input type="text" name="username" value="admin" id="username" class="username" readonly />
                </td>
            </tr>
            <tr>
                <td height="55" align="right">密码：</td>
                <td align="left"><input type="password" name="password" id="password" class="password"/></td>
            </tr>
            <tr>
                <td height="55" align="right">&nbsp;</td>
                <td align="center"><input type="image" src="<c:url value="/resources/index/images/indexbtn.png"/>" class="btn-login"/></td>
            </tr>
        </table>
    </form>
</div>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp">
</c:import>
<script>
    var error = '${param.error}';
</script>
<script type="text/javascript" src="<c:url value="/resources/user/js/login.js"/>"></script>

</body>
</html>
