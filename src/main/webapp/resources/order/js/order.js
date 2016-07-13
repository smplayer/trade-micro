

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
            var context = $(n).parents(".item").first();
            var id = $("input[name=id]", context).val();
            var propName = $(this).attr("name");
            var propValue = $(this).val();

            $.ajax({
                type: 'POST',
                // async: false,
                url: ctx + "/ajax/order/productItem/modifyProp",
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify({
                    "id": id,
                    "propName": propName,
                    "propValue": propValue
                }),
                success: function (data) {

                    var context = $("#" + data.item.id).parents('.item').first();
                    
                    if (propName == "cartonSize" ) {
                        
                    } else if (propName == "packingQuantity") {
                        
                    } else if (propName == "grossWeight") {

                    } else if (propName == "netWeight") {

                    } else if (propName == "orderedCartonQuantity") {

                    } else if (propName == "scheduledDeliverableCartonQuantity") {

                    }
                    
                    
                    $("input[name=orderedProductQuantity]", context).val(data.item["orderedProductQuantity"] != 0 ? data.item["orderedProductQuantity"] : "");
                    $("input[name=totalVolume]", context).val(data.item["totalVolume"] != 0 ? data.item["totalVolume"] : "");
                    $("input[name=totalAmount]", context).val(data.item["totalAmount"] != 0 ? data.item["totalAmount"] : "");
                    $("input[name=quotedPrice]", context).val(data.item["quotedPrice"] != 0 ? data.item["quotedPrice"] : "");


                    getAccumulativeTotal();
                },
                error: function (xhr, type) {
                    //alert('数据加载失败' + type);
                }
            });
            
            
            
            
            
            
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



function showFactoryDetails(factoryId) {
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

                var factoryNameNode = $("#" + data.id);
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
function removeFactoryDetails(factoryId) {
    $("#factoryDetails").hide();
}

$(function () {
    
    $("#main-table .factoryName").mouseover(function (e) {
        showFactoryDetails($(this).attr("id"));
    });
    $("#main-table .factoryName").mouseout(function (e) {
        removeFactoryDetails($(this).attr("id"));
    });

    $("#select-all").click(selectAll);
    $("#keywords").keyup(function (e) {
        if (e.keyCode == 13) {
            search();
        }
    });
    $("#search").click(search);
    loadFavorOrderCustomerList();
})