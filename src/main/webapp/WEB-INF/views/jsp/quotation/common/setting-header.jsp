<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>

    .top-title {
        width: 620px;
        letter-spacing: 23px;
        margin: 0 auto;
        margin-top: 40px;
        height: 22px;
        font-weight: 500;
        font-size: 22px;
        line-height: 22px;
        font-family: "Microsoft Yahei";
        text-align: center;
        padding-left: 35px;
    }

    .top-hr {
        width: 290px;
        height: 5px;
        border-bottom: 1px red solid;
        margin: 0 auto;
    }

    .top span {
        padding-left: 35px;
    }

    .nav {
        width: 620px;
        text-align: center;
        margin: 1px auto;
    }

    .nav a {
        margin: 0 7px;
        font-size: 13px;
        font-family: "微软雅黑", "Microsoft YaHei", Verdana, Arial, "宋体", Helvetica, sans-serif;
        color: #000;
    }
</style>

<div class="top-title"><span>${param.title}</span></div>
<div class="top-hr"></div>
<c:if test="${(empty param.hideNav) || param.hideNav != true}" var="showNav">
    <div class="nav">
        <a href="<c:url value="/quotation/operating"/>" class="${param.curPage == "operating" ? "active" : ""}">操作版</a>
        <a href="<c:url value="/quotation/confirm"/>" target="_blank" class="${param.curPage == "confirm" ? "active" : ""}">确认版</a>
        <a href="<c:url value="/quotation/saveAsArc"/>" style="margin: 0" class="${param.curPage == "saveAsArc" ? "active" : ""}">存档</a>/<a
            href="<c:url value="/quotation/arc"/>" style="margin: 0" class="${param.curPage == "arc" ? "active" : ""}">档案</a>
        <a href="<c:url value="/quotation/operating/setting"/>" class="${param.curPage == "setting" ? "active" : ""}" style="margin-left: 0">返回</a>
    </div>
</c:if>