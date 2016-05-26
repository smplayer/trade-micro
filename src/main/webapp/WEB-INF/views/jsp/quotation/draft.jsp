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
    <title>见客下单</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/draft.css"/>"/>
    <style type="text/css">
    </style>
</head>

<body>

<div id="container">

    <c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
    </c:import>
    <c:import url="/WEB-INF/views/jsp/quotation/common/quotation-header.jsp">
        <c:param name="title" value="见客下单"/>
        <c:param name="curPage" value="operating"/>
    </c:import>


    <form id="form" name="form" method="post" action="">
        <input type="hidden" id="quotation-id" value="${quotation.id}"/>
        <div class="top2 top2w">
            <div class="topfl fl">客户名：&nbsp;${quotation.customerName}</div>
            <div class="topf2 fl">日期：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>
            <div class="fl">币种：&nbsp;${quotation.currency}</div>

            <div class="fr topr2">
                <table width="98" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="9">
                            <c:if test="${page.pageIndex > 1}" var="notFirstPage">
                                <a href="
                                    <c:url value="/quotation/operating">
                                        <c:param name="pageIndex" value="${page.pageIndex - 1}"/>
                                        <c:param name="id" value="${quotation.id}"/>
                                    </c:url>
                                ">
                                    <img src="<c:url value="/resources/quotation/images/left1.png" />" width="9" height="8"/>
                                </a>
                            </c:if>
                            <c:if test="${not notFirstPage}">
                                <img src="<c:url value="/resources/quotation/images/left1.png" />" width="9" height="8"/>
                            </c:if>
                        </td>
                        <td width="66" align="center">
                            <a href="
                                <c:url value="/quotation/operating" >
                                    <c:param name="pageIndex" value="1"/>
                                    <c:param name="id" value="${quotation.id}"/>
                                </c:url>
                             ">1</a>
                            <span style="width: 10px; text-align: right; display: inline-block;">-</span><input id="newPageIndex" type="text" value="${empty page ? 0 : page.pageIndex}" style="width: 20px; text-align: center; border: 0; background-color: transparent;"
                        />/<span style="width: 20px; text-align: center; display: inline-block;">${empty page ? 0 : page.pageQuantity}</span>
                        </td>
                        <td width="9">
                            <c:if test="${page.pageIndex < page.pageQuantity}" var="notLastPage">
                                <a href="
                                    <c:url value="/quotation/operating">
                                        <c:param name="pageIndex" value="${page.pageIndex + 1}"/>
                                        <c:param name="id" value="${quotation.id}"/>
                                    </c:url>
                                ">
                                    <img src="<c:url value="/resources/quotation/images/right1.png" />" width="9" height="8"/>
                                </a>
                            </c:if>
                            <c:if test="${not notLastPage}">
                                <img src="<c:url value="/resources/quotation/images/right1.png" />" width="9" height="8"/>
                            </c:if>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="fr"><a href="javascript:findFactory();" id="find-factory">厂名查新</a></div>
        </div>
        <table id="main-table" style="width: 1330px;" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
            <tr>
                <td height="40" rowspan="2" class="td39 toptdbg">序号</td>
                <td rowspan="2" class="td50 toptdbg">图片</td>
                <td colspan="2" class="toptdbg">工厂</td>
                <td rowspan="2" class="td65 toptdbg">包装方式</td>
                <td rowspan="2" class="td45 toptdbg">单位</td>
                <td rowspan="2" class="td50 toptdbg">厂价</td>
                <td colspan="3" class="toptdbg">包装资料</td>
                <td rowspan="2" class="td65 toptdbg">下单箱数</td>
                <td rowspan="2" class="td55 toptdbg">报价</td>
                <td rowspan="2" class="td55 toptdbg">总数量</td>
                <td rowspan="2" class="td55 toptdbg">总体积</td>
                <td rowspan="2" class="td55 toptdbg">总金额</td>
                <td rowspan="2" class="td90 toptdbg">厂 名</td>
                <td rowspan="2" class="td70 toptdbg">联系人</td>
                <td rowspan="2" class="td100 toptdbg">手机/电话</td>
                <td rowspan="2" class="td150 toptdbg">备 注</td>
                <td rowspan="2" class="td40 toptdbg">全选</td>
            </tr>
            <tr>
                <td class="td75 toptdbg">品名</td>
                <td class="td65 toptdbg">货号</td>
                <td class="td85 toptdbg">纸箱规格</td>
                <td class="td50 toptdbg">装量</td>
                <td class="td70 toptdbg">毛/净重</td>
            </tr>

            <tr class="item" id="new-item">
                <td height="24" class="break tdbg line-number">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg"><input type="text" name="factoryProductName" /></td>
                <td class="break tdbg"><input type="text" name="factoryProductNo" /></td>
                <td class="break tdbg"><input type="text" name="packageForm" /></td>
                <td class="break tdbg"><input type="text" name="unit" /></td>
                <td class="break tdbg"><input type="text" name="factoryPrice" /></td>
                <td class="break tdbg"><input type="text" name="cartonSize" /></td>
                <td class="break tdbg"><input type="text" name="packingQuantity" /></td>
                <td class="break tdbg">
                    <input type="text" name="grossWeight" value="" style="width: 25px;" />/<input type="text" name="grossWeight" value="" style="width: 25px;" />
                </td>
                <td class="break tdbg"><input type="text" name="orderedCartonQuantity" /></td>
                <td class="break tdbg"><input type="text" name="quotedPrice" /></td>
                <td class="break tdbg"><input type="text" name="orderedProductQuantity" /></td>
                <td class="break tdbg"><input type="text" name="totalVolume" /></td>
                <td class="break tdbg"><input type="text" name="totalAmount" /></td>
                <td class="break tdbg"><input type="text" name="factoryName" /></td>
                <td class="break tdbg"><input type="text" name="linkman" /></td>
                <td class="break tdbg"><input type="text" name="contactNumber" /></td>
                <td class="break tdbg"><input type="text" name="remark" /></td>
                <td class="break tdbg"><input type="checkbox" name="id" value="" class="empty" /></td>
            </tr>
            <c:forEach items="${page.dataList}" var="p" varStatus="status">
                <tr class="item">
                    <td height="24" class="break tdbg line-number">
                            ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
                    </td>
                    <td class="break tdbg">${p.imageURL}</td>
                    <td class="break tdbg"><input class="${empty p.productId ? "" : "extracted-product"}" type="text" name="factoryProductName" value="${p.factoryProductName}" /></td>
                    <td class="break tdbg"><input class="${empty p.productId ? "" : "extracted-product"}" type="text" name="factoryProductNo" value="${p.factoryProductNo}" /></td>
                    <td class="break tdbg"><input type="text" name="packageForm" value="${p.packageForm}" /></td>
                    <td class="break tdbg"><input type="text" name="unit" value="${p.unit}" /></td>
                    <td class="break tdbg"><input type="text" name="factoryPrice" value="<fmt:formatNumber value="${p.factoryPrice}" maxFractionDigits="2"/>" /></td>
                    <td class="break tdbg"><input type="text" name="cartonSize" value="${p.cartonSize}" /></td>
                    <td class="break tdbg"><input type="text" name="packingQuantity" value="${p.packingQuantity}" /></td>
                    <td class="break tdbg">
                        <input type="text" name="grossWeight" value="<fmt:formatNumber value="${p.grossWeight}" maxFractionDigits="2"/>" style="width: 25px;" />/<input type="text" name="netWeight" value="<fmt:formatNumber value="${p.netWeight}" maxFractionDigits="2"/>" style="width: 25px;" />
                    </td>
                    <td class="break tdbg"><input type="text" name="orderedCartonQuantity" value="${p.orderedCartonQuantity}" /></td>
                    <td class="break tdbg"><input type="text" name="quotedPrice" value="<fmt:formatNumber value="${p.quotedPrice}" maxFractionDigits="${quotation.decimalPlaces}"/>" /></td>
                    <td class="break tdbg"><input type="text" name="orderedProductQuantity" value="${p.orderedProductQuantity}" /></td>
                    <td class="break tdbg"><input type="text" name="totalVolume" value="<fmt:formatNumber value="${p.totalVolume}" maxFractionDigits="2"/>" /></td>
                    <td class="break tdbg"><input type="text" name="totalAmount" value="<fmt:formatNumber value="${p.totalAmount}" maxFractionDigits="2"/>" /></td>
                    <td class="break tdbg"><input class="${not empty p.factoryId ? "found-factory":""}" type="text" name="factoryName" value="${p.factoryName}" /></td>
                    <td class="break tdbg"><input type="text" name="linkman" value="${p.linkman}" /></td>
                    <td class="break tdbg"><input type="text" name="contactNumber" value="${p.contactNumber}" /></td>
                    <td class="break tdbg"><input type="text" name="remark" value="${p.remark}" /></td>
                    <td class="break tdbg"><input type="checkbox" class="draft-id" name="id" value="${p.id}" id="${p.id}" /></td>
                </tr>
            </c:forEach>
            <c:forEach begin="${fn:length(page.dataList)}" end="17" step="1">
                <tr class="item">
                    <td height="24" class="break tdbg line-number">&nbsp;</td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                    <td class="break tdbg"></td>
                </tr>
            </c:forEach>
            <tr class="item">
                <td height="24" class="break tdbg line-number">&nbsp;</td>
                <td class="break tdbg">累计</td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg" id="accumulativeTotal-orderedCartonQuantity"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg" id="accumulativeTotal-orderedProductQuantity"></td>
                <td class="break tdbg" id="accumulativeTotal-totalVolume"></td>
                <td class="break tdbg" id="accumulativeTotal-totalAmount"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
                <td class="break tdbg"></td>
            </tr>
        </table>
        <table width="1330" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="28" colspan="5" align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
                <td width="1070" height="20" align="right" valign="top">
                    <a href="javascript:extractAsProduct();" id="extract-as-product">转产品库</a>
                </td>
                <td width="50" align="left" valign="top">&nbsp;</td>
                <td width="70" align="right" valign="top">
                    <input type="image" id="btn-save" src="<c:url value="/resources/quotation/images/save1.png" />" class="btn"/>
                </td>
                <td width="70" align="right" valign="top"><input type="image"
                                                                 src="<c:url value="/resources/quotation/images/del2.png" />"
                                                                 class="btn"/></td>
                <td width="70" align="center" valign="top">&nbsp;</td>
            </tr>
        </table>
    </form>

</div>



<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script>
    var pageIndex = '${page.pageIndex}';
    var pageSize = '${page.pageSize}';
    var createNewItemUrl = '<c:url value="/ajax/quotation/productItemDraft/create" />';
    var modifyItemPropUrl = '<c:url value="/ajax/quotation/productItemDraft/modifyProp" />';
    var findFactoryUrl = '<c:url value="/quotation/findFactoryForDraft" />';
    var generateProductsUrl = '<c:url value="/quotation/generateProducts" />';
</script>
<script type="text/javascript" src="<c:url value="/resources/common/project/js/uuid.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/draft.js"/>"></script>
<script>


    $("#pre-reset").click(function () {
        $("input[type=text]", "#form").each(function () {
            $(this).val("");
        });
        $("input[type=radio]", "#form").each(function () {
            this.checked = false;
        });
    });

</script>

</body>
</html>
