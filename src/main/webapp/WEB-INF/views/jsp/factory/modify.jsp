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
<form id="form1" name="form1" method="post" action="<c:url value="/factory/create"/>">
    <input type="hidden" name="id" id="factory-id" value="${factory.id}" />
    <div class="main border">
        <div class="top topw">
            <div class="toptt">工厂资料</div>
        </div>
        <div class="topline toplw"></div>
        <table width="519" border="0" align="center" cellpadding="4" cellspacing="0">
            <tr>
                <td width="102"><input name="textfield" type="text" class="w102" id="textfield" value="工厂/公司名称"
                                       readonly="readonly"/></td>
                <td colspan="3"><input type="text" name="name" id="name" class="w404" value="${factory.name}"/></td>
            </tr>
            <tr>
                <td><input name="textfield2" type="text" class="w102" id="textfield2" value="主营" readonly="readonly"/></td>
                <td width="148"><input type="text" name="mainProduct" id="mainProduct" class="w148" value="${factory.mainProduct}"/></td>
                <td width="90"><input name="textfield14" type="text" class="w90" id="textfield14" value="联系人" readonly="readonly"/></td>
                <td width="148"><input type="text" name="linkman" id="linkman" class="w148" value="${factory.linkman}"/></td>
            </tr>
            <tr>
                <td><input name="textfield3" type="text" class="w102" id="textfield3" value="手机" readonly="readonly"/></td>
                <td><input type="text" name="mobileNumber" id="mobileNumber" class="w148" value="${factory.mobileNumber}"/></td>
                <td><input name="textfield15" type="text" class="w90" id="textfield15" value="电话" readonly="readonly"/></td>
                <td><input type="text" name="phoneNumber" id="phoneNumber" class="w148"  value="${factory.phoneNumber}"/></td>
            </tr>
            <tr>
                <td><input name="textfield4" type="text" class="w102" id="textfield4" value="传真" readonly="readonly"/></td>
                <td><input type="text" name="fax" id="fax" class="w148"  value="${factory.fax}"/></td>
                <td><input name="textfield16" type="text" class="w90" id="textfield16" value="QQ" readonly="readonly"/></td>
                <td><input type="text" name="qq" id="qq" class="w148"  value="${factory.qq}"/></td>
            </tr>
            <tr>
                <td><input name="textfield5" type="text" class="w102" id="textfield5" value="地址" readonly="readonly"/></td>
                <td colspan="3"><input type="text" name="address" id="address" class="w404" value="${factory.address}"/></td>
            </tr>
            <tr>
                <td><input name="textfield17" type="text" class="w102h" id="textfield17" value="简录" readonly="readonly"/></td>
                <td colspan="3"><textarea name="summary" id="summary" cols="45" rows="5" class="w404h" value="${factory.summary}">${factory.summary}</textarea>
                </td>
            </tr>
        </table>

        <div class="bottom">
            <div class="btmbtn"><input id="btn-confirm" type="image" src="<c:url value="/resources/quotation/images/tijiao2.png"/>" class="btn"/></div>
        </div>
    </div>
</form>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/factory/js/modify.js"/>"></script>


</body>
</html>
