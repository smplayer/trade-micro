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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/order/css/order.css"/>"/>
    <style type="text/css">
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">


    <c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
        <c:param name="backgroundColor" value=""/>
        <c:param name="currentModule" value="order"/>
        <c:param name="currentSubModule" value="operating"/>
        <c:param name="title" value="订单管理"/>
    </c:import>


    <div class="top2 top2w">
        <div class="topfl fl">客户名：
        </div>
        <div class="fl"><input name="textfield" type="text" id="textfield" size="15" class="border"/>
            查询
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
    <table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
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
            <td rowspan="2" class="td40 toptdbg">全选</td>
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
        <tr>
            <td height="24" class="break tdbg">&nbsp;</td>
            <td class="break tdbg">累计</td>
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
            <td width="130" align="right" valign="top"><input type="image" src="images/save4.png" class="btn"/></td>
            <td width="70" align="right" valign="top"><input type="image" src="images/del4.png" class="btn"/></td>
            <td width="70" align="center" valign="top">&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>
