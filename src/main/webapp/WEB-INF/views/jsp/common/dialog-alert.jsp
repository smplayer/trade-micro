<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
    #dialog-alert {
        display: none;
        background: #fff url("<c:url value="/resources/common/project/images/dialog-alert-bg.jpg"/>") no-repeat;
        width: 250px;
        /*height: 220px;*/
        /*border: 3px solid #006DB8;*/
    }

    .dialog-header {
        text-align: right;
    }

    .dialog-close img {
        width: 24px;
        height: 24px;
        margin: 10px 10px 0px 0px
    }

    .dialog-main {
        height: 216px;
    }

    .dialog-main table {
        height: 182px;
        width: 100%;
    }

    .dialog-main table td {
        vertical-align: middle;

    }

    .dialog-alert-text-content {
        font-size: 18px;
        font-weight: normal;
        padding: 0 30px;
        line-height: 2em;
        text-align: center;
    }
</style>

<div id="dialog-alert">
    <div class="dialog-header">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>

    <div class="dialog-main">
        <table>
            <tr>
                <td class="dialog-alert-text-content"></td>
            </tr>
        </table>
    </div>
</div>