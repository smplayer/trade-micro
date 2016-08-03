<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>CONFIRMATION OF ORDER</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/confirming-order.css"/>"/>

    <style type="text/css">
        #dialog-accumulation table td {
            background-color: #ffffff;
        }
        #title{
            letter-spacing: normal;
            font-size: 18px;
        }
        .toplw {
            width: 280px;
            border-bottom:1px black solid;
        }
        #currency {
            width: 150px;
        }
        .toptdbg {
            font-size: 12px;
            line-height: 12px;
        }
        #page-number {
            width: 80px;
        }

        #data-table input[type=text] {
            width: 100%;
            height: 24px;
            text-align: center;
        }
    </style>
</head>

<body>
<div class="main border">
    <div class="top topw">
        <div id="title" class="toptt">CONFIRMATION OF ORDER &nbsp;</div>
    </div>
    <div class="topline toplw"></div>
    <div class="top3">
        <div class="topfl fl">TO：&nbsp;${quotation.customerName}</div>
        <div id="currency" class="topf2 fl">CURRENCY：&nbsp;${quotation.currency}</div>
        <div id="quotation-date" class="fl">QUOTATION DATE：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>
        <div id="page-number" class="fr topr2">${page.pageIndex}/${page.pageQuantity}</div>
    </div>
    <table id="data-table" width="620" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
        <tr>
            <td height="35" class="toptdbg td75">PRODUCT-<BR/>NAME</td>
            <td class="toptdbg td70">COMPANY<BR/>MODEL NO</td>
            <td class="toptdbg td55">PACKIN-<BR/>GMETH-<BR/>OD</td>
            <%--<td class="toptdbg td39">UNIT</td>--%>
            <td class="toptdbg td55">QUOTA-<BR/>TION</td>
            <td class="toptdbg td90">CARTON</td>
            <td class="toptdbg td45">LOAD-<BR/>ING&nbsp;QU-<BR/>ANTITY</td>
            <td class="toptdbg td50">CTN<BR/>NO</td>
            <td class="toptdbg td60">TOTAL<BR/>QTY</td>
            <td class="toptdbg td60">TOTAL<BR/>CBM</td>
            <td class="toptdbg td60">TOTAL<BR/>AMOUNT</td>
        </tr>
        <c:forEach items="${page.dataList}" var="p" varStatus="status">
            <tr class="item" id="${p.id}">
                <td height="28" class="ddtd tdbg">
                    <input type="text" name="factoryProductNameEn" value="${empty p.factoryProductNameEn ? p.factoryProductName : p.factoryProductNameEn}" />
                </td>
                <td class="ddtd tdbg ${p.syncToProduct ? "" : "notSyncToProduct"}">
                    ${productNoFrom=='factory' ? p.factoryProductNo : p.companyProductNo}
                </td>
                <td class="ddtd tdbg">
                    <input type="text" name="packageFormEn" value="${empty p.packageFormEn ? p.packageForm : p.packageFormEn}" />
                </td>
                <td class="ddtd tdbg">
                    <c:if test="${p.quotedPrice != 0}" var="hasQuotedPrice">
                        <fmt:formatNumber value="${p.quotedPrice}" maxFractionDigits="${quotation.decimalPlaces}" pattern="#.#" />
                    </c:if>
                </td>
                <td class="ddtd tdbg">${p.cartonSize}</td>
                <td class="ddtd tdbg">${p.packingQuantity}</td>
                <td class="ddtd tdbg cartonQuantity">${p.orderedCartonQuantity}</td>
                <td class="ddtd tdbg orderedProductQuantity">${p.orderedProductQuantity}</td>
                <td class="ddtd tdbg volume">
                    <fmt:formatNumber value="${p.totalVolume}" maxFractionDigits="1"  pattern="#.#"/>
                </td>
                <td class="ddtd tdbg amount">
                    <fmt:formatNumber value="${p.totalAmount}" maxFractionDigits="1" pattern="#.#" />
                </td>
            </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(page.dataList)}" end="23" step="1">
            <tr>
                <td height="28" class="ddtd tdbg">&nbsp;</td>
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
            <td height="28" class="ddtd tdbg">TOTAL</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg">&nbsp;</td>
            <td class="ddtd tdbg" id="sum-carton-quantity">&nbsp;</td>
            <td class="ddtd tdbg" id="sum-product-quantity">&nbsp;</td>
            <td class="ddtd tdbg" id="sum-volume">&nbsp;</td>
            <td class="ddtd tdbg" id="sum-amount">&nbsp;</td>
        </tr>
    </table>
    <table style="width: 620px; margin-left: 70px;" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="12" colspan="3"></td>
        </tr>
        <tr>
            <td width="170" height="32" align="left">SIGNED AND STAMPED:</td>
            <td width="160" align="left">TRADE TERMS:&nbsp;${quotation.tradeClauseType}${quotation.tradeClause}</td>
            <td width="115" align="left">CTR TYPE:&nbsp;
            ${quotation.containerType == '1' ? '20尺' : quotation.containerType == '2' ? '40尺' : quotation.containerType == '3' ? "40尺加高" : ''}</td>
        <tr>
        </tr>
            <td width="170" align="left">PORT OF SHIPMENT:&nbsp;${quotation.shipmentPort}</td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>
<table width="735" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" colspan="8">&nbsp;</td>
    </tr>
    <tr>
        <td width="40" align="center">&nbsp;</td>
        <td width="110" align="left">
            <a href="
            <c:url value="/quotation/confirming/order?id=${param.id}">
                <c:param name="productNoFrom" value="${empty param.productNoFrom ? 'company' : param.productNoFrom}" />
                <c:param name="pageIndex" value="${empty param.pageIndex ? 1 : param.pageIndex}" />
            </c:url>" class="${empty param.lang || param.lang == 'cn' ? 'current' : ''}" />中文</a>
            /
            <a href="
            <c:url value="/quotation/confirming/order?id=${param.id}&lang=en" >
                <c:param name="productNoFrom" value="${empty param.productNoFrom ? 'company' : param.productNoFrom}" />
                <c:param name="pageIndex" value="${empty param.pageIndex ? 1 : param.pageIndex}" />
            </c:url>" class="${param.lang == 'en' ? 'current' : ''}"/>英文</a>
        </td>
        <td width="140" align="left">
            <a href="
            <c:url value="/quotation/confirming/order?id=${param.id}" >
                <c:param name="lang" value="${empty param.lang ? 'cn' : param.lang}" />
                <c:param name="pageIndex" value="${empty param.pageIndex ? 1 : param.pageIndex}" />
            </c:url>" class="${empty param.productNoFrom || param.productNoFrom == 'company' ? 'current' : ''}" />公司</a>
            /
            <a href="<c:url value="/quotation/confirming/order?id=${param.id}&productNoFrom=factory" >
                <c:param name="lang" value="${empty param.lang ? 'cn' : param.lang}" />
                <c:param name="pageIndex" value="${empty param.pageIndex ? 1 : param.pageIndex}" />
            </c:url>" class="${param.productNoFrom == 'factory' ? 'current' : ''}"/>工厂货号</a>
        </td>
        <td width="60" align="left"><a id="show-accumulative-total" href="javascript:void(0)">累计</a></td>
        <td width="105" align="center">
            <a href="/${ctx}/order/generateOrderFromQuotation" id="order-generateOrderFromQuotation">转订单管理</a>
        </td>
        <td width="90" align="right">打印</td>
        <td width="150" align="right">
            <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                <c:param name="prePageImage" value="/resources/common/project/images/left_03.png"  />
                <c:param name="nextPageImage" value="/resources/common/project/images/right_03.png"  />
                <c:param name="pageIndex" value="${page.pageIndex}"  />
                <c:param name="pageQuantity" value="${page.pageQuantity}"  />
                <c:param name="url" value="/quotation/confirming/order?id=${param.id}&productNoFrom=${empty param.productNoFrom ? 'company' : param.productNoFrom}&lang=${empty param.lang ? 'cn' : param.lang}"/>
            </c:import>
        </td>
        <td width="40" align="center">&nbsp;</td>
    </tr>
</table>

<p>&nbsp;</p>



<div id="dialog-accumulation" style="display: none; background-color: #fff; width: 300px; height: 240px; border: 2px solid #a839a8;">
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
            <td style="width: 100px; height: 20px;">总数量</td>
            <td style="width: 100px; height: 20px;" id="total-product-quantity"></td>
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

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/confirming-order.js"/>"></script>
<script>
    var pageIndex = '${page.pageIndex}';
    var pageQuantity = '${page.pageQuantity}';
    var pageSize = '${page.pageSize}';
    var quotationId = '${quotation.id}';
    var generatedOrder = ${quotation.generatedOrder == true ? true : false};
</script>
</body>

</html>
