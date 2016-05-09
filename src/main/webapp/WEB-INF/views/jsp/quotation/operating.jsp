<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>报价表操作</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/quotation/css/quotation.css"/>"/>
    <style type="text/css">

        html, body {
            height: 100%;
            overflow: hidden;
        }

        #container{
            margin: 0 auto;
            width: 982px;
            height: 100%;
            overflow: hidden;
            border-left: 1px solid #000;
            border-right: 1px solid #000;
            background: url(<c:url value="/resources/common/project/images/bjt_46.png"/>) repeat #F0E9D1;
        }

        .main {
            width: 500px;
            height: 530px;
            margin: 20px auto;
            background: #F6D6FB;
            padding: 0 60px;
            border: 1px solid #333;
        }

        input {
            border: 1px solid #333;
            color: #333;
            background: #fff;
            height: 20px;
            line-height: 20px;
        }

        .input2 {
            border: 0;
            background: #F6D6FB;
            height: 20px;
            line-height: 20px;
            vertical-align: middle;
        }

        .btn {
            height: 23px;
            width: 42px;
            cursor: pointer;
            border: 0;
            background: none;
        }
    </style>
</head>

<body>

<div id="container">

    <c:import url="/WEB-INF/views/jsp/quotation/common/page-header.jsp" >
        <c:param name="curPage" value="quotation"/>
    </c:import>

    <div>operating</div>
</div>

<script type="text/javascript" src="<c:url value="/resources/common/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/quotation/js/operating-setting.js"/>"></script>
<script>

    $("#pre-reset").click(function(){
        $("input[type=text]", "#form").each(function () {
            $(this).val("");
        });
        $("input[type=radio]", "#form").each(function () {
            this.checked = false;
        });
    });

</script>

</body>
</html>
