/**
 * Created by yrx on 2016/5/4.
 */
// function initEmptyLine() {
//     $("input.empty").each(function () {
//         $(this).data("line-id", Math.uuid())
//         console.log($(this).data("line-id"));
//     });
// }
var plan;
var sync = true;
function startSync() {
    // setTimeout(function () {
    //     sync = true;
    // }, 1000);
    sync = true;
}
function stopSync() {
    sync = false;
}

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
            // newId = data.item.id;
            // console.log(lineId + ":" + data.item.id);
            // $("input[data-line-id='" + lineId + "']").attr("id", data.item.id);
            // $n.val(data.item.id);
            callback(data.item.id);
            startSync();
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
            // console.log(xhr);
            startSync();
        }
    });
}

function modifyItemProp(id, propName, propValue) {

    $.ajax({
        type: 'POST',
        // async: false,
        url: modifyItemPropUrl,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "propName": propName,
            "propValue": propValue
        }),
        success: function (data) {
            startSync();
            
            var context = $("#" + data.item.id).parents('.item').first();
            
            for ( var propName in data.item) {
                $("input[name=" + propName + "]", context).val(data.item[propName]);
            }
            
        },
        error: function (xhr, type) {
            alert('数据加载失败' + type);
            // console.log(xhr);
            startSync();
        }
    });
}

function syncItem(n) {
    // stopSync();
    if(plan){
        clearTimeout(plan);
    }
    plan = setTimeout(function () {
        var quotationId = $("#quotation-id").val();

        var context = $(n).parents('.item').first();
        var id = $("input[name=id]", context).attr("id");

        if (!id || id == "") {
            //TO-DO 在创建成功之前避免重复创建
            createNewItem(quotationId, function (newId) {
                $("input[name=id]", context).attr("id", newId);
                syncItem(n);
            });
            return;
        } else {
            var propName = $(n).attr("name");
            var propValue = $(n).val();
            modifyItemProp(id, propName, propValue)
        }
    }, 500);
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

$(function () {
    // initEmptyLine();

    $("#main-table input[type=text]").keyup(function (e) {
        if($(this).attr("name") == "cartonSize") {
            var reg = /^[1-9]+[0-9]*X[1-9]+[0-9]*X[1-9]+[0-9]*$/;
            if(reg.test($(this).val())) {
                syncItem(this);
            }
        }else if ($(this).attr("name") == "factoryName") {
            $(this).removeClass("found-factory");
            syncItem(this);
        } else {
            syncItem(this);
        }
    });

});