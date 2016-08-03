<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>更多</title>
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
            margin: 60px auto 0 auto;
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

        input[name=orderId] {
            margin-top: 15px;
        }
    </style>
</head>

<body>
<form id="form" name="form" method="post" action="<c:url value="/container/favor/setting"/>">
    <div class="main border">
        <div class="top topw">
            <div class="toptt">更多</div>
        </div>
        <div class="topline toplw"></div>
        <table id="table1" width="500" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="" style="background-color: #fff">
            <tr class="header-line">
                <td width="76" height="30" align="center" valign="middle" class="favor-container"></td>
                <td width="21" align="center" valign="middle" bgcolor="">
                    <div>1</div>
                    <input type="checkbox" name="indexNumber" id="checkbox" value="1"/></td>
                <td width="77" align="center" valign="middle" class="favor-container high-light"></td>
                <td width="21" align="center" valign="middle" class="high-light">
                    <div>2</div>
                    <input type="checkbox" name="indexNumber" id="checkbox20" value="2"/></td>
                <td width="77" align="center" valign="middle" class="favor-container"></td>
                <td width="21" align="center" valign="middle" bgcolor="">
                    <div>3</div>
                    <input type="checkbox" name="indexNumber" id="checkbox21" value="3"/></td>
                <td width="77" align="center" valign="middle" class="favor-container high-light"></td>
                <td width="21" align="center" valign="middle" class="high-light">
                    <div>4</div>
                    <input type="checkbox" name="indexNumber" id="checkbox22" value="4"/></td>
                <td width="77" align="center" valign="middle" class="favor-container"></td>
                <td width="21" align="center" valign="middle">
                    <div>5</div>
                    <input type="checkbox" name="indexNumber" id="checkbox23" value="5"/></td>
            </tr>
            <tr class="header-line">
                <td height="30" align="center" valign="middle" class="favor-container"></td>
                <td align="center" valign="middle">
                    <div>6</div>
                    <input type="checkbox" name="indexNumber" id="checkbox2" value="6"/></td>
                <td align="center" valign="middle" class="favor-container high-light"></td>
                <td align="center" valign="middle" class="high-light">
                    <div>7</div>
                    <input type="checkbox" name="indexNumber" id="checkbox19" value="7"/></td>
                <td align="center" valign="middle" class="favor-container"></td>
                <td align="center" valign="middle">
                    <div>8</div>
                    <input type="checkbox" name="indexNumber" id="checkbox46" value="8"/></td>
                <td align="center" valign="middle" class="favor-container high-light"></td>
                <td align="center" valign="middle" class="high-light">
                    <div>9</div>
                    <input type="checkbox" name="indexNumber" id="checkbox33" value="9"/></td>
                <td align="center" valign="middle" class="favor-container"></td>
                <td align="center" valign="middle">
                    <div>10</div>
                    <input type="checkbox" name="indexNumber" id="checkbox24" value="10"/></td>
            </tr>

            <c:forEach var="f" items="${list}" varStatus="status">
                <c:if test="${status.index == 0}" var="firstLine">
                    <tr>
                </c:if>

                <c:if test="${status.index % 5 == 1 || status.index % 5 == 3}" var="highLight">
                    <td height="30" align="center" valign="middle" class="high-light">${f.containerNo}</td>
                    <td align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="favorId" value="${f.id}" id="${f.id}"/>
                    </td>
                </c:if>

                <c:if test="${not highLight}">
                    <td height="30" align="center" valign="middle">${f.containerNo}</td>
                    <td align="center" valign="middle">
                        <input type="checkbox" name="favorId" value="${f.id}" id="${f.id}"/>
                    </td>
                </c:if>

                <c:if test="${(status.index + 1) == fn:length(list)}" var="lastLine">
                    <c:if test="${fn:length(list) % 5 > 0}">
                        <c:forEach begin="${fn:length(list) % 5}" end="4" var="i" varStatus="status3" >

                            <c:if test="${i % 5 == 1 || i % 5 == 3}" var="highLight2">
                                <td height="30" align="center" valign="middle" class="high-light"></td>
                                <td align="center" valign="middle" class="high-light">
                                    <input type="checkbox" name="favorId" disabled/>
                                </td>
                            </c:if>
                            <c:if test="${not highLight2}">
                                <td height="30" align="center" valign="middle"></td>
                                <td align="center" valign="middle">
                                    <input type="checkbox" name="favorId" disabled/>
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
            <c:forEach begin="${fn:length(list) / 5}" end="${fn:length(list) % 5 > 0 ? 6 : 7}" step="1" varStatus="status">
                <tr>
                    <td height="30" align="center" valign="middle" >&nbsp;</td>
                    <td height="30" align="center" valign="middle" >
                        <input type="checkbox" name="favorId" disabled />
                    </td>

                    <td height="30" align="center" valign="middle" class="high-light">&nbsp;</td>
                    <td height="30" align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="favorId" disabled />
                    </td>

                    <td height="30" align="center" valign="middle">&nbsp;</td>
                    <td height="30" align="center" valign="middle">
                        <input type="checkbox" name="favorId" disabled />
                    </td>

                    <td height="30" align="center" valign="middle" class="high-light">&nbsp;</td>
                    <td height="30" align="center" valign="middle" class="high-light">
                        <input type="checkbox" name="favorId" disabled />
                    </td>

                    <td height="30" align="center" valign="middle">&nbsp;</td>
                    <td height="30" align="center" valign="middle">
                        <input type="checkbox" name="favorId" disabled />
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
            url: ctx + "/container/favor/list",
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var $favorList = $(".favor-container");
                $(data).each(function (i,n) {
                    $($favorList[n.indexNumber - 1]).text(n.containerNo);
                });
            },
            error: function (xhr, type) {
//                alert('数据加载失败' + type);
            }
        });
    }

    $(function () {

        $("#confirm").click(function (e) {
            var $indexNumbers = $("[name=indexNumber]:checked");
            var $favorIds = $("[name=favorId]:checked");
            if (!($indexNumbers.length == 0 && $favorIds.length == 0) && !($indexNumbers.length == 1 && $favorIds.length == 1)) {
                if ($indexNumbers.length > 1 || $favorIds.length > 1) {
                    dialogAlert("#dialog-alert", {
                        textContent: '抱歉, 只能选择一个'
                    });
                } else if ($indexNumbers.length == 0 || $favorIds.length == 0) {
                    dialogAlert("#dialog-alert", {
                        textContent: '请匹配相应客户名'
                    });
                }
                return false;
            }
            $("#form").submit();
        });

        $("#del").click(function (e) {
            $("#form").attr("action", ctx + "/container/favor/delete").submit();
        })

        initFavorList();
    });
</script>
</body>
</html>
