/**
 * Created by yrx on 2016/5/9.
 */
$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        
        var factory = {
            "id" : $("#factory-id").val(),
            "summary": $("#summary").val(),
            "draftId": $("#draftId").val()
        };
        $("#form1 input[type=text]").each(function () {
            factory[$(this).attr("name")] = $(this).val();
        });


        $.ajax({
            type: 'POST',
            url: ctx + "/ajax/factory/create",
            dataType: 'json',
            contentType: "application/json",
            // contentType: "application/x-www-form-urlencoded",
            data: JSON.stringify(factory),
            success: function(data){
                // alert('数据加载成功');
                window.opener.location.reload();
                CloseWebPage();
            },
            error: function(xhr, type){
                // alert('数据加载失败' + type);
                // console.log(xhr);
            }
        });
    });
});