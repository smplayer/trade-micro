<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
    #dialog-alert {
        display: none;
        background-color: #fff;
        width: 300px;
        /*height: 220px;*/
        border: 3px solid #006DB8;
    }

    .dialog-header {
        text-align: right;
    }

    .dialog-close img {
        width: 24px;
        height: 24px;
        margin: 10px 10px 0px 0px
    }

    .dialog-alert-main {
        height: 186px;
    }

    .dialog-alert-main table {
        height: 146px;
        width: 300px;
    }

    .dialog-alert-main table td {
        vertical-align: middle;
    }

    .dialog-alert-text-content {
        font-size: 18px;
    }
</style>

<div id="dialog-alert">
    <div class="dialog-header">
        <a href="javascript:void (0);" class="dialog-close">
            <img src="<c:url value="/resources/common/project/images/close.png" />"/>
        </a>
    </div>

    <div class="dialog-alert-main">
        <table>
            <tr>
                <td class="dialog-alert-text-content"></td>
            </tr>
        </table>
    </div>
</div>