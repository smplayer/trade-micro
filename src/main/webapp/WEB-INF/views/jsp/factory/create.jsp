<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工厂录入</title>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    </style>
</head>

<body>
<form action="<c:url value="/factory/create"/>" method="post">

    厂名: <input type="text" value="" name="name" /><br/>
    主要产品: <input type="text" value="" name="mainProduct" /><br/>
    产品数量: <input type="text" value="" name="productQuantity" /><br/>
    联系人: <input type="text" value="" name="linkman" /><br/>
    联系号码: <input type="text" value="" name="contactNumber" /><br/>
    地址: <input type="text" value="" name="address" /><br/>
    备注: <input type="text" value="" name="remark" /><br/>

    <input type="submit" value="提交" />
</form>

<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>

</body>
</html>
