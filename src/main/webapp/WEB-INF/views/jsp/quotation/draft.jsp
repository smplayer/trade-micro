<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

    <c:import url="/WEB-INF/views/jsp/quotation/common/quotation-header.jsp">
        <c:param name="title" value="见客下单"/>
        <c:param name="curPage" value="operating"/>
    </c:import>


    <form id="form1" name="form1" method="post" action="">
        <input type="hidden" id="quotation-id" value="${quotation.id}"/>
        <div class="top2 top2w">
            <div class="topfl fl">客户名：&nbsp;${quotation.customerName}</div>
            <div class="topf2 fl">日期：&nbsp;${quotation.currency}</div>
            <div class="fl">币种：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>

            <div class="fr topr2">
                <table width="84" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="9"><img src="<c:url value="/resources/quotation/images/left1.png" />" width="9"
                                           height="8"/></td>
                        <td width="66" align="center">1-00/00</td>
                        <td width="9"><img src="<c:url value="/resources/quotation/images/right1.png" />" width="9"
                                           height="8"/></td>
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

            <c:forEach items="${page.dataList}" var="p" varStatus="status">
                <tr class="item">
                    <td height="24" class="break tdbg">
                            ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
                    </td>
                    <td class="break tdbg">${p.imageURL}</td>
                    <td class="break tdbg"><input type="text" name="factoryProductName" value="${p.factoryProductName}" /></td>
                    <td class="break tdbg"><input type="text" name="factoryProductNo" value="${p.factoryProductNo}" /></td>
                    <td class="break tdbg"><input type="text" name="packageForm" value="${p.packageForm}" /></td>
                    <td class="break tdbg"><input type="text" name="unit" value="${p.unit}" /></td>
                    <td class="break tdbg"><input type="text" name="factoryPrice" value="${p.factoryPrice}" /></td>
                    <td class="break tdbg"><input type="text" name="cartonSize" value="${p.cartonSize}" /></td>
                    <td class="break tdbg"><input type="text" name="packingQuantity" value="${p.packingQuantity}" /></td>
                    <td class="break tdbg">
                        <input type="text" name="grossWeight" value="${p.grossWeight}" style="width: 25px;" />/<input type="text" name="netWeight" value="${p.netWeight}" style="width: 25px;" />
                    </td>
                    <td class="break tdbg"><input type="text" name="orderedCartonQuantity" value="${p.orderedCartonQuantity}" /></td>
                    <td class="break tdbg"><input type="text" name="quotedPrice" value="${p.quotedPrice}" /></td>
                    <td class="break tdbg"><input type="text" name="orderedProductQuantity" value="${p.orderedProductQuantity}" /></td>
                    <td class="break tdbg"><input type="text" name="totalVolume" value="${p.totalVolume}" /></td>
                    <td class="break tdbg"><input type="text" name="totalAmount" value="${p.totalAmount}" /></td>
                    <td class="break tdbg"><input class=" ${not empty p.factoryId ? "found-factory":""}" type="text" name="factoryName" value="${p.factoryName}" /></td>
                    <td class="break tdbg"><input type="text" name="linkman" value="${p.linkman}" /></td>
                    <td class="break tdbg"><input type="text" name="contactNumber" value="${p.contactNumber}" /></td>
                    <td class="break tdbg"><input type="text" name="remark" value="${p.remark}" /></td>
                    <td class="break tdbg"><input type="checkbox" class="draft-id" name="id" value="${p.id}" id="${p.id}" /></td>
                </tr>
            </c:forEach>
            <c:forEach begin="${fn:length(page.dataList)}" end="19" step="1">
                <tr class="item">
                    <td height="24" class="break tdbg">&nbsp;</td>
                    <td class="break tdbg">&nbsp;</td>
                    <td class="break tdbg"><input type="text" name="factoryProductName" /></td>
                    <td class="break tdbg"><input type="text" name="factoryProductNo" /></td>
                    <td class="break tdbg"><input type="text" name="packageForm" /></td>
                    <td class="break tdbg"><input type="text" name="unit" /></td>
                    <td class="break tdbg"><input type="text" name="factoryPrice" /></td>
                    <td class="break tdbg"><input type="text" name="cartonSize" /></td>
                    <td class="break tdbg"><input type="text" name="packingQuantity" /></td>
                    <td class="break tdbg">
                        <input type="text" name="grossWeight" value="${p.grossWeight}" style="width: 25px;" />/<input type="text" name="grossWeight" value="${p.grossWeight}" style="width: 25px;" />
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
            </c:forEach>
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
                <td width="70" align="right" valign="top"><input type="image"
                                                                 src="<c:url value="/resources/quotation/images/save1.png" />"
                                                                 class="btn"/>
                </td>
                <td width="70" align="right" valign="top"><input type="image"
                                                                 src="<c:url value="/resources/quotation/images/del2.png" />"
                                                                 class="btn"/></td>
                <td width="70" align="center" valign="top">&nbsp;</td>
            </tr>
        </table>
    </form>

</div>


<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/common/project/js/uuid.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/draft.js"/>"></script>
<script>
    var createNewItemUrl = '<c:url value="/ajax/quotation/productItemDraft/create" />';
    var modifyItemPropUrl = '<c:url value="/ajax/quotation/productItemDraft/modifyProp" />';
    var findFactoryUrl = '<c:url value="/quotation/findFactory" />';
    var generateProductsUrl = '<c:url value="/quotation/generateProducts" />';


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
