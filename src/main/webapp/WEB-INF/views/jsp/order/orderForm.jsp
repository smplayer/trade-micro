<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    //    String path = request.getContextPath();
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>定货单</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/project/css/style.css"/>"/>
<style type="text/css">
    .main {
        width: 733px;
        height: 1130px;
        margin: 0 auto;
    }

    .toptt {
        letter-spacing: 24px;
    }

    .toplw {
        width: 154px;
        margin-top: 5px;
    }

    .topw {
        width: 620px;
        padding-top: 35px;
    }

    .top div {
        margin-left: 24px;
    }

    .top3 {
        width: 620px;
        height: 20px;
        line-height: 20px;
        margin: 29px auto 5px auto;
    }

    .topfl {
        margin-left: 40px;
        width: 240px;
        text-align: left;
    }

    .topr2 {
        text-align: right;
        margin-right: 40px;
    }

    .ipt1 {
        width: 120px;
        height: 20px;
        line-height: 20px;
        border: 1px solid #000;
    }

    .ipt2 {
        width: 80px;
        height: 20px;
        line-height: 20px;
        border: 1px solid #000;
    }

    .ipt3 {
        width: 48px;
        height: 18px;
        line-height: 18px;
        border: 1px solid #000;
    }

    .ipt4 {
        width: 48px;
        height: 18px;
        line-height: 18px;
    }

    .ipt5 {
        width: 140px;
        height: 18px;
        line-height: 18px;
    }

    .ipt6 {
        width: 100px;
        height: 18px;
        line-height: 18px;
    }

    .tdbr {
        word-break: break-all;
        word-wrap: break-word; /*支持IE，chrome，FF*/
        text-align: center;
        line-height: 26px;
        padding: 2px 0;
    }

    .td2 {
        text-align: left;
        line-height: 26px;
        height: 26px;
        padding: 2px 0 2px 7px;
    }

    .td70 {
        height: 127px;
        padding: 9px;
        line-height: 21px;
    }

    .td65 {
        height: 65px;
        padding: 2px 0 2px 7px;
        text-align: left;
    }

    .tddiv {
        width: 245px;
        height: 105px;
        margin: 0 auto;
        text-align: left;
    }

    .bdrb {
        border-bottom: 1px solid #000;
        text-align: left;
    }

    .tefr {
        float: right;
        width: 300px;
    }
</style>
</head>

<body>
<div class="main border">
    <form id="form1" name="form1" method="post" action="">
        <div class="top topw">
            <div class="toptt">定货单</div>
        </div>
        <div class="topline toplw"></div>
        <div class="top3">
            <div class="topfl fl">定货日期 <input type="text" name=""  value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />" class="ipt1"/>
            </div>
            <div class="fr topr2">单号 <input type="text" name="num" id="num" class="ipt2"/>
            </div>
        </div>
        <table width="630" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#000000">
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">敝公司（简称买方）于当天向贵公司/厂（简称卖方）定购下列产品，相关条款如下：</td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">1：产品（单位/元）</td>
            </tr>

            <tr>
                <td class="tdbr td90" bgcolor="#FFFFFF">品名</td>
                <td class="tdbr td100" bgcolor="#FFFFFF">公司货号</td>
                <td class="tdbr td75" bgcolor="#FFFFFF">工厂货号</td>
                <td class="tdbr td60" bgcolor="#FFFFFF">包装</td>
                <td class="tdbr td50" bgcolor="#FFFFFF">单价</td>
                <td class="tdbr td50" bgcolor="#FFFFFF">功能</td>
                <td class="tdbr td49" bgcolor="#FFFFFF">箱数</td>
                <td class="tdbr td50" bgcolor="#FFFFFF">总数量</td>
                <td class="tdbr td75" bgcolor="#FFFFFF">总金额</td>
            </tr>

            <c:forEach items="${page.dataList}" var="item" varStatus="status">
            <tr>
                <td class="tdbr" bgcolor="#FFFFFF">${item.companyProductName}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.companyProductNo}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.factoryProductNo}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.packageForm}</td>
                <td class="tdbr" bgcolor="#FFFFFF"><fmt:formatNumber value="${item.factoryPrice}" maxFractionDigits="1" pattern="#.#" /></td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.functionDescription}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.orderedCartonQuantity}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${item.orderedProductQuantity}</td>
                <td class="tdbr" bgcolor="#FFFFFF"><fmt:formatNumber value="${item.payment}" maxFractionDigits="0" pattern="#" /></td>
            </tr>
            </c:forEach>
            <c:forEach begin="${fn:length(page.dataList)}" end="5" step="1">
            <tr>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
            </tr>
            </c:forEach>
            <tr>
                <td class="tdbr" bgcolor="#FFFFFF">合计：</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">&nbsp;</td>
                <td class="tdbr" bgcolor="#FFFFFF">${sum.cartonQuantity}</td>
                <td class="tdbr" bgcolor="#FFFFFF">${sum.productQuantity}</td>
                <td class="tdbr" bgcolor="#FFFFFF"><fmt:formatNumber value="${sum.payment}" maxFractionDigits="0" pattern="#" /></td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">2：包装：纸箱包装必须坚固，能够在出口运输的过程中对产品进行保护。</td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">3：卖方必须保证在
                    <input name="textfield2" type="text" class="ipt3" id="textfield2"/>
                    月
                    <input name="textfield3" type="text" class="ipt3" id="textfield3"/>
                    日之前生产完毕，并等待通知交货。
                </td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">4：买方必须在收货之后
                    <input name="textfield4" type="text" class="ipt3" id="textfield4"/>
                    内付款。
                </td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">5：唛头：</td>
            </tr>
            <tr>
                <td colspan="4" align="center" valign="top" bgcolor="#FFFFFF" class="td70">
                    <input name="textfield5" type="text" class="ipt3" id="textfield5"/>
                    正唛
                    <div class="tddiv">

                    </div>
                </td>
                <td colspan="5" align="center" valign="middle" bgcolor="#FFFFFF" class="td70">
                    <input name="textfield6" type="text" class="ipt3" />
                    侧唛<br/>
                    <div class="tddiv">
                        ITEM NO:<input name="textfield6" type="text" class="ipt5"  /><br/>
                        N.W.: <input name="textfield6" type="text" class="ipt4" value="<fmt:formatNumber value="${sum.netWeight}" maxFractionDigits="0" pattern="#" />" />KG<br/>
                        G.W.: <input name="textfield6" type="text" class="ipt4" value="<fmt:formatNumber value="${sum.grossWeight}" maxFractionDigits="0" pattern="#" />" />KG<br/>
                        MEAS: <input name="textfield6" type="text" class="ipt5" />CM<br/>
                        Q'TY: <input name="textfield6" type="text" class="ipt5" value="<fmt:formatNumber value="${sum.productQuantity}" maxFractionDigits="0" pattern="#" />" /></div>
                </td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">6：卖方必须提供
                    <input name="textfield7" type="text" class="ipt3" id="textfield7"/>
                    个免费样品以供确认，交货时以确认样品为准进行验收。
                </td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td65">
                    7：卖方必须保证产品大货的质量与确认样品相符合，如功能、产品尺寸、产品重量、颜色、贴纸、装量、唛头等等。否则，除了买方有权拒绝收货之外，若因此而致使买方不欺遭受客户减价、退货，其损失应由卖方全部承担。
                </td>
            </tr>
            <tr>
                <td colspan="9" bgcolor="#FFFFFF" class="td2">8：本单一式两份，经卖方阅读、充分理解、接受并签章生效，由双方各执一份为凭。</td>
            </tr>
        </table>
        <table width="630" border="0" align="center" cellpadding="2" cellspacing="0">
            <tr>
                <td width="220" height="31" valign="bottom" class="bdrb">&nbsp;买方：<input name="textfield6" type="text"
                                                                                         class="ipt6" />
                </td>
                <td width="230" valign="bottom" class="bdrb">手机/电话：<input name="" type="text" class="ipt6"
                                                                          /></td>
                <td width="170" valign="bottom" class="bdrb">传真：
                    <input name="textfield8" type="text" class="ipt6" id="textfield8"/></td>
            </tr>
            <tr>
                <td height="31" colspan="3" valign="bottom" class="bdrb">&nbsp;代表人（签章及日期）：<input name=""
                                                                                                 type="text"
                                                                                                 class="ipt6"
                                                                                                 />
                    <div class="tefr">收货人（签章及日期）：
                        <input name="textfield11" type="text" class="ipt6" id="textfield11"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td height="31" valign="bottom" class="bdrb">&nbsp;买方：<input name="" type="text" class="ipt6"
                                                                             /></td>
                <td valign="bottom" class="bdrb">手机/电话：<input name="" type="text" class="ipt6"
                                                              /></td>
                <td valign="bottom" class="bdrb">传真：
                    <input name="textfield9" type="text" class="ipt6" id="textfield9"/></td>
            </tr>
            <tr>
                <td height="31" colspan="3" valign="bottom" class="bdrb">&nbsp;代表人（签章及日期）：<input name=""
                                                                                                 type="text"
                                                                                                 class="ipt6"
                                                                                                 />
                    <div class="tefr">收货人（签章及日期）：
                        <input name="textfield10" type="text" class="ipt6" id="textfield10"/>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>



<c:import url="/WEB-INF/views/jsp/common/dialog-alert.jsp"></c:import>
<script>

</script>




</body>
</html>
