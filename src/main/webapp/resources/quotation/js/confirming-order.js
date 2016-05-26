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
            "pageIndex": pageIndex,
            "pageSize": pageSize
        }),
        success: function (data) {
            $("#total-carton-quantity").text(data.cartonQuantity);
            $("#total-volume").text(data.volume);
            $("#total-amount").text(data.amount);
            dialog("#dialog-accumulation");
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}

$(function () {
    $("#show-accumulative-total").click(function (e) {
        getAccumulativeTotal();
    });
});