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
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div class="top topw">
        <div class="toptt">工厂管理</div>
    </div>
    <div class="topline toplw"></div>
    <div class="top2 top2w">
        <div class="topfl fl">
            <input name="textfield" type="text" id="textfield" size="18" class="border"/>
            查询
        </div>
        <div class="fr topr2">
            <table width="98" border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="9"><img src="images/lefty.png" width="9" height="8"/></td>
                    <td width="80" align="center">1-000/000</td>
                    <td width="9"><img src="images/righty.png" width="9" height="8"/></td>
                </tr>
            </table>
        </div>
        <div class="fr">录入资料</div>
    </div>
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
    </table>
    <table width="1230" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="4" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" align="right" valign="top">&nbsp;</td>
            <td width="70" align="right" valign="top"><input type="image" src="images/save3.png" class="btn"/></td>
            <td width="70" align="right" valign="top"><input type="image" src="images/del3.png" class="btn"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>
