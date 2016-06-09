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
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
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
<form action="<c:url value="/ajax/product/saveOrUpdate"/>" method="post" id="form">
    <input type="hidden" name="id" id="id" value="${product.id}"/>
    <input type="hidden" name="factoryId" id="factoryId" value="${product.factoryId}"/>
    厂名: <input type="text" value="" name="factoryName" id="factoryName" />
    <a href="javascript:void(0);" id="findExistingFactory">查新</a>
    <br/>
    公司品名: <input type="text" value="" name="companyProductName" /><br/>
    厂家品名: <input type="text" value="" name="factoryProductName" /><br/>
    厂价: <input type="text" value="" name="factoryPrice" /><br/>
    纸箱规格: <input type="text" value="" name="cartonSize" /><br/>

    <input id="btn-confirm" type="submit" value="提交" />
</form>


<div id="dialog-factorySelector" style="display: none;">
    <div class="template">
        <div class="factory">

        </div>
    </div>
    <div class="content">

    </div>
    <div class="close">关闭</div>
</div>


<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script>
    var productId = "${product.id}";
</script>
<script type="text/javascript" src="<c:url value="/resources/product/js/edit.js"/>"></script>
<script>

</script>

</body>
</html>
