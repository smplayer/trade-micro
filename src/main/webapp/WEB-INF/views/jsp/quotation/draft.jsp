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
    <title>操作版</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/draft.css"/>"/>
    <style type="text/css">
    </style>
</head>

<body>

<div id="container">

    <c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
        <c:param name="backgroundColor" value="#B47BFE"/>
        <c:param name="currentModule" value="quotation"/>
        <c:param name="currentSubModule" value="operating"/>
        <c:param name="title" value="操作版"/>
    </c:import>

    <div id="cus-list">
        <ul class="template hidden">
            <li class="text key-cus-name">
                客户名:
            </li>

            <c:forEach var="i" begin="1" end="10" varStatus="status" step="1" >
            <li class="favor-cus-name empty">
                <a href="<c:url value="/quotation/operating?empty=true"/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </li>
            </c:forEach>

            <li class="text more" id="set-favor-quotation-list">
               <a href="javascript:void(0);">更多</a>
            </li>
        </ul>
    </div>


        <input type="hidden" id="quotation-id" value="${quotation.id}"/>
        <div class="top2 top2w">
            <div class="topfl fl">客户名：&nbsp;${quotation.customerName}</div>
            <div class="topf2 fl">日期：&nbsp;<fmt:formatDate value="${quotation.lastQuotedDate}" pattern="MM-dd"/></div>
            <div class="fl">币种：&nbsp;${quotation.exchangeRate != 1 || true ? quotation.currency : ''}</div>

            <div class="fr topr2" style="margin-right: 50px">

                <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                    <c:param name="prePageImage" value="/resources/common/project/images/left1.png"  />
                    <c:param name="nextPageImage" value="/resources/common/project/images/right1.png"  />
                    <c:param name="pageIndex" value="${page.pageIndex}"  />
                    <c:param name="pageQuantity" value="${page.pageQuantity}"  />
                    <c:param name="url" value="/quotation/operating"/>
                </c:import>
            </div>
            <div class="fr"><a href="javascript:void(0);" id="find-factory" class="" alt="厂名查新">厂名查新</a></div>
            <div class="fr" style="margin-right: 30px;"><a href="javascript:void(0);" id="copy-item" class="create-item-after-enter" alt="复制下行">复制下行</a></div>
            <div class="fr" style="margin-right: 30px;">
                <c:if test="${quotation.generatedOrder}" var="generatedOrder" >
                    <a href="javascript:void (0);" id="inputProductNo" class="" alt="订单录入">订单录入</a>
                </c:if>
                <c:if test="${not generatedOrder}">
                    <a href="${ctx}/quotation/inputProductNo?id=${quotation.id}" id="inputProductNo" class="open-in-dialog" alt="订单录入">订单录入</a>
                </c:if>
            </div>
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
                <td rowspan="2" class="td40 toptdbg">
                    <a href="javascript:void(0);" id="select-all">全选</a>
                </td>
            </tr>
            <tr>
                <td class="td75 toptdbg">品名</td>
                <td class="td65 toptdbg">货号</td>
                <td class="td85 toptdbg">纸箱规格</td>
                <td class="td50 toptdbg">装量</td>
                <td class="td70 toptdbg">毛/净重</td>
            </tr>

            <tr class="${not empty param['empty'] || page.pageIndex > 1 ? 'hidden' : ''}" id="new-item">
                <form id="form" name="form" method="post" action="">
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
                        <input type="text" name="grossWeight" value="" style="width: 25px;" />/<input type="text" name="netWeight" value="" style="width: 25px;" />
                    </td>
                    <td class="break tdbg"><input type="text" name="orderedCartonQuantity" /></td>
                    <td class="break tdbg"><input type="text" name="quotedPrice" /></td>
                    <td class="break tdbg"><input type="text" name="orderedProductQuantity" readonly /></td>
                    <td class="break tdbg"><input type="text" name="totalVolume" readonly/></td>
                    <td class="break tdbg"><input type="text" name="totalAmount" readonly /></td>
                    <td class="break tdbg"><input type="text" name="factoryName" /></td>
                    <td class="break tdbg"><input type="text" name="linkman" /></td>
                    <td class="break tdbg"><input type="text" name="contactNumber" /></td>
                    <td class="break tdbg"><input type="text" name="remark" /></td>
                    <td class="break tdbg">
                        <input type="hidden" name="id" value="" class="empty" />
                    </td>
                </form>
            </tr>
            <c:forEach items="${page.dataList}" var="p" varStatus="status">
                <tr class="item ${status.index == 0 ? 'first' : ''}">
                    <td height="24" class="break tdbg line-number">
                            <%--${fn:length(page.dataList)  - status.index}--%>
                            ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
                    </td>
                    <td class="break tdbg">
                        <c:if test="${not empty p.imageURL}" var="hasImage">
                            <div class="has-image">
                                <input type="button" value="上传" class="upload-image" data-product-id="${p.id}"/>
                                <a href="javascript:void(0);">
                                    <img class="product-image" src="<c:url value="/resources/upload/${p.imageURL}" />" alt="" data-product-id="${p.id}"/>
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${not hasImage}" >
                            <div class="not-has-image">
                                <input type="button" value="上传" class="upload-image" data-product-id="${p.id}"/>
                                <a href="javascript:void(0);">
                                    <img class="product-image" src="" alt="" data-product-id="${p.id}"/>
                                </a>
                            </div>
                        </c:if>
                    </td>
                    <td class="break tdbg"><input class="${p.syncToProduct ? "extracted-product" : ""}" type="text" name="factoryProductName" value="${p.factoryProductName}" /></td>
                    <td class="break tdbg"><input class="${p.syncToProduct ? "extracted-product" : ""}" type="text" name="factoryProductNo" value="${p.factoryProductNo}" /></td>
                    <td class="break tdbg"><input type="text" name="packageForm" value="${p.packageForm}" /></td>
                    <td class="break tdbg"><input type="text" name="unit" value="${p.unit}" /></td>
                    <td class="break tdbg"><input type="text" name="factoryPrice" value="<fmt:formatNumber value="${p.factoryPrice}" maxFractionDigits="2"/>" /></td>
                    <td class="break tdbg"><input type="text" name="cartonSize" value="${p.cartonSize}" /></td>
                    <td class="break tdbg"><input type="text" name="packingQuantity" value="${p.packingQuantity != 0 ? p.packingQuantity : ''}" /></td>
                    <td class="break tdbg">
                        <input type="text" name="grossWeight" value="<fmt:formatNumber value="${p.grossWeight}" maxFractionDigits="2"/>" style="width: 25px;" />/<input type="text" name="netWeight" value="<fmt:formatNumber value="${p.netWeight}" maxFractionDigits="2"/>" style="width: 25px;" />
                    </td>
                    <td class="break tdbg"><input type="text" name="orderedCartonQuantity" value="${p.orderedCartonQuantity != 0 ? p.orderedCartonQuantity : ''}" /></td>
                    <td class="break tdbg">
                        <c:if test="${p.quotedPrice != 0}" var="hasQuotedPrice">
                            <input type="text" name="quotedPrice" value="<fmt:formatNumber value="${p.quotedPrice}" maxFractionDigits="${quotation.decimalPlaces}"/>" />
                        </c:if>
                        <c:if test="${not hasQuotedPrice}">
                            <input type="text" name="quotedPrice" />
                        </c:if>
                    </td>
                    <td class="break tdbg"><input type="text" name="orderedProductQuantity" readonly value="${p.orderedProductQuantity}" /></td>
                    <td class="break tdbg"><input type="text" name="totalVolume" readonly value="<fmt:formatNumber value="${p.totalVolume}" maxFractionDigits="2"/>" /></td>
                    <td class="break tdbg"><input type="text" name="totalAmount" readonly value="<fmt:formatNumber value="${p.totalAmount}" maxFractionDigits="2" pattern="#.##"/>" /></td>
                    <td class="break tdbg"><input class="${not empty p.factoryId ? "found-factory":""}" type="text" name="factoryName" value="${p.factoryName}" /></td>
                    <td class="break tdbg"><input type="text" name="linkman" value="${p.linkman}" /></td>
                    <td class="break tdbg"><input type="text" name="contactNumber" value="${p.contactNumber}" /></td>
                    <td class="break tdbg"><input type="text" name="remark" value="${p.remark}" /></td>
                    <td class="break tdbg"><input type="checkbox" class="draft-id" name="id" value="${p.id}" id="${p.id}" /></td>
                </tr>
            </c:forEach>
            <c:forEach begin="${fn:length(page.dataList)}" end="19" step="1">
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
                    <td class="break tdbg">
                        <%--<input type="checkbox" class="empty draft-id" name="id" />--%>
                    </td>
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
                <td width="70" align="right" valign="top"><input type="image" id="btn-delete"
                                                                 src="<c:url value="/resources/quotation/images/del2.png" />"
                                                                 class="btn"/></td>
                <td width="70" align="center" valign="top">&nbsp;</td>
            </tr>
        </table>

</div>


<div id="dialog-upload-image" style="display: none; background-color: #fff; width: 300px; height: 220px; border: 1px solid #000000;">

    <div class="dialog-header">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>

    <iframe name="iframe-upload-image" id="iframe-upload-image" style="width: 300px; height: 220px; border: 0;" src=""></iframe>
</div>

<div id="dialog-favor-setting" style="display: none; background-color: #fff; width: 682px; border: 1px solid #000000;">

    <%--<div class="dialog-header" style="position: absolute; top: 0; left: 0; z-index: 10; width: 100%;">--%>
        <%--<a href="javascript:void (0);" class="dialog-close">--%>
            <%--<img src="<c:url value="/resources/common/project/images/close.png" />"/>--%>
        <%--</a>--%>
    <%--</div>--%>

    <iframe name="iframe-favor-setting" id="iframe-favor-setting" style="margin-top: 0px; width: 682px; height: 483px; border: 0;"
            <%--src="<c:url value="/quotation/favor/setting"/>"--%>
    ></iframe>
</div>

<div id="dialog-operating-setting" style="display: none; width: 100%; top: 80px; bottom: 0; border: 0px solid #000000;">

    <%--<div class="dialog-header" style="position: absolute; top: 0; left: 0; z-index: 10; width: 100%;">--%>
        <%--<a href="javascript:void (0);" class="dialog-close">--%>
            <%--<img src="<c:url value="/resources/common/project/images/close.png" />"/>--%>
        <%--</a>--%>
    <%--</div>--%>
    <iframe name="iframe-operating-setting" id="iframe-operating-setting" style="margin-top: 0px; width: 755px; height: 580px; border: 0;"
            <%--src="<c:url value="/quotation/favor/setting"/>"--%>
    ></iframe>
</div>

<div id="dialog-archive-list" style="display: none; width: 100%; top: 80px; bottom: 0; border: 0px solid #000000;">

    <%--<div class="dialog-header" style="position: absolute; top: 0; left: 0; z-index: 10; width: 100%;">--%>
        <%--<a href="javascript:void (0);" class="dialog-close">--%>
            <%--<img src="<c:url value="/resources/common/project/images/close.png" />"/>--%>
        <%--</a>--%>
    <%--</div>--%>
    <iframe name="iframe-archive-list" id="iframe-archive-list" style="margin-top: 0px; width: 755px; height: 580px; border: 0;"
            <%--src="<c:url value="/quotation/archiveList"/>"--%>
    ></iframe>
</div>


<div id="dialog-big-image" style="display: none; background-color: #fff; width: 250px; height: 250px; border: 1px solid #000000;">
    <div class="dialog-header" style="position: absolute; top: 0; left: 0; z-index: 10; width: 100%;">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>
    <img id="big-img" src="" style="width: 220px; height: 220px; margin-top: 15px" />
</div>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    var empty = ${empty param['empty'] ? false : true};
    var indexNumber = ${empty param.indexNumber ? -1 : param.indexNumber};
    var pageQuantity = '${page.pageQuantity}';
    var pageIndex = '${page.pageIndex}';
    var pageSize = '${page.pageSize}';
    var quotationId = '${quotation.id}';
    var currentModule = 'quotation';
    var generatedOrder = ${quotation.generatedOrder == true ? true : false};
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
