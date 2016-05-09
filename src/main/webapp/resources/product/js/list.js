/**
 * Created by yrx on 2016/5/8.
 */

$(function () {
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
            url: productModificationUrl,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(products),
            success: function(data){
                alert('数据加载成功');
            },
            error: function(xhr, type){
                alert('数据加载失败' + type);
                console.log(xhr);
            }
        });
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