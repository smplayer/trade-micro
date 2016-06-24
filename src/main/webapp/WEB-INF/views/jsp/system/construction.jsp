<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<head>
    <meta charset="utf-8">
    <title>关于解说</title>
    <link rel="stylesheet" href="<c:url value="/resources/system/config1/css/global.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/system/config1/css/auto279.css"/>">
</head>
<body style="text-align: center;">

<div style="width: 1300px; margin: 0 auto">
    <img src="<c:url value="/resources/common/project/images/construction/about-construction.jpg"/>"/>
</div>


    <c:import url="/WEB-INF/views/jsp/common/common-script.jsp"></c:import>
    <script>

        $(function () {
            $("#confirm").click(function () {
                $("#form").submit();
            });
        })

    </script>

</body>
</html>