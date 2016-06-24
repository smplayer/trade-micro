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

$(function () {
    $("#btn-query").click(function (e) {
        e.preventDefault();
        var keywords = $("#keywords").val();
        var url = document.location.pathname;
        if ($.trim(keywords) != '') {
            document.location.href = url + "?keywords=" + keywords;
        } else {
            document.location.href = url;
        }
    })

    $(".product-image").click(function (e) {
        showBigProductImage($(this));
    });
    $(".upload-image").click(function (e) {
        var id = $(this).attr("data-product-id");
        uploadImage(id);
    });
    $("#add-product").click(function (e) {
        e.preventDefault();
        open($(this).attr("href"),'_blank');
    });
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
});