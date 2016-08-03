<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>见客档案</title>
    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <style type="text/css">
        body {
            background: url(<c:url value="/resources/quotation/images/bgjk.png" />) #EFE9C7;
        }

        .top2w {
            width: 1180px;
            margin: 5px auto;
        }

        .topr2 {
            margin-right: 70px;
        }

        .tdbg {
            background: #F1F5DA;
        }

        .toptdbg {
            background: #f0e0c9;
            text-align: center;
        }

        .main {
            /*width: 1420px;*/
            /*height: 800px;*/
            /*height: 765px;*/
            /*border: 1px solid #000;*/
            /*margin: 0 auto;*/
            margin-top: 45px;
        }

        .btn {
            height: 25px;
            width: 46px;
            cursor: pointer;
        }
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">



    <c:import url="/WEB-INF/views/jsp/common/top-bar.jsp">
        <c:param name="backgroundColor" value="#B47BFE"/>
        <c:param name="currentModule" value="quotation"/>
        <c:param name="currentSubModule" value="archive"/>
        <c:param name="title" value="档案"/>
    </c:import>





    <div class="main">
        <div class="top2 top2w" style="overflow: hidden">
            <div class="fr topr2" style="margin-right: 70px;">
                <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                    <c:param name="prePageImage" value="/resources/quotation/images/left-arc.png"  />
                    <c:param name="nextPageImage" value="/resources/quotation/images/right-arc.png"  />
                    <c:param name="pageIndex" value="${page.pageIndex}"  />
                    <c:param name="pageQuantity" value="${page.pageQuantity}"  />
                    <c:param name="url" value="/quotation/archiveList"/>
                </c:import>
            </div>
        </div>
        <table id="data-table" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333">
            <tr>
                <td height="40" class="td39 toptdbg">序号</td>
                <td class="td100 toptdbg">存档日期</td>
                <td class="td150 toptdbg">客 户 名</td>
                <td class="td100 toptdbg">贸易条款</td>
                <td class="td80 toptdbg">装运港</td>
                <td class="td60 toptdbg">币种</td>
                <td class="td90 toptdbg">柜 型</td>
                <td class="td90 toptdbg">货柜数量</td>
                <td class="td90 toptdbg">总箱数</td>
                <td class="td90 toptdbg">总体积</td>
                <td class="td90 toptdbg">总金额</td>
                <td class="td80 toptdbg">档案</td>
                <td class="td80 toptdbg">历史档案</td>
                <td class="td40 toptdbg">
                    <a href="javascript: void(0);" id="select-all">全选</a>
                </td>
            </tr>






            <c:forEach items="${page.dataList}" var="a" varStatus="status">
                <tr>
                    <td height="24" class="break tdbg">
                            ${(page.dataQuantity - (page.pageIndex - 1) * page.pageSize) - status.index}
                    </td>
                    <td class="break tdbg">
                        <fmt:formatDate value="${a.archivedDate}" pattern="MM-dd"/>
                    </td>
                    <td class="break tdbg">${a.customerName}</td>
                    <td class="break tdbg">${a.tradeClauseType}${a.tradeClause}</td>
                    <td class="break tdbg">${a.shipmentPort}</td>
                    <td class="break tdbg">${a.currency}</td>
                    <td class="break tdbg">${a.containerType == '1' ? '20尺' : a.containerType == '2' ? '40尺' : a.containerType == '3' ? "40尺加高" : ''}</td>
                    <td class="break tdbg">
                        <fmt:formatNumber value="${totalValueMap[a.id].containerQuantity}" maxFractionDigits="1" pattern="#.#" />
                    </td>
                    <td class="break tdbg">
                        ${totalValueMap[a.id].cartonQuantity}
                    </td>
                    <td class="break tdbg">
                        <fmt:formatNumber value="${totalValueMap[a.id].volume}" maxFractionDigits="0" pattern="#" />

                    </td>
                    <td class="break tdbg">
                        <fmt:formatNumber value="${totalValueMap[a.id].amount}" maxFractionDigits="0" pattern="#" />
                    </td>
                    <td class="break tdbg">
                        <a class="" id="${a.id}" href="<c:url value="/quotation/reloadFromArchive?id=${a.id}" />">调阅</a>
                    </td>
                    <td class="break tdbg">
                        ${a.generatedOrder == true ? '是' : ''}
                    </td>
                    <td class="break tdbg">
                        <input type="checkbox" id="${a.id}" name="id" value="${a.id}" class="${a.generatedOrder ? 'generatedOrder' : ''}" />
                    </td>
                </tr>
            </c:forEach>

            <c:forEach begin="${fn:length(page.dataList)}" end="14" step="1">
                <tr>
                    <td height="26" class="break tdbg">&nbsp;</td>
                    <td class="break tdbg">&nbsp;</td>
                    <td class="break tdbg">&nbsp;</td>
                    <td class="break tdbg">&nbsp;</td>
                    <td class="break tdbg">&nbsp;</td>
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
        <div style="width: 1180px; overflow: hidden; margin: 35px auto 0 auto;">
            <div style="float: right; margin-right: 70px">
                <input type="image" src="<c:url value="/resources/quotation/images/delg.png" />" class="btn" id="del"/>
            </div>
            <div style="float: right;">
                <a href="javascript:void(0);" id="copyFromArchive" style="line-height: 25px; margin-right: 60px;" >复制</a>
            </div>
        </div>
    </div>
</form>


<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>

<script>

function selectAll() {
    var $ids = $("[type=checkbox][name=id]");
    var $checked = $("[name=id]:checked");
    var status;
    if($ids.length > $checked.length && $checked.length >= 0) {
        status = 0;
    } else if ($ids.length == $checked.length) {
        status = 1;
    }
    if( status == 0) {
        $ids.each(function (e) {
            this.checked = true;
        });
    } else {
        $ids.each(function (e) {
            this.checked = false;
        });
    }
}

$(function () {
    $("#select-all").click(selectAll);

    $(".reload-from-archive").click(function (e) {
        e.preventDefault();
        window.parent.location.href = ctx + '/quotation/reloadFromArchive?id=' + $(this).attr("id");
    })
    $("#copyFromArchive").click(function (e) {
        e.preventDefault();
        var checked = $("#data-table input[name=id]:checked");
        if (checked.length == 1) {
            if (!checked.hasClass('generatedOrder')) {
                dialogAlert("#dialog-alert", {
                    textContent: '很抱歉，只有历史档案才能复制'
                });
            } else {
                var id = checked.val();
                window.location.href = ctx + '/quotation/copyFromArchive?id=' + id;
            }
        }
    })

    $("#del").click(function (e) {
        e.preventDefault();

        var checked = $("#data-table input[name=id]:checked");
        var ids = [];
        checked.each(function () {
            ids.push($(this).val());
        });
        if (ids.length != 0) {
            $.ajax({
                type: 'POST',
                url: ctx + "/quotation/deleteArchive",
                data: JSON.stringify({
                    "ids": ids
                }),
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    window.location.reload();
                },
                error: function (xhr, type) {
                    //alert('数据加载失败' + type);
                }
            });
        } else {
            window.location.reload();
        }

    })

})
</script>


</body>
</html>
