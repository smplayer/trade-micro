
function selectAll() {
    var $ids = $("[type=checkbox][name=id]");
    var $checked = $("[name=id]:checked");
    var status;
    if($ids.length > $checked.length && $checked.length >= 0) {
        status = 0;
    } else if ($ids.length == $checked.length) {
        status = 1;
    }
    if( status == 0) {
        $ids.each(function (e) {
            this.checked = true;
        });
    } else {
        $ids.each(function (e) {
            this.checked = false;
        });
    }
}

function loadFavorContainerList() {
    $.ajax({
        type: 'POST',
        url: ctx + "/container/favor/list",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            sheetId: sheetId
        }),
        success: function (data) {
            $("#container-list ul.current").remove();
            var $favorList = $("#container-list ul.template").clone(true).addClass('current').removeClass('template hidden').appendTo("#container-list").find(".favor-container");
            $(data).each(function (i,n) {
                if( i < $favorList.length) {
                    var $f = $($favorList[n.indexNumber - 1]);
                    var $a = $("a", $f);
                    $a.text("第" + n.indexNumber + "柜").attr("href", ctx + "/container/containerSheet?sheetId=" + sheetId + "&containerId=" + n.id).removeClass("hidden");
                    $f.removeClass("empty");
                    if(containerId == n.id) {
                        $f.addClass("current");
                    }
                }
            });
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
}

function save() {
    // var data = {
    //     containerId: containerId,
    //     shipmentPort: $("#shipmentPort").val(),
    //     destinationPort: $("#destinationPort").val(),
    //     deliveryDate: $("#deliveryDate").val(),
    //     containerType: $("#containerType").val(),
    //     cabinNo: $("#cabinNo").val(),
    //     containerNo: $("#containerNo").val(),
    //     sealNo: $("#sealNo").val(),
    //     carrier: $("#carrier").val()
    // }
    //
    //
    // $.ajax({
    //     type: 'POST',
    //     url: ctx + "ajax/container/modify",
    //     dataType: 'json',
    //     contentType: "application/json",
    //     data: JSON.stringify(data),
    //     success: function (data) {
    //         document.location.reload();
    //     },
    //     error: function (xhr, type) {
    //     }
    // });


    var $form = $("<form method='post'></form>");
    $form.attr("target", "_self");
    $form.attr("action", ctx + "/container/modify");
    $form.append($("<input type='hidden' name='sheetId' />").val(sheetId));
    $form.append($("<input type='hidden' name='containerId' />").val(containerId));
    $form.append($("<input type='hidden' name='shipmentPort' />").val(
        $("#shipmentPort").val()
    ));
    $form.append($("<input type='hidden' name='destinationPort' />").val(
        $("#destinationPort").val()
    ));
    $form.append($("<input type='hidden' name='deliveryDate' />").val(
        $("#deliveryDate").val()
    ));
    $form.append($("<input type='hidden' name='containerType' />").val(
        $("#containerType").val()
    ));
    $form.append($("<input type='hidden' name='cabinNo' />").val(
        $("#cabinNo").val()
    ));
    $form.append($("<input type='hidden' name='containerNo' />").val(
        $("#containerNo").val()
    ));
    $form.append($("<input type='hidden' name='sealNo' />").val(
        $("#sealNo").val()
    ));
    $form.append($("<input type='hidden' name='carrier' />").val(
        $("#carrier").val()
    ));
    $form.submit();
}

function moveItemsToLastContaizner() {
    if ($(".checkbox-item:checked").length > 0) {
        var $form = $("<form method='post'></form>");
        $form.attr("target", "_self");
        $form.attr("action", ctx + "/container/moveItemsToLastContainer");
        $form.append($("<input type='hidden' name='sheetId' />").val(sheetId));
        $form.append($("<input type='hidden' name='containerId' />").val(containerId));
        $(".checkbox-item:checked").each(function () {
            $form.append($("<input type='hidden' name='itemIdList' />").val(
                $(this).val()
            ));
        });
        $form.submit();
    } else {

    }
}


function getAccumulativeTotal() {
    var accumulativeTotal = $.ajax({
        type: 'POST',
        url: ctx + "/container/getAccumulativeTotal",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "sheetId": sheetId
        }),
        success: function (data) {
            $("#total-carton-quantity").text(data.cartonQuantity);
            $("#total-product-quantity").text(data.productQuantity);
            $("#total-volume").text(data.volume.toFixed(1));
            $("#total-amount").text(data.amount.toFixed(0));
            dialog("#dialog-accumulation");
        },
        error: function (xhr, type) {
            // alert('数据加载失败' + type);
        }
    });
}

$(function () {
    $("#select-all").click(selectAll);
    loadFavorContainerList();
    $("#save").click(function () {
        save();
    });
    $("#moveItemsToLastContainer").click(function () {
        moveItemsToLastContaizner();
    });
    $("#getAccumulativeTotal").click(function () {
        getAccumulativeTotal();
    });

})