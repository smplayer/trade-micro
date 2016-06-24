<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工厂管理</title>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/factory/css/list.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .top2 {
        }
    </style>
</head>

<body>
<c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
    <c:param name="backgroundColor" value="#eeb13f"/>
    <c:param name="currentModule" value="factory"/>
    <c:param name="title" value="工厂管理"/>
    <c:param name="bgColor" value="#eeb13f"/>
</c:import>

<%--<div class="top topw">--%>
<%--<div class="toptt">工厂管理</div>--%>
<%--</div>--%>
<%--<div class="topline toplw"></div>--%>

<%--<div id="cus-list">--%>
    <%--<ul>--%>
        <%--<li class="text key-factory-name">--%>
            <%--厂名:--%>
        <%--</li>--%>

        <%--<c:forEach var="f" items="${favorPage.dataList}" varStatus="status">--%>
            <%--<li class="favor-cus-name">--%>
                <%--<a href="<c:url value="/factory/list?keyword=${f.name}"/>">${f.name}</a>--%>
            <%--</li>--%>
        <%--</c:forEach>--%>

        <%--<c:forEach begin="${fn:length(favorPage.dataList)}" end="10" step="1">--%>
            <%--<li class="favor-cus-name">--%>
                <%--<a href="javascript:void(0)"></a>--%>
            <%--</li>--%>
        <%--</c:forEach>--%>

        <%--<li class="text more" id="set-favor-quotation-list">--%>
            <%--<a href="javascript:void(0);">更多</a>--%>
        <%--</li>--%>
    <%--</ul>--%>
<%--</div>--%>

<div class="top2 top2w">
    <div class="topfl fl">
        <form id="form-search" action="<c:url value="/factory/list" />" target="_self">
            <input name="keywords" value="${param.keywords}" type="text" id="keywords" size="18" class="border"/>
            <a href="javascript:void(0)" id="search">查询</a>
        </form>
    </div>
    <div class="fr topr2">
        <table id="table1" width="98" border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
                <td width="9">

                    <c:if test="${page.pageIndex > 1}" var="notFirstPage">
                        <a href="
                            <c:url value="/factory/list">
                                <c:param name="pageIndex" value="${page.pageIndex - 1}"/>
                                <c:if test="${not empty param.keywords}">
                                    <c:param name="keywords" value="${param.keywords}"/>
                                </c:if>
                            </c:url>
                        ">
                            <img src="<c:url value="/resources/factory/images/lefty.png" />" width="9" height="8"/>
                        </a>
                    </c:if>
                    <c:if test="${not notFirstPage}">
                        <img src="<c:url value="/resources/factory/images/lefty.png" />" width="9" height="8"/>
                    </c:if>
                </td>
                <td width="80" align="center">
                    <a href="
                            <c:url value="/factory/list">
                                <c:param name="pageIndex" value="1"/>
                                <c:if test="${not empty param.keywords}">
                                    <c:param name="keywords" value="${param.keywords}"/>
                                </c:if>
                            </c:url>
                    ">1</a><span style="width: 10px; text-align: right; display: inline-block;">-</span><input
                        id="newPageIndex" type="text" value="${empty page ? 0 : page.pageIndex}"
                        style="width: 20px; text-align: center; border: 0; background-color: transparent;"
                />/<span
                        style="width: 20px; text-align: center; display: inline-block;">${empty page ? 0 : page.pageQuantity}</span>
                </td>
                <td width="9">
                    <c:if test="${page.pageIndex < page.pageQuantity}" var="notLastPage">
                        <a href="
                            <c:url value="/factory/list">
                                <c:param name="pageIndex" value="${page.pageIndex + 1}"/>
                                <c:if test="${not empty param.keywords}">
                                    <c:param name="keywords" value="${param.keywords}"/>
                                </c:if>
                            </c:url>
                        ">
                            <img src="<c:url value="/resources/factory/images/righty.png" />" width="9" height="8"/>
                        </a>
                    </c:if>
                    <c:if test="${not notLastPage}">
                        <img src="<c:url value="/resources/factory/images/righty.png" />" width="9" height="8"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
    <div class="fr">
        <a href="<c:url value="/factory/create"/>" id="create-factory" target="_blank">录入资料</a>
    </div>
</div>

<form id="form1" name="form1" method="post" action="">
    <table style="width: 1230px;" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
        <tr>
            <td height="40" class="td39 toptdbg">序号</td>
            <td class="td150 toptdbg">厂 名</td>
            <td class="td100 toptdbg">主营产品</td>
            <td class="td70 toptdbg">产品数量</td>
            <td class="td85 toptdbg">联 系 人</td>
            <td class="td175 toptdbg">手机/电话</td>
            <td class="td180 toptdbg">地 址</td>
            <td class="td70 toptdbg">工厂资料</td>
            <td class="td100 toptdbg">录入日期</td>
            <td class="td220 toptdbg">备 注</td>
            <td class="td40 toptdbg">全选</td>
        </tr>
        <c:forEach items="${page.dataList}" var="f" varStatus="status">
            <tr>
                <td height="24" class="break tdbg">
                        ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
                </td>
                <td class="break tdbg">${f.name}</td>
                <td class="break tdbg">${f.mainProduct}</td>
                <td class="break tdbg">
                    <c:if test="${f.productQuantity > 0}" var="hasProduct">
                        <a href="<c:url value="/product/listOfFactory?factoryId=${f.id}"/>" class="show-product-list" target="_blank">
                            ${f.productQuantity}
                        </a>
                    </c:if>
                    <c:if test="${not hasProduct}" >
                        ${f.productQuantity}
                    </c:if>
                </td>
                <td class="break tdbg">${f.linkman}</td>
                <td class="break tdbg">${f.mobileNumber}${empty f.mobileNumber || empty f.phoneNumber ? "" : "/"}${f.phoneNumber}</td>
                <td class="break tdbg">${f.address}</td>
                <td class="break tdbg"><a href="javascript:void(0)" data-url="<c:url value="/factory/modify/${f.id}"/>"
                                          target="_blank" class="modify-factory">查看</a></td>
                <td class="break tdbg"><fmt:formatDate value="${f.createdDate}" pattern="MM-dd"/></td>
                <td class="break tdbg">
                <input type="text" value="${f.remark}" name="remark" />
                </td>
                <td class="break tdbg">
                    <input name="id" class="factory-checkbox" type="checkbox" value="${f.id}"/>
                </td>
            </tr>
        </c:forEach>

        <c:forEach begin="${fn:length(page.dataList)}" end="19" step="1">
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
            </tr>
        </c:forEach>
    </table>
    <table width="1230" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="4" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top"><input type="image"
                                                             src="<c:url value="/resources/factory/images/save3.png" />"
                                                             class="btn"/></td>
            <td width="70" align="right" valign="top"><input type="image"
                                                             src="<c:url value="/resources/factory/images/del3.png" />"
                                                             class="btn"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>
<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/factory/js/list.js"/>"></script>
<script>
</script>

</body>
</html>
