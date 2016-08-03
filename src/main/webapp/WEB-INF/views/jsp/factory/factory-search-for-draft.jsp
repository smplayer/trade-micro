<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工厂查询</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/factory/css/list.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    </style>
</head>

<body>
<form id="main-table" name="form1" method="post" action="">
    <table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
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
            <td class="break tdbg">${f.productQuantity}</td>
            <td class="break tdbg">${f.linkman}</td>
            <td class="break tdbg">${f.mobileNumber}${empty f.mobileNumber || empty f.phoneNumber ? "" : "/"}${f.phoneNumber}</td>
            <td class="break tdbg">${f.address}</td>
            <td class="break tdbg"><a href="javascript:void(0)" data-url="<c:url value="/factory/modify/${f.id}"/>"
                                      target="_blank" class="modify-factory">查看</a></td>
            <td class="break tdbg"><fmt:formatDate value="${f.addedDate}" pattern="MM-dd"/></td>
            <td class="break tdbg">${f.remark}</td>
            <td class="break tdbg">
                <input name="id" class="factory-id-checkbox" type="checkbox" value="${f.id}" id="${f.id}"/>
            </td>
        </tr>
    </c:forEach>

        <%--<c:forEach begin="${fn:length(page.dataList)}" end="19" step="1">--%>
            <%--<tr>--%>
                <%--<td height="24" class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
                <%--<td class="break tdbg">&nbsp;</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    </table>
    <table width="1230" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="4" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top"><input type="image" id="btn-confirm"
                                                             src="<c:url value="/resources/factory/images/search-confirm.png" />"
                                                             class="btn"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/factory/js/list.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/factory/js/factory-search-for-draft.js"/>"></script>
<script>
    var quotationProductItemDraftId = '${quotationProductItemDraftId}';
    var factoryProductNo = '${factoryProductNo}';
    var selectFactoryForProductItemDraftUrl = '<c:url value="/quotation/selectFactoryForProductItemDraft" />';
</script>

</body>
</html>
