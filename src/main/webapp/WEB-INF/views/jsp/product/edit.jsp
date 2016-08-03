<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>样品录入</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link href="<c:url value="/resources/common/project/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .toptt {
            letter-spacing: 22px;
            color: #000;
        }

        .top div {
            margin-left: 22px;
        }

        .toplw {
            width: 164px;
            margin-bottom: 22px;
        }

        .topw {
            width: 620px;
            padding-top: 32px;
        }

        .main {
            width: 638px;
            height: 358px;
            margin: 0 auto;
            background: #C8ECFA;
            margin-top: 60px;
        }

        table {
            font-size: 13px;
            font-family: "Mcrosoft Yahei";
        }

        table span {
            float: right;
            margin-right: 15px;
        }

        td.value {
            /*padding-left: 5px;*/
            width: 157px;
            height: 26px;
        }

        td.value input {
            padding-left: 5px;
        }

        td.key {
            width: 100px;
            height: 26px;
        }

        .findExistingFactory-wrap {
            float: right;
            margin-right: 20px;
        }

        td {
            word-break: break-all;
            word-wrap: break-word;
        }

        A.a2:link {
            COLOR: #016AD5;
        }

        A.a2:visited {
            COLOR: #016AD5;
        }

        A.a2:hover {
            COLOR: red;
        }

        .bottom {
            width: 640px;
            margin-top: 21px;
        }

        .btmbtn {
            margin-right: 99px;
            float: right;
            width: 66px;
            text-align: right;
        }

        #factoryName-with-action {
            /*padding-left: 10px;*/
        }
        #factoryName {
            width: 300px;
            float: left;
        }
        input {
            /*width: 100%;*/
        }
        #factoryProductName {
            width: 100%;
        }
        .value-width-unit input {
            width: 100px;
        }
    </style>
</head>

<body>
<form id="form" name="form" method="post" action="<c:url value="/ajax/product/saveOrUpdate"/>">
    <input type="hidden" id="factoryId" name="factoryId" />
    <div class="main border">
        <div class="top topw">
            <div class="toptt">样品录入</div>
        </div>
        <div class="topline toplw"></div>
        <table style="width: 519px" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
            <tr>
                <td style="height: 26px; width: 100px" align="center" class="key" bgcolor="#FFFFFF">工厂/公司名称</td>
                <td colspan="3" bgcolor="#FFFFFF" id="factoryName-with-action" class="value">
                    <div class="findExistingFactory-wrap float-right"><a href="javascript:void(0);" id="findExistingFactory" class="a2">查新</a></div>
                    <input type="text" id="factoryName" name="factoryName" value="${factoryName}" style="width: 350px" />
                </td>
            </tr>
            <tr>
                <td height="26" align="center" bgcolor="#FFFFFF" class="key">工厂品名</td>
                <td align="left" bgcolor="#FFFFFF" class="value" style="">
                    <input type="text" id="factoryProductName" name="factoryProductName" style="width: 128px" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">工厂货号</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="factoryProductNo" name="factoryProductNo" style="width: 128px" />
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="#FFFFFF" class="key">包装方式</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="packageForm" name="packageForm" style="width: 128px" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">单位</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="unit" name="unit" style="width: 128px" />
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="#FFFFFF" class="key">厂价</td>
                <td align="left" bgcolor="#FFFFFF" class="value-width-unit value">
                    <span>元</span>
                    <input type="text" id="factoryPrice" name="factoryPrice" class="float-left" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">功能</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="functionDescription" name="functionDescription" style="width: 128px" />
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="#FFFFFF" class="key">分类</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="category" name="category" style="width: 128px" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">细类</td>
                <td align="left" bgcolor="#FFFFFF" class="value">
                    <input type="text" id="subCategory" name="subCategory" style="width: 128px" />
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="#FFFFFF" class="key">纸箱规格</td>
                <td align="left" bgcolor="#FFFFFF" class="value-width-unit value">
                    <span>CM</span>
                    <input type="text" id="cartonSize" name="cartonSize" class="float-left" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">装量</td>
                <td align="left" bgcolor="#FFFFFF" class="value-width-unit value">
                    <span>PS</span>
                    <input type="text" id="packingQuantity" name="packingQuantity" class="float-left" />
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="#FFFFFF" class="key">毛重</td>
                <td align="left" bgcolor="#FFFFFF" class="value-width-unit value">
                    <span>KG</span>
                    <input type="text" id="grossWeight" name="grossWeight" class="float-left" />
                </td>
                <td align="center" bgcolor="#FFFFFF" class="key">净重</td>
                <td align="left" bgcolor="#FFFFFF" class="value-width-unit value">
                    <span>KG</span>
                    <input type="text" id="netWeight" name="netWeight" class="float-left" />
                </td>
            </tr>
        </table>
        <div class="bottom">

            <div class="fr btmbtn"><input id="btn-confirm" type="image" src="<c:url value="/resources/common/project/images/bluetj.png" />" class="btn"/></div>
            <div class="fr"><input id="btn-clear" type="image" src="<c:url value="/resources/common/project/images/blueqk.png" />" class="btn"/></div>
        </div>
    </div>
</form>




<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    var productId = "${product.id}";
</script>
<script type="text/javascript" src="<c:url value="/resources/product/js/edit.js" />"></script>
<script>



</script>

</body>
</html>
