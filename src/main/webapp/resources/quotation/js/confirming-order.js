/**
 * Created by yrx on 2016/5/22.
 */


function getAccumulativeTotal() {
    var accumulativeTotal = $.ajax({
        type: 'POST',
        url: ctx + "/quotation/accumulativeTotal",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "quotationId": quotationId,
            "pageQuantity": pageQuantity,
            "pageIndex": pageIndex,
            "pageSize": pageSize
        }),
        success: function (data) {
            $("#total-carton-quantity").text(data.cartonQuantity);
            $("#total-product-quantity").text(data.productQuantity);
            $("#total-volume").text(data.volume.toFixed(1));
            $("#total-amount").text(data.amount.toFixed(0));
            dialog("#dialog-accumulation");
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}

function initTotal() {
    var sumCartonQuantity = 0;
    $(".cartonQuantity").each(function () {
        sumCartonQuantity += Number($(this).text());
    });
    $("#sum-carton-quantity").text(sumCartonQuantity);

    var sumProductQuantity = 0;
    $(".orderedProductQuantity").each(function () {
        sumProductQuantity += Number($(this).text());
    });
    $("#sum-product-quantity").text(sumProductQuantity);

    var sumVolume = 0;
    $(".volume").each(function () {
        sumVolume += Number($(this).text());
    });
    $("#sum-volume").text(sumVolume.toFixed(1));


    var sumAmount = 0;
    $(".amount").each(function () {
        sumAmount += Number($(this).text());
    });
    $("#sum-amount").text(sumAmount.toFixed(0));
}

function generateOrderFromQuotation() {
    $.ajax({
        type: 'POST',
        url: ctx + "/order/generateOrderFromQuotation",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "quotationId": quotationId
        }),
        success: function (data) {
            // alert("成功");
            document.location.reload();
        },
        error: function (xhr, type) {
            // alert("失败");
        }
    });
}


function modifyProp(n) {
    var id = $(n).parents(".item").first().attr("id");
    var propName = $(n).attr("name");
    var propValue = $(n).val();
    $.ajax({
        type: 'POST',
        // async: false,
        url: ctx + "/ajax/quotation/productItemDraft/modifyProp",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "propName": propName,
            "propValue": propValue
        }),
        success: function (data) {
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}


function disableModification() {
    $("input[type=text]").each(function () {
        this.readOnly = true;
    });
}


$(function () {
    initTotal();

    $("#data-table input[type=text]").bind("keyup change", function (e) {
        modifyProp(this);
    });

    $("#show-accumulative-total").click(function (e) {
        e.preventDefault();
        getAccumulativeTotal();
    });
    $("#order-generateOrderFromQuotation").click(function (e) {
        e.preventDefault();
        generateOrderFromQuotation();
    });

    if (generatedOrder === true) {
        disableModification();
    }
});