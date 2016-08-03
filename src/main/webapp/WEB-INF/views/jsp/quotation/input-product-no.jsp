<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>录入货号</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <style type="text/css">
        body {
            padding-top: 100px;
        }
    </style>
</head>

<body>
<div id="container" style="width: 300px; margin: 0 auto; padding: 20px; border: 1px solid #000; background-color: white" >
    <form id="form" name="form" method="post" action="<c:url value="/quotation/inputProductNo"/>" target="_parent">
        <input type="hidden" value="${id}" name="id" />
        <textarea id="productNoString" name="productNoString" style="border: 1px solid #000; width: 300px; height: 200px"></textarea>
        <div style="width: 100%; text-align: right">
            <input type="image" src="<c:url value="/resources/common/project/images/tj.png" />" class="btn" id="submit" style="margin-top: 10px"/>
        </div>
    </form>
</div>
<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    $(function () {
        $("#submit").click(function (e) {
            e.preventDefault();
            $("#form").submit();
        });
    })
</script>
</body>
</html>
