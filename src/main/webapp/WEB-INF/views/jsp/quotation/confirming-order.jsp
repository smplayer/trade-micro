<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>订单确认表</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/confirming-order.css"/>"/>

    <style type="text/css">
    </style>
</head>

<body>
<div class="main border">
    <div class="top topw">
        <div class="toptt">订单确认表</div>
    </div>
    <div class="topline toplw"></div>
    <div class="top3">
        <div class="topfl fl">TO：&nbsp;${quotation.customerName}</div>
        <div class="topf2 fl">币种：&nbsp;${quotation.currency}</div>
        <div class="fl">报价日期：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>
        <div class="fr topr2">${page.pageIndex}/${page.pageQuantity}</div>
    </div>
    <table width="735" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
        <tr>
            <td height="35" class="toptdbg td65">品名</td>
            <td class="toptdbg td85">货号</td>
            <td class="toptdbg td45">包装</td>
            <td class="toptdbg td40">单位</td>
            <td class="toptdbg td40">报价</td>
            <td class="toptdbg td45">功能</td>
            <td class="toptdbg td75">纸箱规格</td>
            <td class="toptdbg td45">装量</td>
            <td class="toptdbg td40">箱数</td>
            <td class="toptdbg td45">总体积</td>
            <td class="toptdbg td50">总金额</td>
        </tr>
        <c:forEach items="${page.dataList}" var="p" varStatus="status">
            <tr>
                <td height="26" class="ddtd tdbg">${p.companyProductName}</td>
                <td class="ddtd tdbg">${p.companyProductNo}</td>
                <td class="ddtd tdbg">${p.packageForm}</td>
                <td class="ddtd tdbg">${p.unit}</td>
                <td class="ddtd tdbg">${p.quotedPrice}</td>
                <td class="ddtd tdbg"></td>
                <td class="ddtd tdbg">${p.cartonSize}</td>
                <td class="ddtd tdbg">${p.packingQuantity}</td>
                <td class="ddtd tdbg">${p.orderedCartonQuantity}</td>
                <td class="ddtd tdbg">${p.totalVolume}</td>
                <td class="ddtd tdbg">${p.totalAmount}</td>
            </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(page.dataList)}" end="14" step="1">
            <tr>
                <td height="26" class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
                <td class="ddtd tdbg">&nbsp;</td>
            </tr>
        </c:forEach>
        <tr>
            <td height="26" class="ddtd tdbg">合计</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
        </tr>
    </table>
    <table width="735" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="22" colspan="6">&nbsp;</td>
        </tr>
        <tr>
            <td width="40">&nbsp;</td>
            <td width="150" align="left">确认签章:</td>
            <td width="160" align="left">贸易条款:</td>
            <td width="175" align="left">柜型:</td>
            <td width="100" align="right">装运港:</td>
            <td width="110" align="left">&nbsp;</td>
        </tr>
    </table>
</div>
<table width="845" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="157" align="center">中文/英文</td>
        <td width="199" align="center">公司/工厂货号</td>
        <td width="66" align="center">
            <a id="show-accumulative-total" href="javascript:void(0)">累计</a>
        </td>
        <td width="150" align="center">转订单管理</td>
        <td width="88" align="center">打印</td>
        <td width="185" align="center">
            <table width="84" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="9"><img src="<c:url value="/resources/common/project/images/left_03.png" />" width="9" height="8"/></td>
                    <td width="66" align="center">1-00/00</td>
                    <td width="9"><img src="<c:url value="/resources/common/project/images/right_03.png" />" width="9" height="8"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<p>&nbsp;</p>

<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/confirming-order.js"/>"></script>
<script>
    var ctx = '${pageContext.request.contextPath}';
    var pageIndex = '${page.pageIndex}';
    var pageSize = '${page.pageSize}';
    var quotationId = '${quotation.id}';

</script>

</body>
</html>
