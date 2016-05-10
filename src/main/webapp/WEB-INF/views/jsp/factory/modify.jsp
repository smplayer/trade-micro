<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工厂资料编辑</title>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resources/factory/css/modify.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="<c:url value="/factory/modify"/>">
    <div class="top topw">
        <div class="toptt">工厂资料</div>
    </div>
    <div class="topline toplw"></div>
    <table id="main-table" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
        <tr>
            <td height="40" class="td100 toptdbg">字段名</td>
            <td class="td220 toptdbg">内容</td>
        </tr>

        <tr>
            <td height="24" class="break tdbg">厂名</td>
            <td class="break tdbg"><input type="text" name="name" value="${factory.name}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">主营产品</td>
            <td class="break tdbg"><input type="text" name="mainProduct" value="${factory.mainProduct}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">产品数量</td>
            <td class="break tdbg"><input type="text" name="productQuantity" value="${factory.productQuantity}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">联系人</td>
            <td class="break tdbg"><input type="text" name="linkman" value="${factory.linkman}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">手机/电话</td>
            <td class="break tdbg"><input type="text" name="contactNumber" value="${factory.contactNumber}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">地址</td>
            <td class="break tdbg"><input type="text" name="address" value="${factory.address}" /></td>
        </tr>
        <tr>
            <td height="24" class="break tdbg">备注</td>
            <td class="break tdbg"><input type="text" name="remark" value="${factory.remark}" /></td>
        </tr>


    </table>

    <div id="bottom-actions" style="">
        <input id="btn-confirm" type="submit" value="提交"/>
    </div>

    <input id="factory-id" type="hidden" value="${factory.id}" />
</form>

<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/factory/js/modify.js"/>"></script>
<script>
    var confirmUrl = '<c:url value="/ajax/factory/modify" />';
</script>

</body>
</html>
