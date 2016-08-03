/**
 * Created by yrx on 2016/5/9.
 */
$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        
        var factory = {
            "id" : $("#factory-id").val(),
            "summary": $("#summary").val()
        };

        var empty = true;
        $("#form1 input[type=text].prop-value").each(function () {
            if ($.trim($(this).val()) != "") {
                empty = false;
                factory[$(this).attr("name")] = $(this).val();
            }
        });

        if ($("#name").val() == "") {
            dialogAlert("#dialog-alert", {
                textContent: '厂名不能为空'
            });
            return;
        }

        if ($("#mobileNumber").val() == "") {
            dialogAlert("#dialog-alert", {
                textContent: '手机/电话不能为空'
            });
            return;
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax/factory/create",
            dataType: 'json',
            contentType: "application/json",
            // contentType: "application/x-www-form-urlencoded",
            data: JSON.stringify(factory),
            success: function(data){
                // alert('数据加载成功');

                $("#factoryId", $($("#iframe-common", window.parent.document)[0].contentWindow.document)).val(data.factory.id);
                $("#factoryName", $($("#iframe-common", window.parent.document)[0].contentWindow.document)).val(data.factory.name);
                window.parent.closeFactoryCreationDialogForProduct();
                // CloseWebPage();
            },
            error: function(xhr, type){
                // alert('数据加载失败' + type);
                // console.log(xhr);
            }
        });

    });
});