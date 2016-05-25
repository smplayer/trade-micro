<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<style type="text/css">
    .top-bar {
        width: 1500px;
        background: #25C885;
        margin: 0 auto;
    }

    .top-bar {
        height: 70px;
    }

    .top-bar.setting-favor {
        height: 90px;
    }

    .top-bar .logo1 {
        margin-top: 9px;
        margin-left: 160px;
        margin-right: 30px;
    }

    .top-bar .logo2 {
        margin-top: 22px;
    }

    .top-bar .nav{
        padding-top: 14px;
        width: 660px;
        margin-left: 28px;
    }

    .top-bar .nav {
        height: 57px;
    }

    .top-bar.setting-favor .nav {
        height: 77px;
    }

    .top-bar .nav li {
        float: left;
        width: 110px;
    }

    /*.top-bar .nav li p {*/
    /*margin-top: 3px;*/
    /*width: 110px;*/
    /*text-align: center;*/
    /*}*/

    .top-bar .nav li .action-wrap {
        margin-top: 3px;
        margin-bottom: 0;
        width: 110px;
        text-align: center;
    }

    .top-bar .nav li .favor-selector-wrap {
        margin-top: 0px;
        margin-bottom: 0;
        width: 110px;
        text-align: center;
        display: none;
    }

    .top-bar .favor-confirm-setting {
        display: none;
    }

    .top-bar.setting-favor .nav li .favor-selector-wrap {
        margin-top: 0px;
        margin-bottom: 0;
        width: 110px;
        text-align: center;
        display: block;
    }

    .top-bar.setting-favor .favor-confirm-setting {
        display: block;
    }

    .top-bar .nav li a {
        font-size: 12px;
        font-family: "宋体";
    }

    .top-bar .nav li a:hover {
        color: #fff;
    }

    .top-bar.setting-favor .nav input {
        margin-top: 5px;
    }

    .top-bar .right-actions {
        margin-right: 176px;
        margin-top: 21px;
    }

    .height20 {
        width: 100%;
        height: 20px;
        clear: both;
    }
</style>

<div id="top-bar" class="top-bar">
    <div class="logo1 fl">
        <img src="<c:url value="/resources/common/project/images/logo.png" />" width="80" height="52"/>
    </div>
    <div class="logo2 fl"><img src="<c:url value="/resources/common/project/images/menutype.png" />" width="71" height="35"/></div>
    <div class="nav fl">
        <ul>
            <li><img src="<c:url value="/resources/common/project/images/menu5.png" />" width="35" height="28"/>
                <p class="action-wrap"><a href="#">见客下单</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
            <li><img src="<c:url value="/resources/common/project/images/menu1.png" />" width="27" height="28"/>
                <p class="action-wrap"><a href="<c:url value="/product/list"/>">产品库</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
            <li><img src="<c:url value="/resources/common/project/images/menu3.png" />" width="28" height="28"/>
                <p class="action-wrap"><a href="<c:url value="/factory/list"/>">工厂管理</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
            <li><img src="<c:url value="/resources/common/project/images/menu2.png" />" width="38" height="28"/>
                <p class="action-wrap"><a href="#">订单管理</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
            <li><img src="<c:url value="/resources/common/project/images/menu7.png" />" width="36" height="28"/>
                <p class="action-wrap"><a href="#">装柜操作</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
            <li><img src="<c:url value="/resources/common/project/images/menu6.png" />" width="21" height="28"/>
                <p class="action-wrap"><a href="#">应付货款</a></p><p class="favor-selector-wrap"><input name="" type="checkbox" value=""/></p></li>
        </ul>
    </div>
    <div class="fl favor-confirm-setting" style="margin-top:40px;"><a href="#"><img src="<c:url value="/resources/common/project/images/tj.png" />" width="36" height="40" alt="提交"/></a></div>
    <div class="right-actions fr">
        <img src="<c:url value="/resources/common/project/images/menut.png" />" width="196" height="33" usemap="#Map" border="0"/>
        <map name="Map" id="Map">
            <area shape="rect" coords="7,-4,91,13" href="22.html" alt="修改密码"/>
            <area shape="rect" coords="123,-4,196,17" href="#" alt="操作设置"/>
            <area shape="rect" coords="-4,16,98,34" href="javascript:void(0);" id="set-favor" alt="设置当前页"/>
            <area shape="rect" coords="121,18,196,32" href="#" alt="关于解说"/>
        </map>
    </div>
</div>