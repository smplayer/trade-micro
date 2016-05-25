<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>档案</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/archive-list.css"/>"/>

    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">

    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">

    <c:import url="/WEB-INF/views/jsp/quotation/common/quotation-header.jsp">
        <c:param name="title" value="档案"/>
        <c:param name="curPage" value="archive"/>
    </c:import>


    <div class="top2 top2w">
        <div class="fr topr2">

            <table width="98" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="9">
                        <c:if test="${page.pageIndex > 1}" var="notFirstPage">
                            <a href="
                                    <c:url value="/quotation/archiveList">
                                        <c:param name="pageIndex" value="${page.pageIndex - 1}"/>
                                    </c:url>
                                ">
                                <img src="<c:url value="/resources/quotation/images/left_03.png" />" width="9" height="8"/>
                            </a>
                        </c:if>
                        <c:if test="${not notFirstPage}">
                            <img src="<c:url value="/resources/quotation/images/left_03.png" />" width="9" height="8"/>
                        </c:if>
                    </td>
                    <td width="66" align="center">
                        <a href="<c:url value="/quotation/archiveList" /> ">1</a>
                        <span style="width: 10px; text-align: right; display: inline-block;">-</span><input id="newPageIndex" type="text" value="${empty page ? 0 : page.pageIndex}" style="width: 20px; text-align: center; border: 0; background-color: transparent;"
                    />/<span style="width: 20px; text-align: center; display: inline-block;">${empty page ? 0 : page.pageQuantity}</span>
                    </td>
                    <td width="9">
                        <c:if test="${page.pageIndex < page.pageQuantity}" var="notLastPage">
                            <a href="
                                    <c:url value="/quotation/archiveList">
                                        <c:param name="pageIndex" value="${page.pageIndex + 1}"/>
                                    </c:url>
                                ">
                                <img src="<c:url value="/resources/quotation/images/right_03.png" />" width="9" height="8"/>
                            </a>
                        </c:if>
                        <c:if test="${not notLastPage}">
                            <img src="<c:url value="/resources/quotation/images/right_03.png" />" width="9" height="8"/>
                        </c:if>

                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
        <tr>
            <td height="40" class="td39 toptdbg">序号</td>
            <td class="td100 toptdbg">存档日期</td>
            <td class="td150 toptdbg">客户名</td>
            <td class="td120 toptdbg">国家/地区</td>
            <td class="td100 toptdbg">贸易条款</td>
            <td class="td80 toptdbg">装运港</td>
            <td class="td70 toptdbg">柜型</td>
            <td class="td70 toptdbg">币种</td>
            <td class="td70 toptdbg">产品数量</td>
            <td class="td90 toptdbg">总金额</td>
            <td class="td80 toptdbg">档案</td>
            <td class="td40 toptdbg">全选</td>
        </tr>
        <c:forEach items="${page.dataList}" var="a" varStatus="status">
        <tr>
            <td height="24" class="break tdbg">
                    ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
            </td>
            <td class="break tdbg">
                <fmt:formatDate value="${a.archivedDate}" pattern="MM-dd"/>
            </td>
            <td class="break tdbg">${a.customerName}</td>
            <td class="break tdbg">${a.region}</td>
            <td class="break tdbg">${a.tradeClauseType}</td>
            <td class="break tdbg">${a.shipmentPort}</td>
            <td class="break tdbg">${a.containerType}</td>
            <td class="break tdbg">${a.currency}</td>
            <td class="break tdbg"></td>
            <td class="break tdbg"></td>
            <td class="break tdbg">
                <a href="<c:url value="/quotation/reloadFromArchive?id=${a.id}"/>">调阅</a>
            </td>
            <td class="break tdbg"></td>
        </tr>
        </c:forEach>

        <c:forEach begin="${fn:length(page.dataList)}" end="18" step="1">
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
            </tr>
        </c:forEach>
    </table>
    <table width="1010" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="3" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top"><input type="image" src="<c:url value="/resources/quotation/images/del.png" />" class="btn"/></td>
            <td width="60" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>
