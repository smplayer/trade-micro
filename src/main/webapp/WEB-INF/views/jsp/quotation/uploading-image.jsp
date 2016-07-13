<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
//    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>上传图片</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/uploading-image.css"/>"/>
    <style type="text/css">
    </style>
</head>

<body>

<div id="container" style="padding: 40px 10px 0 10px;">
    <form id="form" name="form" method="post" enctype="multipart/form-data" action="<c:url value="/quotation/uploadImage"/>" style="text-align: left">
        <input type="hidden" name="id" value="${id}"/>
        <input type="file" name="product-image" style="
        /*width:70px;*/
        "/><br/><br/>
        <input type="submit" value="提交" style="border: 1px solid #000">
    </form>
</div>

<script>
</script>
<script type="text/javascript" src="<c:url value="/resources/common/project/js/uuid.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/uploading-image.js"/>"></script>
<script>

</script>

</body>
</html>
