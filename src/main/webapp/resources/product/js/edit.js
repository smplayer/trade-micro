/**
 * Created by yrx on 2016/5/25.
 */


function setFactoryId(id) {
    $("#factoryId").val(id);
}


$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();

        if ($.trim($("#factoryId").val()) == '') {
            dialogAlert("#dialog-alert", {
                textContent: '很抱歉，请先进行工厂查新'
            });
            return false;
        }

        var factoryProductName = $.trim($("#factoryProductName").val());
        var factoryProductNo = $.trim($("#factoryProductNo").val());
        if (factoryProductName == '' && factoryProductNo == '') {
            dialogAlert("#dialog-alert", {
                textContent: '品名及货号至少填写一项'
            });
            return false;
        }
        var factoryPrice = $.trim($("#factoryPrice").val());
        if (factoryPrice == '') {
            dialogAlert("#dialog-alert", {
                textContent: '厂价不能为空'
            });
            return false;
        }

        var product = {
            "id" : $("#id").val(),
            "factoryId" : $("#factoryId").val(),
            "remark": $("#remark").val()
        };
        $("#form input[type=text], #form input[type=hidden]").each(function () {
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
                window.parent.location.reload();
                CloseWebPage();
            },
            error: function(xhr, type){
                // alert('数据加载失败' + type);
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
        if ($.trim(factoryName) != "") {
            window.parent.openFactorySelectionDialogForProduct(productId, factoryName);
        } else {
            window.parent.openFactoryCreationDialogForProduct(productId, factoryName);
        }

        // open(ctx + "/product/findFactoryForProduct?factoryName=" + factoryName + "&productId=" + productId,'_blank');
    });
    
    $("#btn-clear").click(function (e) {
        e.preventDefault();
        $(":text").each(function () {
            $(this).val("");
        });
    });
});