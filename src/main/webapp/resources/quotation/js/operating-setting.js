/**
 * Created by yrx on 2016/5/4.
 */
/**
 * Created by Administrator on 16-3-28.
 */
function checkCustomerName() {
    var customerName = $("#customerName").val();
    if ($.trim(customerName) == "") {
        dialogAlert("#dialog-alert", {
            textContent: '请输入客户名',
            onClose: function () {
                $("#customerName").focus();
            }
        });
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
        dialogAlert("#dialog-alert", {
            textContent: '请选择贸易条款并输入港口名',
            onClose: function () {
                $("#FOB").focus();
            }
        });
        return false;
    }

    if (inputtedCount > 1) {
        dialogAlert("#dialog-alert", {
            textContent: '只选择一项',
            onClose: function () {
                $("#FOB").focus();
            }
        });
        return false;
    }

    return true;
}

function checkContainerType() {
    var checked = $("[name=containerType]:checked").length == 1;
    if (!checked) {
        dialogAlert("#dialog-alert", {
            textContent: '请选择柜型'
        });
        return false;
    }
    return true;
}

function checkProfit() {
    var profitPercent = $.trim($("#profitPercent").val());
    var profitAmount = $.trim($("#profitAmount").val());
    if (profitPercent == "" && profitAmount == "") {
        dialogAlert("#dialog-alert", {
            textContent: '请加上毛利润',
            onClose: function () {
                $("#profitPercent").focus();
            }
        });
        return false;
    }

    if (profitPercent != "") {
        if(!/^[1-9]+[0-9]*(\.[0-9])?$/.test(profitPercent) || Number(profitPercent) > 50) {
            dialogAlert("#dialog-alert", {
                textContent: '小数点后只保持1位<br/>加点不超过50%',
                onClose: function () {
                    $("#profitPercent").focus();
                }
            });
            return false;
        }
    }

    if (profitAmount != "") {
        if(!/^[1-9]+[0-9]*$/.test(profitAmount)) {
            dialogAlert("#dialog-alert", {
                textContent: '不能有小数',
                onClose: function () {
                    $("#profitAmount").focus();
                }
            });
            return false;
        }
    }


    if (profitPercent != "" && profitAmount != "") {
        dialogAlert("#dialog-alert", {
            textContent: '只选择一项',
            onClose: function () {
                $("#profitPercent").focus();
            }
        });
        return false;
    }
    return true;
}

function checkCustomsClearanceFee() {
    var customsClearanceFee = $("#customsClearanceFee").val();
    if ($.trim(customsClearanceFee) == "") {
        dialogAlert("#dialog-alert", {
            textContent: '请输入出口清关费用',
            onClose: function () {
                $("#customsClearanceFee").focus();
            }
        });
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
            dialogAlert("#dialog-alert", {
                textContent: '请输入汇率',
                onClose: function () {
                    $("#exchangeRate").focus();
                }
            });
            return false;
        } else {
            if (exchangeRate.lastIndexOf(".") != -1) {
                var l = exchangeRate.substring(exchangeRate.lastIndexOf(".")).length;
                if (l > 5) {
                    dialogAlert("#dialog-alert", {
                        textContent: '很抱歉，汇率小数点后设置不能超过4位',
                        onClose: function () {
                            $("#exchangeRate").focus();
                        }
                    });
                    return false;
                }
            }
        }
        if (decimalPlaces == "") {
            dialogAlert("#dialog-alert", {
                textContent: '请输入小数位',
                onClose: function () {
                    $("#decimalPlaces").focus();
                }
            });
            return false;
        } else if (Number(decimalPlaces) > 4) {
            dialogAlert("#dialog-alert", {
                textContent: '很抱歉，系统只接受小数点后4位',
                onClose: function () {
                    $("#decimalPlaces").focus();
                }
            });
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
            dialogAlert("#dialog-alert", {
                textContent: '本系统不支持小数点后5位或以上的设置',
                onClose: function () {
                    $("#decimalPlaces").focus();
                }
            });
        }
    }
    return true;
}

$(function () {
    $("#profitPercent").keyup(function (e) {
        if ($(this).val() != '') {
            $("#profitAmount").attr('disabled', 'disabled');
        } else {
            $("#profitAmount").removeAttr('disabled');
        }
    });
    $("#profitAmount").keyup(function (e) {
        if ($(this).val() != '') {
            $("#profitPercent").attr('disabled', 'disabled');
        } else {
            $("#profitPercent").removeAttr('disabled');
        }
    });

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