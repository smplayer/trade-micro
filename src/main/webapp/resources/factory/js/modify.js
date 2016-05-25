/**
 * Created by yrx on 2016/5/9.
 */
function CloseWebPage(){
    if (navigator.userAgent.indexOf("MSIE") > 0) {
        if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
            window.opener = null;
            window.close();
        } else {
            window.open('', '_top');
            window.top.close();
        }
    }
    else if (navigator.userAgent.indexOf("Firefox") > 0) {
        window.location.href = 'about:blank ';
    } else {
        window.opener = null;
        window.open('', '_self', '');
        window.close();
    }
}

$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        
        var factory = {
            "id" : $("#factory-id").val(),
            "remark": $("#remark").val()
        };
        $("#form1 input[type=text]").each(function () {
            factory[$(this).attr("name")] = $(this).val();
        });


        $.ajax({
            type: 'POST',
            url: confirmUrl,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(factory),
            success: function(data){
                // alert('数据加载成功');
                window.opener.location.reload();
                CloseWebPage();
            },
            error: function(xhr, type){
                alert('数据加载失败' + type);
                console.log(xhr);
            }
        });
    });
});