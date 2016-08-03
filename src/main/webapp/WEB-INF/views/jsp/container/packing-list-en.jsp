<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    //    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>PACKING LIST</title>
<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/container/css/packing-list.css"/>"/>
<style type="text/css">

</style>
</head>

<body>
<div class="header">
    <span class="title">
        PACKING LIST
    </span>
</div>

<div class="pre-table-info">
    <span class="info-item" style="margin-left: 55px; ">POL：${container.shipmentPort}</span>
    <span class="info-item" style="margin-left: 65px; ">PORT OF DESTINATION：${container.destinationPort}</span>
    <span class="info-item" style="margin-left: 170px; ">NO：</span>
    <%--<span class="info-item" style="margin-left: 60px; ">0/0：</span>--%>
</div>

<table id="main-table" style="" border="0" cellpadding="0" cellspacing="1">
    <tr>
        <td class="td60" style="height: 42px">PRODUCT<br/>NAME</td>
        <td class="td85">COMPANY<br/>MODEL&nbsp;NO</td>
        <td class="td40">PACKING<br/>METHOD</td>
        <td class="td40">UNIT</td>
        <td class="td40">FUN</td>
        <td class="td75">CARTON</td>
        <td class="td40">QTY</td>
        <td class="td50">G.W.<br/>/N.W.</td>
        <td class="td40">CTNS</td>
        <td class="td45">TOTAL<br/>QTY</td>
        <td class="td45">TOTAL<br/>CBM</td>
        <td class="td45">TOTAL<br/>G.W.</td>
        <td class="td45">TOTAL<br/>N.W.</td>
    </tr>
    <c:forEach items="${itemList}" var="item" varStatus="status">
        <tr>
            <td class="td60" style="height: 31px">
                ${item.companyProductName}
            </td>
            <td class="td85">
                    ${item.companyProductNo}
            </td>
            <td class="td40">
                    ${item.packageForm}
            </td>
            <td class="td40">
                    ${item.unit}
            </td>
            <td class="td40">
                    ${item.functionDescription}
            </td>
            <td class="td75">
                    ${item.cartonSize}
            </td>
            <td class="td40">
                    ${item.packingQuantity}
            </td>
            <td class="td50">
                    ${item.grossWeight}/${item.netWeight}
            </td>
            <td class="td40">
                    ${item.orderedCartonQuantity}
            </td>
            <td class="td45">
                    ${item.packingQuantity * item.orderedCartonQuantity}
            </td>
            <td class="td45">
            </td>
            <td class="td45">
                    ${item.grossWeight * item.orderedCartonQuantity}
            </td>
            <td class="td45">
                    ${item.netWeight * item.orderedCartonQuantity}
            </td>
        </tr>
    </c:forEach>

    <c:forEach begin="${fn:length(itemList)}" end="26" step="1">
        <tr>
            <td class="td60" style="height: 31px"></td>
            <td class="td85"></td>
            <td class="td40"></td>
            <td class="td40"></td>
            <td class="td40"></td>
            <td class="td75"></td>
            <td class="td40"></td>
            <td class="td50"></td>
            <td class="td40"></td>
            <td class="td45"></td>
            <td class="td45"></td>
            <td class="td45"></td>
            <td class="td45"></td>
        </tr>
    </c:forEach>
    <tr>
        <td class="td60" style="height: 31px">合计</td>
        <td class="td85"></td>
        <td class="td40"></td>
        <td class="td40"></td>
        <td class="td40"></td>
        <td class="td75"></td>
        <td class="td40"></td>
        <td class="td50"></td>
        <td class="td40"></td>
        <td class="td45"></td>
        <td class="td45"></td>
        <td class="td45"></td>
        <td class="td45"></td>
    </tr>
</table>

<div class="after-table-info">
    <span class="info-item" style="margin-left: 50px; ">LOADING DATE：</span>
    <span class="info-item" style="margin-left: 95px; ">SO&nbsp;NO.：</span>
    <span class="info-item" style="margin-left: 100px; ">CTR&nbsp;NO.：</span>
    <span class="info-item" style="margin-left: 100px; ">SEAL&nbsp;NO.：</span>
</div>


<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/container/js/preloaded.js"/>"></script>
<script>
</script>


</body>
</html>
