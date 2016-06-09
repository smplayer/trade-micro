<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>CONFIRMATION OF ORDER</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/confirming-order.css"/>"/>

    <style type="text/css">
        #dialog-accumulation table td {
            background-color: #ffffff;
        }
        #title{
            letter-spacing: normal;
        }
        .toplw {
            width: 320px;
        }
        #currency {
            width: 150px;
        }
        .toptdbg {
            font-size: 12px;
            line-height: 15px;
        }
    </style>
</head>

<body>
<div class="main border">
    <div class="top topw">
        <div id="title" class="toptt ">CONFIRMATION OF ORDER &nbsp;</div>
    </div>
    <div class="topline toplw"></div>
    <div class="top3">
        <div class="topfl fl">TO：&nbsp;${quotation.customerName}</div>
        <div id="currency" class="topf2 fl">CURRENCY：&nbsp;${quotation.currency}</div>
        <div id="quotation-date" class="fl">QUOTATION DATE：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>
        <div class="fr topr2">${page.pageIndex}/${page.pageQuantity}</div>
    </div>
    <table
            <%--width="735" --%>
            border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
        <tr>
            <td height="35" class="toptdbg td65">PRODUCT-<BR/>NAME</td>
            <td class="toptdbg td85">COMPANY<BR/>MODEL NO</td>
            <td class="toptdbg td45">PACKIN-<BR/>GMETH-<BR/>OD</td>
            <td class="toptdbg td40">UNIT</td>
            <td class="toptdbg td40">QUOTA-<BR/>TION</td>
            <td class="toptdbg td45">FUNC-<BR/>TION</td>
            <td class="toptdbg td75">CARTON</td>
            <td class="toptdbg td45">LOAD-<BR/>ING QU-<BR/>ANTITY</td>
            <td class="toptdbg td40">CTN<BR/>NO</td>
            <td class="toptdbg td45">TOTAL<BR/>QTY</td>
            <td class="toptdbg td50">TOTAL<BR/>CBM</td>
            <td class="toptdbg td50">TOTAL<BR/>AMOUNT</td>
        </tr>
        <c:forEach items="${page.dataList}" var="p" varStatus="status">
            <tr>
                <td height="26" class="ddtd tdbg">${p.companyProductName}</td>
                <td class="ddtd tdbg">${productNoFrom=='factory' ? p.factoryProductNo : p.companyProductNo}</td>
                <td class="ddtd tdbg">${p.packageForm}</td>
                <td class="ddtd tdbg">${p.unit}</td>
                <td class="ddtd tdbg">${p.quotedPrice}</td>
                <td class="ddtd tdbg">${p.functionDescription}</td>
                <td class="ddtd tdbg">${p.cartonSize}</td>
                <td class="ddtd tdbg">${p.packingQuantity}</td>
                <td class="ddtd tdbg">${p.orderedCartonQuantity}</td>
                <td class="ddtd tdbg">${p.orderedProductQuantity}</td>
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
            <td width="160" align="left">贸易条款:&nbsp;${quotation.tradeClause}</td>
            <td width="175" align="left">柜型:&nbsp;${quotation.containerType}</td>
            <td width="100" align="right">装运港:${quotation.shipmentPort}</td>
            <td width="110" align="left">&nbsp;</td>
        </tr>
    </table>
</div>
<table width="845" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" colspan="6">&nbsp;</td>
    </tr>
    <tr>
        <td width="157" align="center">
            <a href="<c:url value="/quotation/confirming/order?id=${param.id}" />" class="${empty param.lang || param.lang == 'cn' ? 'current' : ''}" />中文</a>
            /
            <a href="<c:url value="/quotation/confirming/order?id=${param.id}&lang=en" />"  class="${param.lang == 'en' ? 'current' : ''}"/>英文</a>
        </td>
        <td width="199" align="center">
            <a href="<c:url value="/quotation/confirming/order?id=${param.id}" />" class="${empty param.productNoFrom || param.productNoFrom == 'company' ? 'current' : ''}" />公司</a>
            /
            <a href="<c:url value="/quotation/confirming/order?id=${param.id}&productNoFrom=factory" />"  class="${param.productNoFrom == 'factory' ? 'current' : ''}"/>工厂货号</a>
        </td>
        </td>
        <td width="66" align="center">
            <a id="show-accumulative-total" href="javascript:void(0)">累计</a>
        </td>
        <td width="150" align="center">转订单管理</td>
        <td width="88" align="center">打印</td>
        <td width="185" align="center">
            <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                <c:param name="prePageImage" value="/resources/common/project/images/left_03.png"  />
                <c:param name="nextPageImage" value="/resources/common/project/images/right_03.png"  />
                <c:param name="pageIndex" value="${page.pageIndex}"  />
                <c:param name="pageQuantity" value="${page.pageQuantity}"  />
                <c:param name="url" value="/quotation/confirming/order?id=${param.id}"/>
            </c:import>
        </td>
    </tr>
</table>

<p>&nbsp;</p>

<div id="dialog-accumulation" style="display: none; background-color: #fff; width: 300px; height: 220px; border: 2px solid #a839a8;">
    <div style="text-align: right;">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"
                 style="width: 24px; height: 24px; margin: 10px 10px 0px 0px"/>
        </a>
    </div>
    <div>
        <h2 style="display: inline-block; font-weight: normal; letter-spacing: 24px; padding-left: 20px; margin: 0 0 0 0; border-bottom: 1px solid red; padding-bottom: 3px;">
            累计</h2>
    </div>
    <table style="width: 200px; background-color: #000; margin: 20px auto 0 auto" border="0" cellpadding="0"
           cellspacing="1">
        <tr>
            <td style="width: 100px; height: 30px; background-color: #f0c9f0">栏目</td>
            <td style="width: 100px; height: 30px; background-color: #f0c9f0">数据</td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px;">总箱数</td>
            <td style="width: 100px; height: 20px;" id="total-carton-quantity"></td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px;">总体积</td>
            <td style="width: 100px; height: 20px;" id="total-volume"></td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px;">总金额</td>
            <td style="width: 100px; height: 20px;" id="total-amount"></td>
        </tr>
    </table>
</div>


<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/confirming-order.js"/>"></script>
<script>
    var pageIndex = '${page.pageIndex}';
    var pageSize = '${page.pageSize}';
    var quotationId = '${quotation.id}';

</script>

</body>
</html>
