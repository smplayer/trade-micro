/**
 * Created by yrx on 2016/5/25.
 */

$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();

        var product = {
            "id" : $("#id").val(),
            "factoryId" : $("#factoryId").val(),
            "remark": $("#remark").val()
        };
        $("#form input[type=text]").each(function () {
            product[$(this).attr("name")] = $(this).val();
        });

        $.ajax({
            type: 'POST',
            url: $("#form").attr("action"),
            dataType: 'json',
            contentType: "application/json",
            // contentType: "application/x-www-form-urlencoded",
            data: JSON.stringify(product),
            success: function(data){
                // alert('数据加载成功');
                window.opener.location.reload();
                CloseWebPage();
            },
            error: function(xhr, type){
                alert('数据加载失败' + type);
            }
        });
    });



    $(".factory").click(function () {
        alert($(this).html());
        $("#factory-id").val($(this).data("factory-id"));
        $("#factoryName").val($(this).text());
    });

    $("#findExistingFactory").click(function (e) {
        e.preventDefault();
        var factoryName = $("#factoryName").val();
        open(ctx + "/quotation/findFactoryForProduct?factoryName=" + factoryName + "&productId=" + productId,'_blank');
    });
});