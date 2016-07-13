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
        .top2w {
            width: 1100px;
        }

        .topr2 {
            margin-right: 70px;
        }

        .tdbg {
            background: #F1F5DA;
        }

        .toptdbg {
            background: #D9D9D9;
            text-align: center;
        }

        .main {
            width: 1262px;
            /*height: 765px;*/
            border: 1px solid #000;
            margin: 0 auto;
            background: #EBEAE8;
            margin-top: 65px;
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
    <div class="main">
        <div class="top2 top2w" style="overflow: hidden">
            <div class="fr topr2" style="margin-right: 50px;">
                <c:import url="/WEB-INF/views/jsp/common/paging.jsp">
                    <c:param name="prePageImage" value="/resources/common/project/images/leftg.png"  />
                    <c:param name="nextPageImage" value="/resources/common/project/images/rightg.png"  />
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
                        <a class="reload-from-archive" id="${a.id}" href="javascript:void(0);">调阅</a>
                    </td>
                    <td class="break tdbg">
                        <input type="checkbox" id="${a.id}" name="id" value="${a.id}" />
                    </td>
                </tr>
            </c:forEach>

            <c:forEach begin="${fn:length(page.dataList)}" end="12" step="1">
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
                </tr>
            </c:forEach>





        </table>
        <table width="1100" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" colspan="3" align="left" valign="middle">&nbsp;</td>
            </tr>
            <tr>
                <td width="950" height="20" align="right" valign="top">
                    <a href="javascript:void(0);" id="copyFromArchive" >复制</a>
                </td>
                <td width="70" align="right" valign="top"><input type="image" src="<c:url value="/resources/quotation/images/delg.png" />" class="btn" id="del"/></td>
                <td width="80" align="right" valign="top">&nbsp;</td>
            </tr>
        </table>
    </div>
</form>




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
            var id = checked.val();
            window.parent.location.href = ctx + '/quotation/copyFromArchive?id=' + id;
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
