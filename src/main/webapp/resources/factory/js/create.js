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

        var empty = true;
        $("#form1 input[type=text].prop-value").each(function () {
            if ($.trim($(this).val()) != "") {
                empty = false;
                factory[$(this).attr("name")] = $(this).val();
            }
        });

        if (empty === true) {
            window.parent.location.reload();
        } else {
            $.ajax({
                type: 'POST',
                url: ctx + "/ajax/factory/create",
                dataType: 'json',
                contentType: "application/json",
                // contentType: "application/x-www-form-urlencoded",
                data: JSON.stringify(factory),
                success: function(data){
                    // alert('数据加载成功');
                    // window.opener.location.reload();
                    window.parent.location.reload();
                    // CloseWebPage();
                },
                error: function(xhr, type){
                    // alert('数据加载失败' + type);
                    // console.log(xhr);
                }
            });
        }

    });
});