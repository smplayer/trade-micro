/**
 * Created by yrx on 2016/5/8.
 */

function uploadImage(id) {
    $("#iframe-upload-image").attr("src", ctx + "/product/uploadImage?id=" + id);
    dialog("#dialog-upload-image");
}

function uploadImageFinish() {
    dialog("#dialog-upload-image", {close : true});
}

function showBigProductImage($img) {
    $("#big-img").attr("src", $img.attr("src"));
    dialog("#dialog-big-image");
}

function deleteProducts() {
    var ids = [];
    $("[name=id]:checked").each(function () {
        ids.push($(this).val())
    });
    if (ids.length > 0) {
        $.ajax({
            type: 'POST',
            url: ctx + "/product/delete",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(ids),
            success: function(data){
                window.location.reload();
            },
            error: function(xhr, type){
                alert('数据加载失败' + type);
            }
        });
    }
    
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



function openFactorySelectionDialogForProduct(productId, factoryName) {

    var $form = $("<form method='post' target='iframe-common2'></form>");
    $form.attr("action", ctx + "/product/findFactoryForProduct");
    $form.append($("<input type='hidden' name='factoryName' />").val(factoryName));
    $form.append($("<input type='hidden' name='productId' />").val(productId));
    $form.submit();


    openSimpleCommonDialog("#dialog-common2");


    // var width = $(window).width();
    // var height = $(window).height();
    // $("#dialog-common2").css("width", width);
    // $("#dialog-common2").css("height", height);
    // $("iframe", "#dialog-common2").css("width", width);
    // $("iframe", "#dialog-common2").css("height", height);
    // dialog("#dialog-common2", {top: 0});

}

function openFactoryCreationDialogForProduct(productId, factoryName) {

    var $form = $("<form method='post'></form>");
    $form.attr("target", "iframe-common3");
    $form.attr("action", ctx + "/factory/createFactoryForProduct");
    $form.append($("<input type='hidden' name='productId' />").val(productId));
    $form.append($("<input type='hidden' name='factoryName' />").val(factoryName));
    $form.submit();

    openSimpleCommonDialog("#dialog-common3");

}



function closeFactorySelectionDialogForProduct() {
    dialog("#dialog-common2", {close: true});
}


function closeFactoryCreationDialogForProduct() {
    dialog("#dialog-common3", {close: true});
}







function copyProduct() {
    var count = $.trim($("#copy-count").val());
    var count = count == '' ? 1 : count;
    var newHref = ctx + "/product/copy?targetId=" + $("input.product-checkbox.first-item").val() + "&count=" + count;
    // if (pageIndex != '') {
    //     newHref += "&pageIndex=" + pageIndex;
    // }
    document.location.href = newHref;
}



function search() {
    var keywords = $("#keywords").val();
    var url = document.location.pathname;
    if ($.trim(keywords) != '') {
        var $form = $("<form method='post'></form>");
        $form.attr("target", "_self");
        $form.attr("action", url);
        $form.append($("<input type='hidden' name='keywords' />").val(keywords));
        $form.submit();
    } else {
        document.location.href = url;
    }
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



function generateProductNo() {
    var $checked = $("[name=id]:checked");
    if ($checked.length > 0) {
        var ids = [];

        $checked.each(function () {
            ids.push($(this).val());
            this.checked = false;
        });

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax/product/generateProductNo",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(ids),
            success: function(data){
                for(var key in data.idAndNoMap)
                    $("#companyProductNo-" + key).val(data.idAndNoMap[key]);

                if (data.allSuccess === false) {
                    dialogAlert("#dialog-alert", {
                        textContent: '抱歉，资料不全不能生成货号'
                    });
                }

            },
            error: function(xhr, type){
            }
        });
    }
}

$(function () {
    $("#product-list .factoryName").mouseover(function (e) {
        var context = $(this).parents('.item').first();
        var id = $(".factoryId", context).val();
        showFactoryDetails(id, $(this).attr("id"));
    });
    $("#product-list .factoryName").mouseout(function (e) {
        removeFactoryDetails();
    });

    $("#select-all").click(selectAll);
    $("#btn-query").click(function (e) {
        e.preventDefault();
        search();
    });
    $("#keywords").bind("keyup",function (e) {
        if(window.event.keyCode == 13) {
            e.preventDefault();
            search();
        }
    });

    $(".product-image").click(function (e) {
        showBigProductImage($(this));
    });
    $(".upload-image").click(function (e) {
        var id = $(this).attr("data-product-id");
        uploadImage(id);
    });
    // $("#add-product").click(function (e) {
    //     e.preventDefault();
    //     open($(this).attr("href"),'_blank');
    // });
    $("#save").click(function (e) {
        e.preventDefault();
        var products = [];
        $(".product-checkbox:checked").each(function () {
            var context = $(this).parents('tr').first();
            var p = {};
            $("input", context).each(function () {
                if($(this).attr("name") && $(this).attr("name")!="")
                    p[$(this).attr("name")] = $(this).val();
            });
            products.push(p);
        });
        $.ajax({
            type: 'POST',
            url: ctx + "/ajax/product/modify",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(products),
            success: function(data){
                window.location.reload();
            },
            error: function(xhr, type){
                // alert('数据加载失败' + type);
                // console.log(xhr);
            }
        });
    });
    $("#del").click(function (e) {
        e.preventDefault();
        deleteProducts();
    });
    
//        $("#editor-template").blur(function () {
//            var parent = $(this).parents('editable').first();
//            var content = $(this).val();
//            parent.empty().text(content);
//        });
//        $("#editor-template").click(function (e) {
//            e.stopPropagation();
//        });
//
//        $(".editable").click(function () {
//            var content = $(this).text();
//            var editor = $("#editor-template").clone(true).removeAttr("id").val(content);
//            $(this).empty().append(editor);
//        });



    $("#copy-product").click(copyProduct);

    $("#generate-product-no").click(generateProductNo);
    
    
});