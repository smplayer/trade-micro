<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>更多客户名</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <style type="text/css">
        .toptt {
            letter-spacing: 15px;
            color: #000;
        }

        .top div {
            margin-left: 15px;
        }

        .toplw {
            width: 200px;
            margin-bottom: 26px;
            margin-top: 3px;
        }

        .topw {
            width: 620px;
            padding-top: 34px;
        }

        .main {
            width: 680px;
            height: 481px;
            margin: 0 auto;
            background: #FFDEAD;
        }

        table div {
            color: red;
            font-size: 12px;
            margin-bottom: 1px;
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
            width: 680px;
            margin-top: 23px;
        }

        .btmbtn {
            margin-right: 120px;
            float: right;
            width: 70px;
            text-align: right;
        }

        .btm2 {
            float: right;
            width: 70px;
            text-align: right;
        }

        #table1 {
            border-right: 1px solid #000;
            border-top: 1px solid #000;
        }
        #table1 td {
            border-bottom: 1px solid #000;
            border-left: 1px solid #000;
        }

        #table1 .header-line td {
            border-bottom: 1px solid red;
        }

        #table1 .high-light{
            background-color: #DBFDF5;
        }

        input[name=quotationId] {
            margin-top: 15px;
        }
    </style>
</head>

<body>
<form id="form" name="form" method="post" action="<c:url value="/quotation/favor/setting"/>" >
    <div class="main border">
        <div class="top topw">
            <div class="toptt">更多客户名</div>
        </div>
        <div class="topline toplw"></div>
        <table id="table1" width="500" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="" style="background-color: #fff">
            <tr class="header-line">
                <td width="76" height="30" align="center" valign="middle" class="favor-cus-name"></td>
                <td width="21" align="center" valign="middle" bgcolor="">
                    <div>1</div>
                    <input type="checkbox" name="indexNumber" id="checkbox" value="1"/></td>
                <td width="77" align="center" valign="middle" class="favor-cus-name high-light"></td>
                <td width="21" align="center" valign="middle" class="high-light">
                    <div>2</div>
                    <input type="checkbox" name="indexNumber" id="checkbox20" value="2"/></td>
                <td width="77" align="center" valign="middle" class="favor-cus-name"></td>
                <td width="21" align="center" valign="middle" bgcolor="">
                    <div>3</div>
                    <input type="checkbox" name="indexNumber" id="checkbox21" value="3"/></td>
                <td width="77" align="center" valign="middle" class="favor-cus-name high-light"></td>
                <td width="21" align="center" valign="middle" class="high-light">
                    <div>4</div>
                    <input type="checkbox" name="indexNumber" id="checkbox22" value="4"/></td>
                <td width="77" align="center" valign="middle" class="favor-cus-name"></td>
                <td width="21" align="center" valign="middle">
                    <div>5</div>
                    <input type="checkbox" name="indexNumber" id="checkbox23" value="5"/></td>
            </tr>
            <tr class="header-line">
                <td height="30" align="center" valign="middle" class="favor-cus-name"></td>
                <td align="center" valign="middle">
                    <div>6</div>
                    <input type="checkbox" name="indexNumber" id="checkbox2" value="6"/></td>
                <td align="center" valign="middle" class="favor-cus-name high-light"></td>
                <td align="center" valign="middle" class="high-light">
                    <div>7</div>
                    <input type="checkbox" name="indexNumber" id="checkbox19" value="7"/></td>
                <td align="center" valign="middle" class="favor-cus-name"></td>
                <td align="center" valign="middle">
                    <div>8</div>
                    <input type="checkbox" name="indexNumber" id="checkbox46" value="8"/></td>
                <td align="center" valign="middle" class="favor-cus-name high-light"></td>
                <td align="center" valign="middle" class="high-light">
                    <div>9</div>
                    <input type="checkbox" name="indexNumber" id="checkbox33" value="9"/></td>
                <td align="center" valign="middle" class="favor-cus-name"></td>
                <td align="center" valign="middle">
                    <div>10</div>
                    <input type="checkbox" name="indexNumber" id="checkbox24" value="10"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td width="76" height="30" align="center" valign="middle" bgcolor="#FFE3D5" class="favor-cus-name">${not empty list[0] ? list[0].customerName : ""}</td>--%>
                <%--<td width="21" align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>1</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio" value="1"/></td>--%>
                <%--<td width="77" align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[1] ? list[1].customerName : ""}</td>--%>
                <%--<td width="21" align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>2</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio20" value="2"/></td>--%>
                <%--<td width="77" align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[2] ? list[2].customerName : ""}</td>--%>
                <%--<td width="21" align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>3</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio21" value="3"/></td>--%>
                <%--<td width="77" align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[3] ? list[3].customerName : ""}</td>--%>
                <%--<td width="21" align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>4</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio22" value="4"/></td>--%>
                <%--<td width="77" align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[4] ? list[4].customerName : ""}</td>--%>
                <%--<td width="21" align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>5</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio23" value="5"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td height="30" align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[5] ? list[5].customerName : ""}</td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>6</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio2" value="6"/></td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[6] ? list[6].customerName : ""}</td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>7</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio19" value="7"/></td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[7] ? list[7].customerName : ""}</td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>8</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio46" value="8"/></td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[8] ? list[8].customerName : ""}</td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>9</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio33" value="9"/></td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">${not empty list[9] ? list[9].customerName : ""}</td>--%>
                <%--<td align="center" valign="middle" bgcolor="#FFE3D5">--%>
                    <%--<div>10</div>--%>
                    <%--<input type="radio" name="indexNumber" id="radio24" value="10"/></td>--%>
            <%--</tr>--%>


            <c:forEach var="f" items="${quotationPage.dataList}" varStatus="status">
                <c:if test="${status.index == 0}" var="firstLine">
                    <tr>
                </c:if>

                <c:if test="${status.index % 5 == 1 || status.index % 5 == 3}" var="highLight">
                    <td height="30" align="center" valign="middle" class="high-light">${f.customerName}</td>
                    <td align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="quotationId" value="${f.id}" id="${f.id}"/>
                    </td>
                </c:if>

                <c:if test="${not highLight}">
                    <td height="30" align="center" valign="middle">${f.customerName}</td>
                    <td align="center" valign="middle">
                        <input type="checkbox" name="quotationId" value="${f.id}" id="${f.id}"/>
                    </td>
                </c:if>

                <c:if test="${(status.index + 1) == quotationPage.dataQuantity}" var="lastLine">
                    <c:if test="${quotationPage.dataQuantity % 5 > 0}">
                        <c:forEach begin="${quotationPage.dataQuantity % 5}" end="4" var="i" varStatus="status3" >

                            <c:if test="${i % 5 == 1 || i % 5 == 3}" var="highLight2">
                                <td height="30" align="center" valign="middle" class="high-light"></td>
                                <td align="center" valign="middle" class="high-light">
                                    <input type="checkbox" name="quotationId" disabled/>
                                </td>
                            </c:if>
                            <c:if test="${not highLight2}">
                                <td height="30" align="center" valign="middle"></td>
                                <td align="center" valign="middle">
                                    <input type="checkbox" name="quotationId" disabled/>
                                </td>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    </tr>
                </c:if>
                <c:if test="${not firstLine && not lastLine && ((status.index % 5) == 4)}">
                    </tr>
                    <tr>
                </c:if>
            </c:forEach>
            <c:forEach begin="${fn:length(quotationPage.dataList) / 5}" end="${fn:length(quotationPage.dataList) % 5 > 0 ? 6 : 7}" step="1" varStatus="status">
                <tr>
                    <td height="30" align="center" valign="middle" >&nbsp;</td>
                    <td height="30" align="center" valign="middle" >
                        <input type="checkbox" name="quotationId" disabled/>
                    </td>
                    <td height="30" align="center" valign="middle" class="high-light">&nbsp;</td>
                    <td height="30" align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="quotationId" disabled/>
                    </td>
                    <td height="30" align="center" valign="middle">&nbsp;</td>
                    <td height="30" align="center" valign="middle">
                        <input type="checkbox" name="quotationId" disabled/>
                    </td>
                    <td height="30" align="center" valign="middle" class="high-light">&nbsp;</td>
                    <td height="30" align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="quotationId" disabled/>
                    </td>
                    <td height="30" align="center" valign="middle">&nbsp;</td>
                    <td height="30" align="center" valign="middle">
                        <input type="checkbox" name="quotationId" disabled/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="bottom">
            <div class="btmbtn"><input id="confirm" type="image" src="<c:url value="/resources/common/project/images/tj-o.png"/>" class="btn"/></div>
            <div class="btm2"><a id="del"  href="javascript:void(0);" class="btn">清除</a></div>
            <%--<div class="fr" style="margin-right:22px;line-height:21px;">转移</div>--%>
        </div>
    </div>
</form>

<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>
    function initFavorList() {
        $.ajax({
            type: 'POST',
            url: ctx + "/quotation/favor/list",
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var $favorCusNameList = $(".favor-cus-name");
                $(data).each(function (i,n) {
                    $($favorCusNameList[n.indexNumber - 1]).text(n.customerName);
                });
            },
            error: function (xhr, type) {
//                alert('数据加载失败' + type);
            }
        });
    }

    $(function () {

        $("#confirm").click(function (e) {
//            if($("[name=indexNumber]:checked").length == 0 ){
//                alert("请选择序号");
//                return false;
//            }
//            if($("[name=quotationId]:checked").length == 0 ){
//                alert("请选择客户");
//                return false;
//            }
            var $indexNumbers = $("[name=indexNumber]:checked");
            var $quotationIds = $("[name=quotationId]:checked");
            if (!($indexNumbers.length == 0 && $quotationIds.length == 0) && !($indexNumbers.length == 1 && $quotationIds.length == 1)) {
                if ($indexNumbers.length > 1 || $quotationIds.length > 1) {
                    dialogAlert("#dialog-alert", {
                        textContent: '抱歉, 只能选择一个'
                    });
                } else if ($indexNumbers.length == 0 || $quotationIds.length == 0) {
                    dialogAlert("#dialog-alert", {
                        textContent: '请匹配相应客户名'
                    });
                }
                return false;
            }
            $("#form").submit();
        });

        $("#del").click(function (e) {
//            if($("[name=indexNumber]:checked").length == 0 ){
//                alert("请选择序号");
//                return false;
//            }
            $("#form").attr("action", ctx + "/quotation/favor/delete").submit();
//            window.parent.location.reload();
        })

        initFavorList();
    });
</script>
</body>
</html>
