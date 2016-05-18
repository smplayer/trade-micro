<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .topw {
        width: 620px;
        padding-top: 41px;
    }

    .toptt {
        letter-spacing: 23px;
    }

    .top div {
        margin-left: 23px;
    }

    .top span {
        margin-right: 23px;
    }

    .toplw {
        width: 230px;
    }

    .menutop {
        margin-bottom: 10px;
    }

    .menu a.active{
        color: red;
    }
</style>


<div class="top topw">
    <div class="toptt">${param.title}</div>
</div>
<div class="topline toplw"></div>
<div class="menu menutop">
    <a href="<c:url value="/quotation/operating"/>" class="${param.curPage == "operating" ? "active" : ""}">操作版</a>
    <a href="<c:url value="/quotation/confirming/order?id=${quotation.id}"/>" target="_blank" class="${param.curPage == "confirm" ? "active" : ""}">确认版</a>

    <a href="<c:url value="/quotation/saveToArchive?id=${quotation.id}"/>" style="margin: 20px 0 0 0" class="${param.curPage == "saveAsArc" ? "active" : ""}">存档</a>/<a
        href="<c:url value="/quotation/arc"/>" style="margin: 0 20px 0 0" class="${param.curPage == "arc" ? "active" : ""}">档案</a>

    <a href="<c:url value="/quotation/operating/setting?id=${quotation.id}"/>" class="${param.curPage == "setting" ? "active" : ""}" style="margin-left: 0">返回</a>
</div>