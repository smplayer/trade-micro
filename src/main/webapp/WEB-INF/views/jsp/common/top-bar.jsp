<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
    #top-bar {
    }

    .menu1, .menu2 {
        width: 1500px;
        background: ${not empty param.backgroundColor ? param.backgroundColor : "#fff"};
        margin: 0 auto;
    }

    .menu1 {
        height: 45px;
        overflow: hidden;
    }

    .menu1.setting-favor {
        height: 70px;
    }

    .logo1 {
        margin-top: 6px;
        margin-left: 180px;
        margin-right: 60px;
    }

    .nav, .nav2 {
        padding-top: 5px;
        width: 696px;
        margin-left: 28px;
    }

    .nav {
        height: 40px;
    }

    .nav2 {
        height: 65px;
    }
    #menu-current ul {
        margin: 0;
        margin-left: 323px;
        padding: 0;
        overflow: hidden;
    }

    .nav li, .nav2 li, #menu-current li {
        width: 116px;
        float: left;
    }

    .nav li img, .nav2 li img {
        margin-bottom: 0;
    }

    .nav li p, .nav2 li p {
        margin-top: 3px;
        width: 116px;
        text-align: center;
    }

    .nav li a, .nav2 li a {
        font-size: 12px;
        font-family: "Microsoft Yahei";
        margin-top: 0;
        line-height: 11px;
    }

    .nav li a:hover, .nav2 li a:hover {
        color: #f00;
    }

    .nav2 input {
        margin-top: 10px;
        background: #fff;
    }

    .menur {
        margin-right: 176px;
        margin-top: 3px;
    }

    .height20 {
        width: 100%;
        height: 50px;
        clear: both;
    }

    .face {
        width: 27px;
        margin-top: 9px;
        margin-right: 20px;
    }

    .menur a {
        font: 600 13px/20px "Microsoft Yahei";
        letter-spacing: 3px;
    }

    .tj {
        margin-top: 45px;
    }

    .menu .current {
        color: red;
    }
</style>


<div id="top-bar" class="menu menu1">
    <div class="logo1 fl">
        <img src="<c:url value="/resources/common/project/images/logo.png"/>" width="53"/>
    </div>
    <div class="logo2 fl"></div>
    <div class="nav2 fl">
        <ul>
            <li class="menu-item quotation"><img src="<c:url value="/resources/common/project/images/menu5.png"/>" height="18"/>
                <p><a id="action-quotation" href="<c:url value="/quotation/operating"/>" class="${param.currentModule == 'quotation' ? 'current' : ''}">见客下单</a><br/><input name="favor-module" type="radio" value="quotation" /></p>
            </li>
            <li class="menu-item product"><img src="<c:url value="/resources/common/project/images/menu1.png"/>" height="18"/>
                <p><a id="action-product" href="<c:url value="/product/list"/>" class="${param.currentModule == 'product' ? 'current' : ''}">产品库</a><br/><input name="favor-module" type="radio" value="product"/></p></li>
            <li class="menu-item factory"><img src="<c:url value="/resources/common/project/images/menu3.png"/>" height="18"/>
                <p><a id="action-factory" href="<c:url value="/factory/list"/>" class="${param.currentModule == 'factory' ? 'current' : ''}">工厂管理</a><br/><input name="favor-module" type="radio" value="factory"/></p></li>
            <li class="menu-item order"><img src="<c:url value="/resources/common/project/images/menu2.png"/>" height="18"/>
                <p><a id="action-order" href="<c:url value="/order/list"/>" class="${param.currentModule == 'order' ? 'current' : ''}">订单管理</a><br/><input name="favor-module" type="radio" value="order"/></p></li>
            <li class="menu-item packing"><img src="<c:url value="/resources/common/project/images/menu7.png"/>" height="18"/>
                <p><a id="action-packing" href="#" class="${param.currentModule == 'packing' ? 'current' : ''}">装柜制单</a><br/><input name="favor-module" type="radio" value="packing"/></p></li>
            <li class="menu-item payable"><img src="<c:url value="/resources/common/project/images/menu6.png"/>" height="18"/>
                <p><a id="action-payable" href="#" class="${param.currentModule == 'payable' ? 'current' : ''}">应付货款</a><br/><input name="favor-module" type="radio" value="payable"/></p></li>
        </ul>
    </div>
    <div class="tj fl"><a href="javascript:void(0);" id="set-favor-module-confirm">提交</a></div>
    <div class="menur fr">
        <table width="196" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="center" valign="middle"><a href="javascript:void(0)" id="modify-password">修改密码</a></td>
                <td align="center" valign="middle"><a href="javascript:void(0)" id="set-start-number">货号设置</a></td>
            </tr>
            <tr>
                <td align="center" valign="middle"><a href="javascript:void(0);" id="set-favor-module">设置当前页</a></td>
                <td align="center" valign="middle"><a href="<c:url value="/system/construction"/>">关于解说</a></td>
            </tr>
        </table>
    </div>
    <div class="face fr"><img src="<c:url value="/resources/common/project/images/face.png"/>" width="27" height="27"/></div>
</div>

<style type="text/css">
    #notice-current-module {
        display: none;
        color: red;
        width: 98px;
        height: 15px;
        line-height: 15px;
        border: 1px solid #000;
        border-top: 0px;
        background-color: #fff;
        text-align: center;
        position: absolute;
    }
</style>

<div id="notice-current-module">
</div>

<style type="text/css">
    .drop-down-menu{
        position: absolute;
        padding: 22px 0 5px 0;
        display: none;
        z-index: 1000;
        line-height: 2em;
    }
    .drop-down-menu .wrap {
        padding: 20px 0;
        background-color: #fff;
        border: 1px solid #000;
        width: 100px;
    }
    .drop-down-menu .disabled {
        color: #aaa;
    }
</style>
<div class="drop-down-menu quotation">
    <div class="wrap">
        <div>
            <a href="<c:url value="/quotation/operating/setting?id=${quotation.id}"/>">见客设置</a>
        </div>
        <div>
            <a href="<c:url value="/quotation/operating"/>">操作版</a>
        </div>
        <div>
            <c:if test="${param.currentSubModule != 'operating'}" var="notOperating">
                <span class="disabled"> 确认版</span>
            </c:if>
            <c:if test="${not notOperating}">
                <a href="<c:url value="/quotation/confirming/order?id=${quotation.id}"/>" target="_blank">确认版</a>
            </c:if>
        </div>
        <div>

            <c:if test="${param.currentSubModule != 'operating'}" var="notOperating">
                <span class="disabled">存档</span>
            </c:if>
            <c:if test="${not notOperating}">
                <a href="<c:url value="/quotation/saveToArchive"/>" id="quotation-saveToArchive">存档</a>
            </c:if>
        </div>
        <div>
            <a href="<c:url value="/quotation/archiveList"/>">档案</a>
        </div>
    </div>
</div>
<div class="drop-down-menu product">
    <div class="wrap">
        <div>
            <a href="<c:url value="/product/list"/>">操作版</a>
        </div>
        <div>
            <a href="<c:url value="/product/list/complete"/>">标准产品</a>
        </div>
        <div>
            <a href="<c:url value="/product/list/incomplete"/>">补料产品</a>
        </div>
    </div>
</div>
<div class="drop-down-menu factory">
    <div class="wrap">
        <div>
            <a href="<c:url value="/factory/list"/>">工厂管理</a>
        </div>
    </div>
</div>
<div class="drop-down-menu order">
    <div class="wrap">
        <div>
            <a href="<c:url value="/order/list"/>">订单管理</a>
        </div>
    </div>
</div>
<div class="drop-down-menu packing">
    <div class="wrap">
        <div>
            <a href="<c:url value="/packing"/>">操作版</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">预装清单</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">装箱单</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">商业发票</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">补料单</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">存档</a>
        </div>
        <div>
            <a href="<c:url value="/packing"/>">档案</a>
        </div>
    </div>
</div>
<div class="drop-down-menu payable">
    <div class="wrap">
        <div>
            <a href="<c:url value="/payable"/>">应付货款</a>
        </div>
    </div>
</div>

<script>
    var title = '${param.title}';
</script>