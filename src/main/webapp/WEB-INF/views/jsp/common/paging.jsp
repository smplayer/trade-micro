<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table width="110" border="0" align="right" cellpadding="0" cellspacing="0">
    <tr>
        <td width="9">
            <c:if test="${param.pageIndex > 1}" var="notFirstPage">
                <a href="
                                    <c:url value="${param.url}">
                                        <c:param name="pageIndex" value="${param.pageIndex - 1}"/>
                                    </c:url>
                                ">
                    <img src="<c:url value="${param.prePageImage}" />"/>
                </a>
            </c:if>
            <c:if test="${not notFirstPage}">
                <img src="<c:url value="${param.prePageImage}" />"/>
            </c:if>
        </td>
        <td width="66" align="center">
            <a href="
                                <c:url value="${param.url}">
                                    <c:param name="pageIndex" value="1"/>
                                </c:url>
                             ">1</a>
            <span style="width: 10px; text-align: right; display: inline-block;">-</span>
            <input id="newPageIndex" type="text"
                   value="${empty param.pageIndex ? 0 : param.pageIndex}"
                   style="width: 20px; text-align: center; border: 0; background-color: transparent;"
            />/<span
                style="width: 20px; text-align: center; display: inline-block;"><a href="
                                <c:url value="${param.url}">
                                    <c:param name="pageIndex" value="${param.pageQuantity}"/>
                                </c:url>
                             ">${empty param.pageQuantity ? 0 : param.pageQuantity}</a></span>
        </td>
        <td width="9">
            <c:if test="${param.pageIndex < param.pageQuantity}" var="notLastPage">
                <a href="
                                    <c:url value="${param.url}">
                                        <c:param name="pageIndex" value="${param.pageIndex + 1}"/>
                                    </c:url>
                                ">
                    <img src="<c:url value="${param.nextPageImage}" />"/>
                </a>
            </c:if>
            <c:if test="${not notLastPage}">
                <img src="<c:url value="${param.nextPageImage}" />"/>
            </c:if>

        </td>
    </tr>
</table>

<script>
    $(function () {
        $("#newPageIndex").keyup(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                var url = ctx + '${param.url}';
                if (url.indexOf('?') == -1) {
                    url += '?';
                } else {
                    url += '&';
                }

                var pageIndex = $(this).val();
                if ($.trim(pageIndex) == '') {
                    pageIndex = 1;
                }
                url += 'pageIndex=' + pageIndex;

                document.location.href = url;
            }
        });
    })
</script>