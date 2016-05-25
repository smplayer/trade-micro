/**
 * Created by yrx on 2016/5/4.
 */
/**
 * Created by Administrator on 16-3-28.
 */
function checkCustomerName() {
    var customerName = $("#customerName").val();
    if ($.trim(customerName) == "") {
        alert("请输入客户名");
        return false;
    }
    return true;
}
function checkClause() {
    var fob = $("#FOB").val();
    var cnf = $("#CNF").val();
    var gif = $("#GIF").val();

    var inputtedCount = 0;
    if ($.trim(fob) != "") {
        inputtedCount++;
    }
    if ($.trim(cnf) != "") {
        inputtedCount++;
    }
    if ($.trim(gif) != "") {
        inputtedCount++;
    }

    if (inputtedCount == 0) {
        alert("请选择贸易条款并输入港口名");
        $("#FOB").focus();
        return false;
    }

    if (inputtedCount > 1) {
        alert("只选择一项");
        $("#FOB").focus();
        return false;
    }

    return true;
}

function checkContainerType() {
    var checked = $("input[name=containerType]:checked").length == 1;
    if (!checked) {
        alert("请选择柜型");
        return false;
    }
    return true;
}

function checkProfit() {
    var profitPercent = $.trim($("#profitPercent").val());
    var profitAmount = $.trim($("#profitAmount").val());
    if (profitPercent == "" && profitAmount == "") {
        alert("请加上毛利润");
        $("#profitPercent").focus();
        return false;
    }
    if (profitPercent != "" && profitAmount != "") {
        alert("只选择一项");
        $("#profitPercent").focus();
        return false;
    }
    return true;
}

function checkCustomsClearanceFee() {
    var customsClearanceFee = $("#customsClearanceFee").val();
    if ($.trim(customsClearanceFee) == "") {
        alert("请输入出口清关费用");
        return false;
    }
    return true;
}

function checkCurrency() {
    var currency = $.trim($("#currency").val());
    if (currency != "") {
        var exchangeRate = $.trim($("#exchangeRate").val());
        var decimalPlaces = $.trim($("#decimalPlaces").val());
        if (exchangeRate == "") {
            alert("请输入汇率");
            $("#exchangeRate").focus();
            return false;
        }
        if (decimalPlaces == "") {
            alert("请输入小数位");
            $("#decimalPlaces").focus();
            return false;
        }
    } else {
        $("#currency").val("人民币");
        $("#exchangeRate").val("1");
        $("#decimalPlaces").val("2");
    }
    return true;
}

function checkDecimalPlaces() {
    var decimalPlaces = $.trim($("#decimalPlaces").val());
    if (decimalPlaces != "") {
        if (Number(decimalPlaces) > 5) {
            alert("本系统不支持小数点后5位或以上的设置");
        }
    }
    return true;
}

$(function () {
    $("#form").submit(function (e) {
            var checkedResult =
                checkCustomerName() && checkClause() && checkContainerType()
                && checkProfit() && checkCustomsClearanceFee() && checkCurrency();
            if (checkedResult) {
                return true;
            } else {
                return false;
            }
        }
    );
});