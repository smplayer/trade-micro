<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>报价表设置</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <style type="text/css">

        html, body {
            height: 100%;
            overflow: hidden;
        }

        #container{
            margin: 0 auto;
            width: 982px;
            height: 100%;
            overflow: hidden;
            border-left: 1px solid #000;
            border-right: 1px solid #000;
            background: url(<c:url value="/resources/common/project/images/bjt_46.png"/>) repeat #F0E9D1;
        }

        .main {
            width: 500px;
            height: 530px;
            margin: 20px auto;
            background: #F6D6FB;
            padding: 0 60px;
            border: 1px solid #333;
        }

        input {
            border: 1px solid #333;
            color: #333;
            background: #fff;
            height: 20px;
            line-height: 20px;
        }

        .input2 {
            border: 0;
            background: #F6D6FB;
            height: 20px;
            line-height: 20px;
            vertical-align: middle;
        }

        .btn {
            height: 23px;
            width: 42px;
            cursor: pointer;
            border: 0;
            background: none;
        }
    </style>
</head>

<body>

<div id="container">

    <c:import url="/WEB-INF/views/jsp/quotation/common/setting-header.jsp" >
        <c:param name="title" value="见客设置"/>
        <c:param name="curPage" value="setting"/>
        <c:param name="hideNav" value="true"/>
    </c:import>

    <form id="form" name="form" method="post" action="<c:url value="/quotation/operating/setting"/>">
        <input type="hidden" name="id" value="${quotation.id}" />
        <div class="main">
            <table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td height="50" colspan="4" align="right">&nbsp;</td>
                </tr>
                <tr>
                    <td width="103" height="46" align="right">客户名称：</td>
                    <td width="183" align="left">
                        <input name="customerName" type="text" id="customerName" size="16" value="${quotation.customerName}"/></td>
                    <td width="214" colspan="2" align="right">国家/地区：
                        <input name="region" type="text" id="region" size="15" value="${quotation.region}"/></td>
                </tr>
                <tr>
                    <td height="46" align="right">选择贸易条款：</td>
                    <td colspan="3" align="left">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="73%">FOB
                                    <input name="FOB" type="text" id="FOB" size="9" value="${quotation.tradeClauseType == "FOB" ? quotation.tradeClause : ""}"/>
                                    &nbsp;&nbsp;&nbsp;CNF <input name="CNF" type="text" id="CNF" size="9" value="${quotation.tradeClauseType == "CNF" ? quotation.tradeClause : ""}"/>
                                    &nbsp;&nbsp;&nbsp;GIF
                                    <input name="GIF" type="text" id="GIF" size="9" value="${quotation.tradeClauseType == "GIF" ? quotation.tradeClause : ""}"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="46" align="right">装运港：</td>
                    <td colspan="3" align="left"><input name="shipmentPort" type="text" id="shipmentPort" size="16" value="${quotation.shipmentPort}"/></td>
                </tr>
                <tr>
                    <td height="46" align="right">选择柜型：</td>
                    <td colspan="3">
                        <table width="100%" border="0">
                            <tr>
                                <td><input type="radio" id="containerType1" name="containerType" value="1" class="input2" ${quotation.containerType == "1"?"checked":""}/>
                                    <label for="containerType1">20尺</label>
                                </td>
                                <td><input type="radio" id="containerType2" name="containerType" value="2" class="input2" ${quotation.containerType == "2"?"checked":""}/>
                                    <label for="containerType2">40尺</label>
                                </td>
                                <td><input type="radio" id="containerType3" name="containerType" value="3" class="input2" ${quotation.containerType == "3"?"checked":""}/>
                                    <label for="containerType3">40尺加高</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="46" align="right">选择整柜利润：</td>
                    <td colspan="3" align="left">（在货值上加点）
                        <input name="profitPercent" type="text" id="profitPercent" size="10" value="${quotation.profitPercent}"/>
                        %&nbsp;&nbsp; 加佣金
                        <input name="profitAmount" type="text" id="profitAmount" size="10" value="<fmt:formatNumber type="number" pattern="#" value="${quotation.profitAmount}" maxFractionDigits="0"/>"/>
                        元
                    </td>
                </tr>
                <tr>
                    <td height="46" align="right">出口清关费用：</td>
                    <td colspan="3" align="left">（内含陆运费）
                        <input name="customsClearanceFee" type="text" id="customsClearanceFee" size="12" value="<fmt:formatNumber type="number" pattern="#" value="${quotation.customsClearanceFee}" maxFractionDigits="0"/>"/></td>
                </tr>
                <tr>
                    <td height="46" align="right">币种：</td>
                    <td colspan="3" align="left"><input name="currency" type="text" id="currency" size="6" value="${quotation.currency}"/>
                        &nbsp;&nbsp;汇率：<input name="exchangeRate" type="text" id="exchangeRate" size="6" value="${quotation.exchangeRate}"/>
                        &nbsp;&nbsp;小数点后保留：
                        <input name="decimalPlaces" type="text" id="decimalPlaces" size="5" value="${quotation.decimalPlaces}"/>位
                    </td>
                </tr>
                <tr>
                    <td height="46" align="right">制表：</td>
                    <td colspan="3" align="left"><input name="editor" type="text" id="editor" size="16" value="${quotation.editor}"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        电话：<input name="tel" type="text" id="tel" size="16" value="${quotation.tel}"/></td>
                </tr>
                <tr>
                    <td height="90" colspan="4" align="right">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="78%" align="right">
                                    <a href="javascript:void(0);" id="pre-reset">清空</a>
                                    <input type="reset" name="reset" id="reset" style="display: none;" />
                                </td>
                                <td width="22%" align="center"><input type="image" id="confirm" src="<c:url value="/resources/quotation/images/setting-confirm.png"/>" class="btn"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/operating-setting.js"/>"></script>
<script>

    $("#pre-reset").click(function(){
        $("input[type=text]", "#form").each(function () {
            $(this).val("");
        });
        $("input[type=radio]", "#form").each(function () {
            this.checked = false;
        });
    });

</script>

</body>
</html>
