<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>产品库微</title>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/product/css/list.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div class="top"><span>产品库</span></div>
    <div class="topline"></div>
    <div class="menu"><a href="#">产品库</a><a href="#">标准产品</a><a href="#">补料产品</a></div>
    <table width="1240" border="0" align="center" cellspacing="1">
        <tr>
            <td width="197" height="20" align="left"><input name="textfield" type="text" id="textfield" size="10"
                                                            class="border"/> &nbsp;查询
            </td>
            <td width="761" align="left">&nbsp;</td>
            <td width="128" align="right">产品录入</td>
            <td width="151">
                <table width="120" border="0" align="right" cellpadding="0" cellspacing="0">
                    <tr>
                        <td><a href="#"><img src="<c:url value="/resources/product/images/left_03.png" />" width="13"
                                             height="11"/></a></td>
                        <td align="center">1-000/000</td>
                        <td><a href="#"><img src="<c:url value="/resources/product/images/right_03.png" />" width="13"
                                             height="11"/></a></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <table id="product-list" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333" style="width: 1330px">
        <tr>
            <td height="40" rowspan="2" class="td39 toptdbg">序号</td>
            <td rowspan="2" class="td50 toptdbg">图片</td>
            <td rowspan="2" class="td80 toptdbg">品 名</td>
            <td colspan="2" class="toptdbg">货 号</td>
            <td rowspan="2" class="td65 toptdbg">包装方式</td>
            <td rowspan="2" class="td50 toptdbg">单位</td>
            <td colspan="2" class="td100 toptdbg">价 格 记 录</td>
            <td colspan="3" class="toptdbg">包装资料</td>
            <td rowspan="2" class="td55 toptdbg">功能</td>
            <td rowspan="2" class="td55 toptdbg">类别</td>
            <td rowspan="2" class="td55 toptdbg">细类</td>
            <td colspan="2" class="toptdbg">工厂资料</td>
            <td rowspan="2" class="td65 toptdbg">录入日期</td>
            <td rowspan="2" class="td140 toptdbg">备 注</td>
            <td rowspan="2" class="td40 toptdbg">全选</td>
        </tr>
        <tr>
            <td class="td90 toptdbg">公司</td>
            <td class="td80 toptdbg">工厂</td>
            <td class="td50 toptdbg">厂价</td>
            <td class="td50 toptdbg">日期</td>
            <td class="td85 toptdbg">纸箱规格</td>
            <td class="td50 toptdbg">装量</td>
            <td class="td70 toptdbg">毛/净重</td>
            <td class="td85 toptdbg">厂名</td>
            <td class="td75 toptdbg">电话</td>
        </tr>
        <c:forEach items="${productPage.dataList}" var="p" varStatus="status">
            <tr>
                <td height="24" class="break tdbg">
                        ${(productPage.dataQuantity - (productPage.pageIndex - 1) * productPage.pageSize) - status.index}
                </td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">${p.name}</td>
                <td class="break tdbg"><input name="" type="text" value="${p.companyProductNo}"/></td>
                <td class="editable break tdbg"><input name="factoryProductNo" type="text" value="${p.factoryProductNo}"/></td>
                <td class="editable break tdbg"><input name="packageForm" type="text" value="${p.packageForm}"/></td>
                <td class="editable break tdbg"><input name="unit" type="text" value="${p.unit}"/></td>
                <td class="editable break tdbg"><input name="factoryPrice" type="text" value="<fmt:formatNumber type="number" pattern="#.#" value="${p.factoryPrice}" maxFractionDigits="1"/>"/></td>
                <td class="break tdbg">${p.lastFactoryQuotedTime}</td>
                <td class="editable break tdbg"><input name="cartonSize" type="text" value="${p.cartonSize}"/></td>
                <td class="editable break tdbg"><input name="packingQuantity" type="text" value="${p.packingQuantity}"/></td>
                <td class="editable break tdbg">
                    <input name="" type="text" value="${p.grossWeight}/${p.netWeight}"/>
                </td>
                <td class="editable break tdbg"><input name="functionDescription" type="text" value="${p.functionDescription}"/></td>
                <td class="editable break tdbg"><input name="category" type="text" value="${p.category}"/></td>
                <td class="editable break tdbg"><input name="subCategory" type="text" value="${p.subCategory}"/></td>
                <td class="break tdbg">${p.factoryName}</td>
                <td class="break tdbg">${p.factoryContactNumber}</td>
                <td class="break tdbg">${p.addedDate}</td>
                <td class="break tdbg"><input name="remark" type="text" value="${p.remark}"/></td>
                <td class="break tdbg">
                    <input name="id" class="product-checkbox" type="checkbox" value="${p.id}" />
                </td>
            </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(productPage.dataList)}" end="19" step="1">
            <tr>
                <td height="24" class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
                <td class="break tdbg">&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
    <table width="1280" border="0" align="center" cellspacing="1">
        <tr>
            <td height="25" colspan="2" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" align="left" valign="top" class="padtop3">&nbsp;</td>
            <td width="139" align="center" valign="top">
                <input type="image" src="<c:url value="/resources/product/images/save.png" />" class="btn" id="save"/>&nbsp;&nbsp;&nbsp;
                <input type="image" src="<c:url value="/resources/product/images/del.png" />" class="btn"/></td>
        </tr>
    </table>
</form>
<div class="html-template" style="display: none;">
    <input id="editor-template" class="editor" type="text" />
</div>
<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/product/js/list.js"/>"></script>
<script>
    var productModificationUrl = '<c:url value="/ajax/product/modify"/>';
</script>

</body>
</html>
