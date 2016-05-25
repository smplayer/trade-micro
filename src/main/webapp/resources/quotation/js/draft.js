/**
 * Created by yrx on 2016/5/4.
 */
// function initEmptyLine() {
//     $("input.empty").each(function () {
//         $(this).data("line-id", Math.uuid())
//         console.log($(this).data("line-id"));
//     });
// }
var planning = [];
var synchronizing = [];
var timerIds = [];

function createNewItem(quotationId, callback) {
    $.ajax({
        type: 'POST',
        // async: false,
        url: createNewItemUrl,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(
            {"quotationId": quotationId}
        ),
        success: function (data) {
            callback(data.item.id);
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}

function modifyItemProp(id, propName, propValue, lineNumber) {

    $.ajax({
        type: 'POST',
        // async: false,
        url: modifyItemPropUrl,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "propName": propName,
            "propValue": propValue,
            "lineNumber": lineNumber
        }),
        success: function (data) {
            if(synchronizing[data['lineNumber'] + data['modifiedPropName']] === false) {
                var context = $("#" + data.item.id).parents('.item').first();
                for ( var pName in data.item) {
                    if(
                        pName == "orderedProductQuantity" ||
                        pName == "totalVolume" ||
                        pName == "totalAmount"
                    ) {
                        $("input[name=" + pName + "]", context).val(data.item[pName]);
                    } else {
                        if(data.item[pName] != 0)
                            $("input[name=" + pName + "]", context).val(data.item[pName]);
                        else
                            $("input[name=" + pName + "]", context).val("");
                    }
                }

                getAccumulativeTotal();
            }
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}

function syncItem(n) {
    // stopSync();
    var context = $(n).parents(".item").first();
    var lineNumber = $(".line-number", context).data('line-number');
    var fieldId = lineNumber+$(n).attr("name");
    synchronizing[fieldId] = true;
    if(planning[fieldId]){
        //避免重复操作
        clearTimeout(planning[fieldId]);
    }
    timerIds[fieldId] = timerIds[fieldId] ? ++timerIds[fieldId] : 1;
    planning[fieldId] = setTimeout(function () {
        var curTimerId = timerIds[fieldId];
        
        var quotationId = $("#quotation-id").val();

        var context = $(n).parents('.item').first();
        var id = $("input[name=id]", context).attr("id");

        if (!id || id == "") {
            createNewItem(quotationId, function (newId) {
                $("input[name=id]", context).attr("id", newId);
                syncItem(n);
            });
            return;
        } else {
            var propName = $(n).attr("name");
            var propValue = $(n).val();

            if(propName == "cartonSize") {
                var reg = /^[1-9]+[0-9]*[Xx\*][1-9]+[0-9]*[Xx\*][1-9]+[0-9]*$/;
                if(!reg.test(propValue)) {
                    return false;
                }
            } else if (
                propName == "packingQuantity" ||
                propName == "orderedCartonQuantity" ||
                propName == "orderedProductQuantity"
            ) {
                if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$/.test($.trim(propValue))) ) {
                    propValue = "0";
                }
            } else if (
                propName == "factoryPrice" ||
                propName == "grossWeight" ||
                propName == "netWeight" ||
                propName == "quotedPrice" ||
                propName == "totalVolume" ||
                propName == "totalAmount"
            ) {
                if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$|^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/.test($.trim(propValue))) ) {
                    propValue = "0";
                }
            }
            
            modifyItemProp(id, propName, propValue,lineNumber);
        }
        if(curTimerId == timerIds[fieldId]){
            synchronizing[fieldId] = false;
        }
        
        //延迟提交, 避免重复操作
    }, 400);
}

function findFactory() {
    var checkedItems = $("#main-table input[name=id]:checked");
    if(checkedItems.length == 0){
        alert("请选择一项");
    } else if (checkedItems.length > 1) {
        alert("只能选择一项");
    } else {
        var context = checkedItems.parents('.item').first();
        var factoryName = $("input[name=factoryName]", context).val();
        open(findFactoryUrl + "?quotationProductItemDraftId=" + checkedItems.val() + "&keywords=" + factoryName, '_blank');
    }
}

function extractAsProduct() {
    var checkedItems = $("#main-table input[name=id]:checked");
    var ids = [];
    var ok = true;
    checkedItems.each(function () {
        var context = $(this).parents(".item").first();
        var draftId = $("input[name=id]", context).val();
        var $factoryProductName = $("input[name=factoryProductName]", context);
        var $factoryProductNo = $("input[name=factoryProductNo]", context);
        if($factoryProductName.val() == "" && $factoryProductNo.val() == "") {
            alert("品名和货号不能全空");
            $factoryProductName.focus();
            ok = false;
            return false;
        }
        ids.push(draftId);
    });

    if(ok === true) {
        $.ajax({
            type: 'POST',
            url: generateProductsUrl,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "ids": ids
            }),
            success: function (data) {
                $(ids).each(function () {
                    var context = $("#" + this).parents(".item").first();
                    var $factoryProductNo = $("input[name=factoryProductNo]", context);
                    var $factoryProductName = $("input[name=factoryProductName]", context);
                    $factoryProductNo.addClass("extracted-product")
                    $factoryProductName.addClass("extracted-product")
                });
            },
            error: function (xhr, type) {
                alert('数据加载失败' + type);
            }
        });
    }
}

function getAccumulativeTotal() {
    var quotationId = $("#quotation-id").val();
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
            $("#accumulativeTotal-orderedCartonQuantity").text(data.cartonQuantity);
            $("#accumulativeTotal-orderedProductQuantity").text(data.productQuantity);
            $("#accumulativeTotal-totalVolume").text(data.volume.toFixed(0));
            $("#accumulativeTotal-totalAmount").text(data.amount.toFixed(0));
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
        }
    });
}

function initPage() {
    // var emptyFactoryPriceItems = $("input[name=factoryPrice][value='0']");
    // $(emptyFactoryPriceItems).each(function () {
    //     var context = $(this).parents(".item").first();
    //     $("input[value='0']", context).val("");
    // });
    $("#form .item input[value='0']").val("");
}

$(function () {
    initPage();
    
    // initEmptyLine();
    $("#main-table input[type=text]").bind("keyup", function (e) {
        //TO-DO 考虑值为空的状况
        syncItem(this);
    });

    $( "[name='factoryName']" ).bind("keyup", function (e) {
        $(this).removeClass("found-factory");
    });

    $(
        "[name='factoryProductName']," +
        "[name='factoryProductNo']," +
        "[name='packageForm']," +
        "[name='unit']," +
        "[name='factoryPrice']," +
        "[name='cartonSize']," +
        "[name='packingQuantity']," +
        "[name='grossWeight']," +
        "[name='netWeight']"
    ).bind("keyup", function (e) {
        var context = $(this).parents(".item").first();
        $(".extracted-product", context).removeClass("extracted-product");
    });

    $(".line-number").each(function (i,n) {
        $(this).data('line-number',i);
    });
    
    $("#btn-save").click(function (e) {
        e.preventDefault();
        window.location.reload();
    });

    $("[name=factoryProductName]").first().focus();

    getAccumulativeTotal();

});