function loadFavorOrderCustomerList() {
    $.ajax({
        type: 'POST',
        url: ctx + "/order/favorCustomer/list",
        dataType: 'json',
        contentType: "application/json",
        success: function (data) {
            $("#customer-list ul.current").remove();
            var $favorList = $("#customer-list ul.template").clone(true).addClass('current').removeClass('template hidden').appendTo("#customer-list").find(".favor-customer");
            $(data).each(function (i,n) {
                if( i < $favorList.length) {
                    var $f = $($favorList[n.indexNumber - 1]);
                    var $a = $("a", $f);
                    $a.text(n.customerName).attr("href", ctx + "/order?favorId=" + n.id).removeClass("hidden");
                    $f.removeClass("empty");
                    if(favorId == n.id) {
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



function getAccumulativeTotal() {
    if(favorId != "") {
        var accumulativeTotal = $.ajax({
            type: 'POST',
            url: ctx + "/order/accumulativeTotal",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "favorId": favorId,
                "pageIndex": pageIndex,
                "pageSize": pageSize
            }),
            success: function (data) {

                $("#accumulativeLine .orderedCartonQuantity").text(data.cartonQuantity);
                $("#accumulativeLine .volume").text(data.volume.toFixed(1));
                $("#accumulativeLine .payment").text(data.payment.toFixed(0));
                $("#accumulativeLine .deliveredCartonQuantity").text(data.deliveredCartonQuantity);
                $("#accumulativeLine .remainingCartonQuantity").text(data.remainingCartonQuantity);
                $("#accumulativeLine .scheduledDeliverableCartonQuantity").text(data.scheduledDeliverableCartonQuantity);
                $("#accumulativeLine .scheduledDeliverableVolume").text(data.scheduledDeliverableVolume.toFixed(1));
                $("#accumulativeLine .scheduledDeliverablePayment").text(data.scheduledDeliverablePayment.toFixed(0));
                // $("#accumulativeTotal-orderedProductQuantity").text(data.productQuantity);
                // $("#accumulativeTotal-totalVolume").text(data.volume.toFixed(1));
                // $("#accumulativeTotal-totalAmount").text(data.amount.toFixed(0));
            },
            error: function (xhr, type) {
                //alert('数据加载失败' + type);
            }
        });
    }
}

function syncProp(n) {

    var context = $(n).parents(".item").first();
    var id = $("input[name=id]", context).val();
    var propName = $(n).attr("name");
    var propValue = $(n).val();

    $.ajax({
        type: 'POST',
        // async: false,
        url: ctx + "/order/modifyItemProp",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "propName": propName,
            "propValue": propValue
        }),
        success: function (data) {
            var context = $("#" + data.item.id).parents('.item').first();

            if (data.item["scheduledDeliverableCartonQuantity"] > data.item["remainingCartonQuantity"]) {
                $(".scheduledDeliverableCartonQuantity", context).val('');
                syncProp(n);
            } else {
                var fieldsToRefresh = [
                    'factoryPrice',
                    'volume',
                    'payment',
                    'remainingCartonQuantity',
                    'scheduledDeliverableVolume',
                    'scheduledDeliverablePayment'
                ];

                for (var i = 0; i < fieldsToRefresh.length; i++) {
                    if (fieldsToRefresh[i] != $(n).attr('name') && data.item[fieldsToRefresh[i]] != 0) {
                        var value = data.item[fieldsToRefresh[i]];
                        if (value) {
                            if (
                                fieldsToRefresh[i] == "volume" ||
                                fieldsToRefresh[i] == "scheduledDeliverableVolume"
                            ){
                                value = value.toFixed(1);
                            }
                            $("." + fieldsToRefresh[i], context).text(value);
                        }
                    } else {

                        $("." + fieldsToRefresh[i], context).text('');
                    }
                }

                getAccumulativeTotal();
            }
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
}

function initInput() {
    $("#main-table .item input[type=text]").bind("keyup change", function (e) {
        //TO-DO 考虑值为空的状况
        if (
            e.keyCode != 9  &&
            e.keyCode != 16 &&
            e.keyCode != 17 &&
            e.keyCode != 18 &&
            e.keyCode != 20 &&
            e.keyCode != 27 &&
            e.keyCode != 37 &&
            e.keyCode != 38 &&
            e.keyCode != 39 &&
            e.keyCode != 40
        ) {
            syncProp(this);
        }
    });
}

function search() {
    var $form = $("<form method='post'></form>");
    $form.attr("target", "_self");
    $form.attr("action", ctx + "/order");
    $form.append($("<input type='hidden' name='keywords' />").val($("#keywords").val()));
    $form.append($("<input type='hidden' name='favorId' />").val(favorId));
    $form.submit();
}



function showFactoryDetails(factoryId, nodeId) {
    if (factoryId && factoryId != '') {
        $.ajax({
            type: 'POST',
            url: ctx + "/factory/getFactoryInfo",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                id: factoryId
            }),
            success: function(data){
                var detailsDialog = $("#factoryDetails");
                $("#factoryDetails .factoryName").text(data.linkman);
                $("#factoryDetails .contactNumber").text(data.mobileNumber);

                var factoryNameNode = $("#" + nodeId);
                var offset = factoryNameNode.offset();
                detailsDialog.css("top", offset.top - (detailsDialog.height() - factoryNameNode.height()) / 2);
                detailsDialog.css("left", offset.left + (factoryNameNode.width() / 2) + 35);

                detailsDialog.show();
            },
            error: function(xhr, type){
            }
        });
    }
}

function removeFactoryDetails() {
    $("#factoryDetails").hide();
}

function initOpenProductionSheetAction() {
    var date = $(".addedDate").first().text;
    var factoryId = "";

    var n = 1;
    $(".open-production-sheet").each(function (i, a) {
        var context = $(this).parents('.item').first();
        var curDate = $(".addedDate", context).text();
        var curFactoryId = $(".factoryId", context).val();
        $(this).attr("data-group-id", curFactoryId + "_" + ((n-1) / 6).toFixed(0));

        if (date != curDate) {
            date = curDate;
            factoryId = "";
            n = 1;
        }

        if (curFactoryId != factoryId || n % 6 == 1){
            $(this).show();
            factoryId = curFactoryId;
        }

        n++;

    });
}

function startInputProductNo() {
    $("#iframe-common").attr("src", ctx + "/order/inputProductNo?id=" + favorId);
    dialog("#dialog-common", {
        top: 80,
        onClose : function () {
        }
    });
}

function openProductionSheetClick(e) {
    e.preventDefault();
    // var context = $(this).parents('.item').first();
    var groupId = $(this).attr("data-group-id");
    var itemIds = [];
    $("[data-group-id=" + groupId + "]").each(function () {
        itemIds.push($(this).attr("data-item-id"));
    });

    if (itemIds.length > 0) {
        for (var i = 0; i < itemIds.length; i++) {
            $("#form1").append($("<input type='hidden' name='itemIds' />").val(itemIds[i]));
        }
        $("#form1").submit();
    }

}

function toContainer(e) {
    var volume = $("#containerVolume").val();
    if (!(/^[1-9]\d*$/.test(volume))) {
        $("#containerVolume").val('');
    } else {
        var $form = $("<form method='post'></form>");
        $form.attr("target", "_self");
        $form.attr("action", ctx + "/container/generateFromOrder");
        $form.append($("<input type='hidden' name='favorId' />").val(favorId));
        $form.append($("<input type='hidden' name='containerVolume' />").val(
            $("#containerVolume").val()
        ));
        $form.submit();
    }
}

$(function () {
    $("#toContainer").click(function (e) {
        toContainer(e);
    });

    $(".open-production-sheet").click(openProductionSheetClick);
    
    $("#main-table .factoryName").mouseover(function (e) {
        var context = $(this).parents('.item').first();
        var id = $(".factoryId", context).val();
        showFactoryDetails(id, $(this).attr("id"));
    });
    $("#main-table .factoryName").mouseout(function (e) {
        removeFactoryDetails();
    });

    $("#select-all").click(selectAll);
    $("#keywords").keyup(function (e) {
        if (e.keyCode == 13) {
            search();
        }
    });
    $("#search").click(search);

    $("#inputProductNo").click(function (e) {
        startInputProductNo();
    });


    initInput();

    loadFavorOrderCustomerList();

    getAccumulativeTotal();

    initOpenProductionSheetAction();
})