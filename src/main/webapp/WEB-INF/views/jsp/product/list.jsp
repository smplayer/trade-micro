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

    <c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
        <c:param name="backgroundColor" value="#2ebc54"/>
        <c:param name="currentModule" value="product"/>
        <c:param name="title" value="${not empty title ? title : '操作版'}"/>
    </c:import>

    <%--<div class="top"><span>产品库</span></div>--%>
    <%--<div class="topline"></div>--%>
    <%--<div class="menu">--%>
        <%--<a href="<c:url value="/product/list"/>">产品库</a>--%>
        <%--<a href="<c:url value="/product/list/complete"/>">标准产品</a>--%>
        <%--<a href="<c:url value="/product/list/incomplete"/>">补料产品</a>--%>
    <%--</div>--%>

    <table width="1240" border="0" align="center" cellspacing="1">
        <tr>
            <td width="197" height="20" align="left"><input name="textfield" type="text" id="textfield" size="10"
                                                            class="border"/> &nbsp;查询
            </td>
            <td width="761" align="left">&nbsp;</td>
            <td width="128" align="right">
                <a href="<c:url value="/product/create" />" id="add-product">产品录入</a>
            </td>
            <td width="151">
                <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                    <c:param name="prePageImage" value="/resources/common/project/images/left_03.png"  />
                    <c:param name="nextPageImage" value="/resources/common/project/images/right_03.png"  />
                    <c:param name="pageIndex" value="${productPage.pageIndex}"  />
                    <c:param name="pageQuantity" value="${productPage.pageQuantity}"  />
                    <c:param name="url" value="/product/list"/>
                </c:import>
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
                <td class="break tdbg">
                    <c:if test="${not empty p.imageURL}" var="hasImage">
                        <div class="has-image">
                            <input type="button" value="上传" class="upload-image" data-product-id="${p.id}"/>
                            <a href="javascript:void (0);">
                            <img class="product-image" src="<c:url value="/resources/upload/${p.imageURL}" />" alt="" data-product-id="${p.id}"
                                 style=""/>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${not hasImage}" >
                        <div class="not-has-image">
                            <input type="button" value="上传" class="upload-image" data-product-id="${p.id}"/>
                            <a href="javascript:void (0);">
                            <img class="product-image" src="" alt="" data-product-id="${p.id}" style=""/>
                            </a>
                        </div>
                    </c:if>
                </td>
                <td class="break tdbg">
                    <input name="companyProductName" type="text" value="${p.companyProductName}"/>
                </td>
                <td class="break tdbg"><input name="companyProductNo" type="text" value="${p.companyProductNo}"/></td>
                <td class="editable break tdbg"><input name="factoryProductNo" type="text" value="${p.factoryProductNo}"/></td>
                <td class="editable break tdbg"><input name="packageForm" type="text" value="${p.packageForm}"/></td>
                <td class="editable break tdbg"><input name="unit" type="text" value="${p.unit}"/></td>
                <td class="editable break tdbg"><input name="factoryPrice" type="text" value="<fmt:formatNumber type="number" pattern="#.#" value="${p.factoryPrice}" maxFractionDigits="1"/>"/></td>
                <td class="break tdbg">${p.lastFactoryQuotedTime}</td>
                <td class="editable break tdbg"><input name="cartonSize" type="text" value="${p.cartonSize}"/></td>
                <td class="editable break tdbg"><input name="packingQuantity" type="text" value="${p.packingQuantity}"/></td>
                <td class="editable break tdbg">
                    <input type="text" name="grossWeight" value="<fmt:formatNumber value="${p.grossWeight}" maxFractionDigits="2"/>" style="width: 25px;"
                    />/<input type="text" name="netWeight" value="<fmt:formatNumber value="${p.netWeight}" maxFractionDigits="2"/>" style="width: 25px;" />
                </td>
                <td class="editable break tdbg"><input name="functionDescription" type="text" value="${p.functionDescription}"/></td>
                <td class="editable break tdbg"><input name="category" type="text" value="${p.category}"/></td>
                <td class="editable break tdbg"><input name="subCategory" type="text" value="${p.subCategory}"/></td>
                <td class="break tdbg">${p.factoryName}</td>
                <td class="break tdbg">${p.factoryContactNumber}</td>
                <td class="break tdbg">
                    <fmt:formatDate value="${p.addedDate}" pattern="MM-dd" />
                </td>
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

<div id="dialog-upload-image" style="display: none; background-color: #fff; width: 300px; height: 220px; border: 2px solid #a839a8;">

    <div class="dialog-header">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>

    <iframe name="iframe-upload-image" id="iframe-upload-image" style="width: 300px; height: 220px; border: 0;" src=""></iframe>
</div>

<div id="dialog-big-image" style="display: none; background-color: #fff; width: 250px; height: 250px; border: 2px solid #a839a8;">
    <div class="dialog-header" style="position: absolute; top: 0; left: 0; z-index: 10; width: 100%;">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>
    <img id="big-img" src="" style="width: 220px; height: 220px; margin-top: 15px" />
</div>


<script>
    var currentModule = 'product';
</script>
<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/product/js/list.js"/>"></script>
<script>
    var productModificationUrl = '<c:url value="/ajax/product/modify"/>';
</script>

</body>
</html>
