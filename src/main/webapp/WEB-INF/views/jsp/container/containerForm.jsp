<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    //    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>装柜制单</title>
<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/container/css/containerForm.css"/>"/>
<style type="text/css">



    #container-list {
        margin-top: 35px;
        overflow: hidden;
    }
    #container-list .text{
        width: 75px;
    }
    #container-list .text.key-container-num {
    }

    #container-list ul {
        margin: 0 auto;
        padding: 0;
        overflow: hidden;
        width: 1154px;
    }
    #container-list li {
        background-color: #fff;
        list-style: none;
        float: left;
        width: 85px;
        margin-left: 10px;
        border: 1px solid #B47BFE;
        height: 18px;
    }
    .favor-container a {
        display: block;
        width: 100%;
    }
    #container-list .favor-container.current a{
        color: red;
    }

    #main-table input[type=text] {
        width: 100%;
        text-align: center;
    }
</style>
</head>

<body>


<c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
    <c:param name="bgColor" value="#e8b796"/>
    <c:param name="currentModule" value="container"/>
    <c:param name="title" value="确认表"/>
</c:import>








<div id="container-list">
    <ul class="template hidden">
        <li class="text key-container-num">
            柜别:
        </li>

        <c:forEach var="i" begin="1" end="10" step="1" varStatus="status">
            <li class="favor-container empty i${i}">
                <a href="#" class="hidden"></a>
            </li>
        </c:forEach>

        <li class="text more" id="set-favor-container-list">
            <a href="<c:url value="/container/favor/setting"/>" class="open-in-dialog" >更多</a>
        </li>
    </ul>
</div>














    <div class="top2 top2w" style="margin-top: 10px">
        <div class="topfl fl">客户名：&nbsp;${sheet.customerName}</div>
        <div class="topf2 fl">币种：&nbsp;${sheet.currency}</div>
        <div class="topf3 fl">装运港 <input id="shipmentPort" name="shipmentPort" value="${container.shipmentPort}" type="text" class="w80"/></div>
        <div class="fl" >目的港 <input id="destinationPort" name="destinationPort" value="${container.destinationPort}" type="text" class="w80"/></div>
        <div class="fr topr2">
            <%--<c:import url="/WEB-INF/views/jsp/common/paging.jsp">--%>
                <%--<c:param name="prePageImage" value="/resources/container/images/pre-page.png"  />--%>
                <%--<c:param name="nextPageImage" value="/resources/container/images/next-page.png"  />--%>
                <%--<c:param name="pageIndex" value="${page.pageIndex}"  />--%>
                <%--<c:param name="pageQuantity" value="${page.pageQuantity}"  />--%>
                <%--<c:param name="url" value="/container"/>--%>
            <%--</c:import>--%>
        </div>
        <div class="topr3 fr" style="margin-right: 200px">转应付货款</div>
        <div class="fr">转已装运</div>
    </div>
    <table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333" style="margin-top: 3px;">
        <tr>
            <td height="40" rowspan="2" class="td50 toptdbg">图片</td>
            <td rowspan="2" class="td75 toptdbg">品 名</td>
            <td rowspan="2" class="td85 toptdbg">公司货号</td>
            <td rowspan="2" class="td60 toptdbg">包装方式</td>
            <td rowspan="2" class="td40 toptdbg">单位</td>
            <td colspan="2" class="toptdbg">价格/元</td>
            <td rowspan="2" class="td55 toptdbg">功能</td>
            <td colspan="3" class="toptdbg">包装资料</td>
            <td rowspan="2" class="td55 toptdbg">箱数</td>
            <td rowspan="2" class="td55 toptdbg">总数量</td>
            <td rowspan="2" class="td55 toptdbg">总体积</td>
            <td rowspan="2" class="td55 toptdbg">总毛重</td>
            <td rowspan="2" class="td55 toptdbg">总净重</td>
            <td colspan="2" class="toptdbg">货 款</td>
            <td rowspan="2" class="td230 toptdbg">备 注</td>
            <td rowspan="2" class="td39 toptdbg">
                <a href="javascript:void(0);" id="select-all">全选</a>
            </td>
        </tr>
        <tr>
            <td class="td45 toptdbg">报价</td>
            <td class="td45 toptdbg">厂价</td>
            <td class="td85 toptdbg">纸箱规格</td>
            <td class="td55 toptdbg">装量</td>
            <td class="td70 toptdbg">毛/净重</td>
            <td class="td60 toptdbg">客户</td>
            <td class="td60 toptdbg">工厂</td>
        </tr>

        <c:forEach items="${containerProductItemList}" var="item" varStatus="status">
            <tr>
                <td height="24" class="break tdbg">&nbsp;</td>
                <td class="break tdbg">${item.companyProductName}</td>
                <td class="break tdbg">${item.companyProductNo}</td>
                <td class="break tdbg">${item.packageForm}</td>
                <td class="break tdbg">${item.unit}</td>
                <td class="break tdbg"><fmt:formatNumber value="${item.quotedPrice}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg"><fmt:formatNumber value="${item.factoryPrice}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg">${item.functionDescription}</td>
                <td class="break tdbg">${item.cartonSize}</td>
                <td class="break tdbg">${item.packingQuantity}</td>
                <td class="break tdbg">
                    <fmt:formatNumber value="${item.grossWeight}" pattern="#.#" maxFractionDigits="1" />/<fmt:formatNumber value="${item.netWeight}" pattern="#.#" maxFractionDigits="1" />
                <td class="break tdbg">${item.orderedCartonQuantity}</td>
                <td class="break tdbg">${item.orderedCartonQuantity * item.packingQuantity}</td>
                <td class="break tdbg">
                    <fmt:formatNumber value="${item.totalVolume}" pattern="#.#" maxFractionDigits="1" />
                </td>
                <td class="break tdbg"><fmt:formatNumber value="${item.totalGrossWeight}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg"><fmt:formatNumber value="${item.totalNetWeight}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg"><fmt:formatNumber value="${item.factoryPayment}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg"><fmt:formatNumber value="${item.customPayment}" pattern="#.#" maxFractionDigits="1" /></td>
                <td class="break tdbg">${item.remark}</td>
                <td class="break tdbg"><input type="checkbox" value="${item.id}" name="id" class="checkbox-item"/></td>
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
        <tr>
            <td height="24" class="break tdbg">合计</td>
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
    <table width="1330" border="0" align="center" cellpadding="0" cellspacing="0" style="line-height: 23px">
        <tr>
            <td height="30" colspan="12" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td width="151" height="20" align="right" valign="top">装柜日期 <input type="text" name="deliveryDate" id="deliveryDate" value="${container.deliveryDate}" class="w60"/></td>
            <td width="111" align="right" valign="top">柜型 <input type="text" name="containerType" id="containerType" value="${container.containerType}" class="w60"/></td>
            <td width="130" align="right" valign="top">定仓号 <input type="text" name="cabinNo" id="cabinNo" value="${container.cabinNo}" class="w60"/></td>
            <td width="121" align="right" valign="top">柜号 <input type="text" name="containerNo" id="containerNo" value="${container.containerNo}" class="w60"/></td>
            <td width="116" align="right" valign="top">封号 <input type="text" name="sealNo" id="sealNo" value="${container.sealNo}" class="w60"/></td>
            <td width="129" align="right" valign="top">承运人 <input type="text" name="carrier" id="carrier" value="${container.carrier}" class="w60"/></td>
            <td width="362" align="left" valign="top">
                <a href="javascript:void(0);" style="float: left; margin-left: 30px;" id="moveItemsToLastContainer">过页</a>
                <a href="javascript:void(0);" style="float: left; margin-left: 30px;" id="getAccumulativeTotal">累计</a>
                <a href="javascript:void(0);" style="float: left; margin-left: 30px;">装运导入</a>
                <a href="javascript:void(0);" style="float: left; margin-left: 30px;">订单导入</a>

            </td>
            <td width="70" align="right" valign="top"><input type="image" src="${ctx}/resources/container/images/save.png" class="btn" id="save"/>
            </td>
            <td width="70" align="right" valign="top"><input type="image" src="${ctx}/resources/container/images/del.png" class="btn" id="del"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>





<div id="dialog-accumulation" style="display: none; background-color: #fff; width: 300px; height: 240px; border: 2px solid #a839a8;">
    <div style="text-align: right;">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"
                 style="width: 24px; height: 24px; margin: 10px 10px 0px 0px"/>
        </a>
    </div>
    <div>
        <h2 style="display: inline-block; font-weight: normal; letter-spacing: 24px; padding-left: 20px; margin: 0 0 0 0; border-bottom: 1px solid red; padding-bottom: 3px;">
            累计</h2>
    </div>
    <table style="width: 200px; background-color: #000; margin: 20px auto 0 auto" border="0" cellpadding="0"
           cellspacing="1">
        <tr>
            <td style="width: 100px; height: 30px; background-color: #f0c9f0">栏目</td>
            <td style="width: 100px; height: 30px; background-color: #f0c9f0">数据</td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px; background-color: #fff">总箱数</td>
            <td style="width: 100px; height: 20px; background-color: #fff" id="total-carton-quantity"></td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px; background-color: #fff">总数量</td>
            <td style="width: 100px; height: 20px; background-color: #fff" id="total-product-quantity"></td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px; background-color: #fff">总体积</td>
            <td style="width: 100px; height: 20px; background-color: #fff" id="total-volume"></td>
        </tr>
        <tr>
            <td style="width: 100px; height: 20px; background-color: #fff">总金额</td>
            <td style="width: 100px; height: 20px; background-color: #fff" id="total-amount"></td>
        </tr>
    </table>
</div>




<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/container/js/containerForm.js"/>"></script>
<script>
var sheetId = '${sheet.id}';
var containerId = '${container.id}';
</script>




</body>
</html>
