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
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>订单管理</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/order/css/order.css"/>"/>
    <style type="text/css">

        #customer-list {
            margin-top: 25px;
            overflow: hidden;
        }
        #customer-list .text{
            width: 60px;
        }
        #customer-list .text.key-cus-name {
        }

        #customer-list ul {
            margin: 0 auto;
            padding: 0;
            overflow: hidden;
            width: 1120px;
        }
        #customer-list li {
            background-color: #fff;
            list-style: none;
            float: left;
            width: 80px;
            margin-left: 5px;
            border: 1px solid #B47BFE;
            height: 18px;
        }
        .favor-customer a {
            display: block;
            width: 100%;
        }
        #customer-list .favor-customer.current a{
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
        <c:param name="backgroundColor" value=""/>
        <c:param name="currentModule" value="order"/>
        <c:param name="currentSubModule" value="operating"/>
        <c:param name="title" value="订单管理"/>
    </c:import>

    <div id="customer-list">
        <ul class="template hidden">
            <li class="text key-cus-name">
                客户名:
            </li>

            <c:forEach var="i" begin="1" end="10" step="1" varStatus="status">
                <li class="favor-customer empty i${i}">
                    <a href="#" class="hidden"></a>
                </li>
            </c:forEach>

            <li class="text more" id="set-favor-customer-list">
                <a href="<c:url value="/order/favorCustomer/setting"/>" class="open-in-dialog" >更多</a>
            </li>
        </ul>
    </div>

    <div class="top2 top2w">
        <div class="topfl fl">客户名：&nbsp;${favor.customerName}
        </div>
        <div class="fl">
            <input name="keywords" type="text" id="keywords" size="15" class="border"/>
            <a href="javascript:void(0);" id="search">查询</a>
        </div>
        <div class="fr topr2" style="text-align: right">
            <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                <c:param name="prePageImage" value="/resources/common/project/images/left_03.png"  />
                <c:param name="nextPageImage" value="/resources/common/project/images/right_03.png"  />
                <c:param name="pageIndex" value="${page.pageIndex}"  />
                <c:param name="pageQuantity" value="${page.pageQuantity}"  />
                <c:param name="url" value="/order${empty param.keywords ? '' : '?keywords='}${empty param.keywords ? '' : param.keywords}"/>
            </c:import>
        </div>
        <div class="fr">
            <a href="#">录入货号</a>
        </div>
    </div>
    <table id="main-table" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333" style="width: 1332px;">
        <tr>
            <td height="40" rowspan="2" class="td39 toptdbg">序号</td>
            <td rowspan="2" class="td65 toptdbg">添加日期</td>
            <td rowspan="2" class="td50 toptdbg">布产</td>
            <td rowspan="2" class="td80 toptdbg">厂名</td>
            <td rowspan="2" class="td50 toptdbg">图片</td>
            <td rowspan="2" class="td80 toptdbg">品名</td>
            <td rowspan="2" class="td70 toptdbg">货号</td>
            <td rowspan="2" class="td50 toptdbg">包装</td>
            <td rowspan="2" class="td45 toptdbg">单位</td>
            <td rowspan="2" class="td45 toptdbg">厂价</td>
            <td rowspan="2" class="td50 toptdbg">功能</td>
            <td colspan="3" class="toptdbg">包装资料</td>
            <td colspan="3" class="toptdbg">下单</td>
            <td colspan="2" class="toptdbg">发货情况/箱</td>
            <td colspan="3" class="toptdbg">装运计划</td>
            <td rowspan="2" class="td115 toptdbg">备 注</td>
            <td rowspan="2" class="td40 toptdbg">
                <a href="javascript:void(0);" id="select-all">全选</a>
            </td>
        </tr>
        <tr>
            <td class="td85 toptdbg">纸箱规格</td>
            <td class="td45 toptdbg">装量</td>
            <td class="td70 toptdbg">毛/净重</td>
            <td class="td40 toptdbg">箱数</td>
            <td class="td40 toptdbg">体积</td>
            <td class="td50 toptdbg">货款</td>
            <td class="td45 toptdbg">发货</td>
            <td class="td45 toptdbg">剩货</td>
            <td class="td40 toptdbg">箱数</td>
            <td class="td40 toptdbg">体积</td>
            <td class="td50 toptdbg">货款</td>
        </tr>

        <c:forEach items="${page.dataList}" var="p" varStatus="status">
        <tr class="item">
            <td height="24" class="break tdbg">
                ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
            </td>
            <td class="break tdbg">
                <fmt:formatDate value="${p.addedDate}" pattern="MM-dd" />
            </td>
            <td class="break tdbg">布产</td>
            <td class="break tdbg">
                <a href="javascript:void(0);" class="factoryName" id="${p.factoryId}">${p.factoryName}</a>
            </td>
            <td class="break tdbg">
                <c:if test="${not empty p.imageURL}">
                    <img src="${ctx}/${p.imageURL}" style="width: 20px; height: 20px" />
                </c:if>
            </td>
            <td class="break tdbg">${p.companyProductName}</td>
            <td class="break tdbg">${p.companyProductNo}</td>
            <td class="break tdbg">${p.packageForm}</td>
            <td class="break tdbg">${p.unit}</td>
            <td class="break tdbg"><fmt:formatNumber value="${p.factoryPrice}" maxFractionDigits="1" pattern="#.#" /></td>
            <td class="break tdbg">${p.functionDescription}</td>
            <td class="break tdbg">
                <input type="text" value="${p.cartonSize}" name="cartonSize" />
            </td>
            <td class="break tdbg">
                <input type="text" value="${p.packingQuantity}" name="packingQuantity" />
            </td>
            <td class="break tdbg">
                <input type="text" value="<fmt:formatNumber value="${p.grossWeight}" maxFractionDigits="1" pattern="#.#" />"
                       name="grossWeight" style="width: 25px;" />/<input
                    type="text" value="<fmt:formatNumber value="${p.netWeight}" maxFractionDigits="1" pattern="#.#" />"
                    name="netWeight" style="width: 25px;" />
            </td>
            <td class="break tdbg"><input type="text" value="${p.orderedCartonQuantity}" name="orderedCartonQuantity" /></td>
            <td class="break tdbg"><fmt:formatNumber value="${p.volume}" maxFractionDigits="1" pattern="#.#" /></td>
            <td class="break tdbg"><fmt:formatNumber value="${p.payment}" maxFractionDigits="0" pattern="#.#" /></td>
            <td class="break tdbg">${p.deliveredCartonQuantity}</td>
            <td class="break tdbg">${p.remainingCartonQuantity}</td>
            <td class="break tdbg"><input type="text" value="${p.scheduledDeliverableCartonQuantity}" name="scheduledDeliverableCartonQuantity" /></td>
            <td class="break tdbg">${p.scheduledDeliverableVolume}</td>
            <td class="break tdbg">${p.scheduledDeliverablePayment}</td>
            <td class="break tdbg"><input type="text" value="${p.remark}" name="remark" /></td>
            <td class="break tdbg">
                <input type="checkbox" class="" name="id" value="${p.id}" id="${p.id}" />
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
    </table>
    <table width="1330" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td height="21" colspan="4" align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
            <td height="20" width="1060" align="right" valign="top">转装柜操作
                <input name="textfield2" type="text" id="textfield2" size="5" class="border"/>
                立方米
            </td>
            <td width="130" align="right" valign="top"><input type="image" src="${ctx}/resources/order/images/save4.png" class="btn"/></td>
            <td width="70" align="right" valign="top"><input type="image" src="${ctx}/resources/order/images/del4.png" class="btn"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>



    <div id="factoryDetails" style="display: none; width: 100px; position: absolute; background-color: #fff; border: 1px solid #000;">
        <div>
            <span class="factoryName"></span>
        </div>
        <div>
            <span class="contactNumber"></span>
        </div>
    </div>




<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    var pageIndex = '${page.pageIndex}';
    var pageSize = '${page.pageSize}';
    var favorId = '${favor.id}';
    var currentModule = 'order';
</script>
<script type="text/javascript" src="<c:url value="/resources/order/js/order.js"/>"></script>
<script>

</script>




</body>
</html>
