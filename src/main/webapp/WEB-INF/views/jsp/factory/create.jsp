<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>工厂录入</title>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .toptt {
            letter-spacing: 16px;
            color: #053188;
        }

        .top div {
            margin-left: 16px;
        }

        .toplw {
            width: 160px;
            margin-bottom: 25px;
        }

        .topw {
            width: 620px;
            padding-top: 38px;
        }

        .main {
            width: 638px;
            height: 418px;
            margin: 0 auto;
            background: #E4DFFD;
        }

        .w102, .w148, .w404, .w90 {
            vertical-align: middle;
            height: 26px;
            border: 1px solid #000;
            line-height: 26px;
            background: #fff;
        }

        .w102 {
            width: 100px;
            text-align: center;
        }

        .w148 {
            width: 146px;
        }

        .w404 {
            width: 402px;
        }

        .w90 {
            width: 88px;
            text-align: center;
        }

        .w102h {
            width: 100px;
            text-align: center;
            vertical-align: middle;
            height: 52px;
            border: 1px solid #000;
            line-height: 54px;
            background: #fff;
        }

        .w404h {
            width: 402px;
            height: 52px;
            border: 1px solid #000;
            background: #fff;
        }

        .txt522 {
            width: 518px;
            height: 90px;
            background: #fff;
            border: 1px solid #000;
        }

        table {
            font-size: 12px;
            font-family: "宋体";
        }

        .bottom {
            width: 638px;
            margin-top: 25px;
        }

        .btmbtn {
            margin-right: 114px;
            float: right;
        }
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="<c:url value="/factory/create"/>">
    <div class="main border">
        <div class="top topw">
            <div class="toptt">工厂资料</div>
        </div>
        <div class="topline toplw"></div>
        <table width="519" border="0" align="center" cellpadding="4" cellspacing="0">
            <tr>
                <td width="102"><input name="textfield" type="text" class="w102" id="textfield" value="工厂/公司名称"
                                       readonly="readonly"/></td>
                <td colspan="3"><input type="text" name="name" id="name" class="w404"/></td>
            </tr>
            <tr>
                <td><input name="textfield2" type="text" class="w102" id="textfield2" value="主营" readonly="readonly"/></td>
                <td width="148"><input type="text" name="mainProduct" id="mainProduct" class="w148"/></td>
                <td width="90"><input name="textfield14" type="text" class="w90" id="textfield14" value="联系人" readonly="readonly"/></td>
                <td width="148"><input type="text" name="linkman" id="linkman" class="w148"/></td>
            </tr>
            <tr>
                <td><input name="textfield3" type="text" class="w102" id="textfield3" value="手机" readonly="readonly"/></td>
                <td><input type="text" name="mobileNumber" id="mobileNumber" class="w148"/></td>
                <td><input name="textfield15" type="text" class="w90" id="textfield15" value="电话" readonly="readonly"/></td>
                <td><input type="text" name="phoneNumber" id="phoneNumber" class="w148"/></td>
            </tr>
            <tr>
                <td><input name="textfield4" type="text" class="w102" id="textfield4" value="传真" readonly="readonly"/></td>
                <td><input type="text" name="fax" id="fax" class="w148"/></td>
                <td><input name="textfield16" type="text" class="w90" id="textfield16" value="QQ" readonly="readonly"/></td>
                <td><input type="text" name="qq" id="qq" class="w148"/></td>
            </tr>
            <tr>
                <td><input name="textfield5" type="text" class="w102" id="textfield5" value="地址" readonly="readonly"/></td>
                <td colspan="3"><input type="text" name="address" id="address" class="w404"/></td>
            </tr>
            <tr>
                <td><input name="textfield17" type="text" class="w102h" id="textfield17" value="备注" readonly="readonly"/></td>
                <td colspan="3"><textarea name="remark" id="remark" cols="45" rows="5" class="w404h"></textarea>
                </td>
            </tr>
        </table>

        <div class="bottom">
            <div class="btmbtn"><input id="btn-confirm" type="image" src="<c:url value="/resources/quotation/images/tijiao2.png"/>" class="btn"/></div>
        </div>
    </div>
</form>

<%--<form action="<c:url value="/factory/create"/>" method="post">--%>

    <%--厂名: <input type="text" value="" name="name" /><br/>--%>
    <%--主要产品: <input type="text" value="" name="mainProduct" /><br/>--%>
    <%--产品数量: <input type="text" value="" name="productQuantity" /><br/>--%>
    <%--联系人: <input type="text" value="" name="linkman" /><br/>--%>
    <%--联系号码: <input type="text" value="" name="contactNumber" /><br/>--%>
    <%--地址: <input type="text" value="" name="address" /><br/>--%>
    <%--备注: <input type="text" value="" name="remark" /><br/>--%>

    <%--<input type="submit" value="提交" />--%>
<%--</form>--%>
<script>
    var confirmUrl = '<c:url value="/ajax/factory/create" />';
</script>
<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/factory/js/create.js"/>"></script>
</body>
</html>
