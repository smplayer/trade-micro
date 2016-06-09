<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>见客操作</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .toptt {
            letter-spacing: 22px;
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
            height: 600px;
            margin: 0 auto;
            background: #F4D8B3;
        }

        .w522 {
            width: 520px;
            vertical-align: middle;
            height: 23px;
            border: 1px solid #000;
            line-height: 23px;
            background: #fff;
        }

        .w197 {
            width: 195px;
            vertical-align: middle;
            height: 23px;
            border: 1px solid #000;
            line-height: 23px;
            background: #fff;
        }

        .txt522 {
            width: 518px;
            height: 90px;
            background: #fff;
            border: 1px solid #000;
        }

        table {
            font-size: 16px;
            font-family: "Mcrosoft Yahei";
        }

        .bottom {
            width: 760px;
            margin-top: 17px;
        }

        .btmbtn {
            margin-right: 125px;
            float: right;
        }
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div class="main border">
        <div class="top topw">
            <div class="toptt">见客操作</div>
        </div>
        <div class="topline toplw" ></div>
        <table width="580" border="0" align="center" cellpadding="0" cellspacing="0">

            <c:forEach var="o" items="list" varStatus="status">
                <tr>
                    <td width="163" height="45">列号：</td>
                    <td width="180">客户名：</td>
                    <td width="217">国家/地区：</td>
                    <td width="20" align="right"><input type="radio" name="operating" id="checkbox"/></td>
                </tr>
            </c:forEach>

        </table>

        <div class="bottom">
            <div class="btmbtn"><input type="image" src="images/tijiaoy.png" class="btn"/></div>
        </div>
    </div>
</form>

<c:import url="/WEB-INF/views/jsp/common/common-script.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/confirming-order.js"/>"></script>

</body>
</html>
