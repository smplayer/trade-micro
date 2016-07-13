<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>产品列表</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/product/css/listOfFactory.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        body {
            background: url(<c:url value="/resources/factory/images/qiqiubg.png"/>) #FCEBFC;
        }

        .toptt {
            letter-spacing: 18px;
        }

        .top div {
            margin-left: 18px;
        }

        .toplw {
            width: 190px;
        }

        .topw {
            width: 620px;
            padding-top: 43px;
        }

        .top2w {
            width: 1230px;
            margin-top: 15px;
            margin-bottom: 3px;
        }

        .topfl {
            margin-left: 65px;
            text-align: left;
            width: 190px;
        }

        .topr2 {
            margin-right: 65px;
        }

        .tdbg {
            background: #DFE6F5;
        }

        .toptdbg {
            background: #D9C9F2;
            text-align: center;
        }

        .jkmenu {
            width: 320px;
            margin: 0 auto 7px auto;
            height: 20px;
            line-height: 20px;
        }
        .product-image {
            width: 20px;
            height: 20px;
        }
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div class="top topw"><div class="toptt">产品列表</div></div>
    <div class="topline toplw"></div>
    <div class="top2 top2w">
        <div class="topfl fl">
            <input name="keywords" value="${param.keywords}" type="text" id="keywords" size="15" class="border"/>
            <a href="<c:url value="/"/>" id="search">查询</a>
        </div>
        <div class="fl">
            <span id="factory-name" style="margin-left: 10px;">厂名：&nbsp;${factory.name}</span>
            <span id="contact-number" style="margin-left: 80px;">手机/电话：&nbsp;${not empty factory.mobileNumber ? factory.mobileNumber : factory.phoneNumber}</span>
        </div>
        <div class="fr topr2">
            <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                <c:param name="prePageImage" value="/resources/common/project/images/left_03.png"  />
                <c:param name="nextPageImage" value="/resources/common/project/images/right_03.png"  />
                <c:param name="pageIndex" value="${productPage.pageIndex}"  />
                <c:param name="pageQuantity" value="${productPage.pageQuantity}"  />
                <c:param name="url" value="/product/listOfFactory?factoryId=${factory.id}"/>
            </c:import>
        </div>
    </div>

    <table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
        <tr>
            <td height="40" rowspan="2" class="td39 toptdbg">序号</td>
            <td rowspan="2" class="td50 toptdbg">图片</td>
            <td colspan="2" class="toptdbg">品 名</td>
            <td colspan="2" class="toptdbg">货 号</td>
            <td rowspan="2" class="td60 toptdbg">包装方式</td>
            <td rowspan="2" class="td40 toptdbg">单位</td>
            <td colspan="2" class="toptdbg">价格记录</td>
            <td colspan="3" class="toptdbg">包装资料</td>
            <td rowspan="2" class="td55 toptdbg">功能</td>
            <td rowspan="2" class="td55 toptdbg">类别</td>
            <td rowspan="2" class="td55 toptdbg">细类</td>
            <td rowspan="2" class="td75 toptdbg">录入日期</td>
            <td rowspan="2" class="td140 toptdbg">备 注</td>
            <td rowspan="2" class="td40 toptdbg">全选</td>
        </tr>
        <tr>
            <td class="td75 toptdbg">公司</td>
            <td class="td75 toptdbg">工厂</td>
            <td class="td90 toptdbg">公司</td>
            <td class="td80 toptdbg">工厂</td>
            <td class="td50 toptdbg">厂价</td>
            <td class="td50 toptdbg">日期</td>
            <td class="td80 toptdbg">纸箱规格</td>
            <td class="td50 toptdbg">装量</td>
            <td class="td70 toptdbg">毛/净重</td>
        </tr>
        <c:forEach items="${productPage.dataList}" var="p" varStatus="status" >
        <tr>
            <td height="24" class="break tdbg">
                ${(productPage.dataQuantity - (productPage.pageIndex - 1) * productPage.pageSize) - status.index}
            </td>
            <td class="break tdbg">
                <c:if test="${not empty p.imageURL}" var="hasImage">
                    <a href="javascript:void(0);">
                        <img class="product-image" src="<c:url value="/resources/upload/${p.imageURL}" />" alt="" data-product-id="${p.id}"/>
                    </a>
                </c:if>
            </td>
            <td class="break tdbg">${p.companyProductName}</td>
            <td class="break tdbg">${p.factoryProductName}</td>
            <td class="break tdbg">${p.companyProductNo}</td>
            <td class="break tdbg">${p.factoryProductNo}</td>
            <td class="break tdbg">${p.packageForm}</td>
            <td class="break tdbg">${p.unit}</td>
            <td class="break tdbg">${p.factoryPrice}</td>
            <td class="break tdbg"><fmt:formatDate value="${p.lastFactoryQuotedDate}" pattern="MM-dd" /></td>
            <td class="break tdbg">${p.cartonSize}</td>
            <td class="break tdbg">${p.packingQuantity}</td>
            <td class="break tdbg">${p.grossWeight}/${p.netWeight}</td>
            <td class="break tdbg">${p.functionDescription}</td>
            <td class="break tdbg">${p.category}</td>
            <td class="break tdbg">${p.subCategory}</td>
            <td class="break tdbg"><fmt:formatDate value="${p.addedDate}" pattern="MM-dd" /></td>
            <td class="break tdbg">${p.remark}</td>
            <td class="break tdbg">&nbsp;</td>
        </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(productPage.dataList)}" end="19" step="1">
            <tr class="">
                <td height="24" class="break tdbg">&nbsp;</td>
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
    </table>

    <table width="1230" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="4" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" width="1035" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top"><input type="image" src="<c:url value="/resources/order/images/save1.png" />" class="btn"/></td>
            <td width="70" align="right" valign="top"><input type="image" src="<c:url value="/resources/order/images/del2.png" />" class="btn"/></td>
            <td width="55" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>
<form id="form-search" action="<c:url value="/product/listOfFactory" />" method="GET" target="_self">
    <input type="hidden" name="factoryId" value="${factory.id}" />
</form>


<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>

<script type="text/javascript" src="<c:url value="/resources/product/js/listOfFactory.js"/>"></script>

</body>
</html>
