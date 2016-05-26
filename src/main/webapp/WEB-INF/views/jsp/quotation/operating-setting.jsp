<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>见客设置</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <style type="text/css">
        .toptt {
            margin: 0 auto;
            height: 22px;
            font: 500 22px/22px "Microsoft Yahei";
            letter-spacing: 10px;
            color: #f00;
        }

        .top div {
            margin-left: 22px;
        }

        .toplw {
            width: 164px;
            margin-bottom: 29px;
        }

        .topw {
            width: 620px;
            padding-top: 72px;
        }

        .main {
            width: 760px;
            height: 545px;
            margin: 0 auto;
            background: #F6D6FB;
            border: 1px solid #000;
            padding-top: 55px;
        }

        table {
            font-size: 12px;
            font-family: "宋体";
        }

        .bottom {
            width: 760px;
            margin-top: 28px;
        }

        .btmbtn {
            width: 74px;
            text-align: right;
            margin-right: 139px;
            float: right;
        }

        .input3 {
            border: 0;
            background: #F6D6FB;
            height: 20px;
            line-height: 20px;
            vertical-align: middle;
        }

        .w100, .w80, .w40, .w60 {
            vertical-align: middle;
            height: 18px;
            border: 1px solid #000;
            line-height: 18px;
            background: #fff;
        }

        .w100 {
            width: 98px;
        }

        .w80 {
            width: 78px;
        }

        .w40 {
            width: 38px;
        }

        .w60 {
            width: 58px;
        }

        .liehao {
            font-size: 16px;
            font-family: "Microsoft Yahei";
            font-weight: bold;
            margin-top: 5px;
            line-height: 22px;
            margin-right: 34px;
        }
    </style>
</head>

<body>
<form id="form" name="form" method="post" action="<c:url value="/quotation/operating/setting"/>">
    <input type="hidden" name="id" value="${quotation.id}"/>
    <div class="main">
        <table width="505" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td width="329" height="28" align="right" valign="top">
                    <div class="toptt">见客设置</div>
                </td>
                <td width="176" align="right" valign="bottom">
                    <div class="liehao">列号：&nbsp; ${quotation.serialNumber}</div>
                </td>
            </tr>
        </table>
        <table width="505" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="37" colspan="4" align="right">&nbsp;</td>
            </tr>
            <tr>
                <td width="103" height="46" align="right">客户名称：</td>
                <td width="183" align="left">
                    <input name="customerName" type="text" id="customerName" class="w100"
                           value="${quotation.customerName}"/></td>
                <td width="214" colspan="2" align="right">国家/地区：
                    <input name="region" type="text" id="region" class="w100" value="${quotation.region}"/></td>
            </tr>
            <tr>
                <td height="46" align="right">选择贸易条款：</td>
                <td colspan="3" align="left">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="73%">FOB
                                <input name="FOB" type="text" id="FOB" class="w80"
                                       value="${quotation.tradeClauseType == "FOB" ? quotation.tradeClause : ""}"/>
                                &nbsp;&nbsp;&nbsp;&nbsp; CNF
                                <input name="CNF" type="text" id="CNF" class="w80"
                                       value="${quotation.tradeClauseType == "CNF" ? quotation.tradeClause : ""}"/></td>
                            <td width="27%" align="right">GIF
                                <input name="GIF" type="text" id="GIF" class="w80"
                                       value="${quotation.tradeClauseType == "GIF" ? quotation.tradeClause : ""}"/></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td height="46" align="right">装运港：</td>
                <td colspan="3" align="left">
                    <input name="shipmentPort" type="text" id="shipmentPort" class="w80"
                           value="${quotation.shipmentPort}"/>
                </td>
            </tr>
            <tr>
                <td height="46" align="right">选择柜型：</td>
                <td colspan="3">
                    <table width="100%" border="0">
                        <tr>
                            <td><input type="radio" id="containerType1" name="containerType" value="1"
                                       class="input3" ${quotation.containerType == "1"?"checked":""}/>
                                <label for="containerType1">20尺</label>
                            </td>
                            <td><input type="radio" id="containerType2" name="containerType" value="2"
                                       class="input3" ${quotation.containerType == "2"?"checked":""}/>
                                <label for="containerType2">40尺</label>
                            </td>
                            <td><input type="radio" id="containerType3" name="containerType" value="3"
                                       class="input3" ${quotation.containerType == "3"?"checked":""}/>
                                <label for="containerType3">40尺加高</label>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td height="46" align="right">选择整柜利润：</td>
                <td colspan="3" align="left">（在货值上加点）
                    <input name="profitPercent" type="text" id="profitPercent" class="w40"
                           value="${quotation.profitPercent}"/>
                    %&nbsp;&nbsp;&nbsp;（加佣金）
                    <input name="profitAmount" type="text" id="profitAmount" class="w40"
                           value="<fmt:formatNumber type="number" pattern="#" value="${quotation.profitAmount}" maxFractionDigits="0"/>"/>
                    元
                </td>
            </tr>
            <tr>
                <td height="46" align="right">出口清关费用：</td>
                <td colspan="3" align="left">（内含陆运费）
                    <input name="customsClearanceFee" type="text" id="customsClearanceFee" class="w80"
                           value="<fmt:formatNumber type="number" pattern="#" value="${quotation.customsClearanceFee}" maxFractionDigits="0"/>"/>
                    </td>
            </tr>
            <tr>
                <td height="46" align="right">币种：</td>
                <td colspan="3" align="left">
                    <input name="currency" type="text" id="currency" class="w60" value="${quotation.currency}"/>
                    &nbsp; &nbsp;汇率：
                    <input name="exchangeRate" type="text" id="exchangeRate" class="w60" value="${quotation.exchangeRate}"/>
                    %&nbsp; &nbsp;小数点后保留：
                    <input name="decimalPlaces" type="text" id="decimalPlaces" class="w40" value="${quotation.decimalPlaces}"/>
                    位
                </td>
            </tr>
            <tr>
                <td height="46" align="right">制表：</td>
                <td colspan="3" align="left">
                    <input name="editor" type="text" id="editor" class="w100" value="${quotation.editor}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 电话：
                    <input name="tel" type="text" id="tel" class="w100" value="${quotation.tel}"/>
                    </td>
            </tr>
        </table>
        <div class="bottom">
            <div class="btmbtn fl">
                <input type="image" id="confirm" src="<c:url value="/resources/quotation/images/tijiao2.png"/>" class="btn"/>
                </div>
            <div class="fr"><a href="javascript:void(0);" id="pre-reset">清空</a></div>
        </div>
    </div>
    </div>
</form>

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
