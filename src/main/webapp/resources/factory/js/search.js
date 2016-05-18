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
function selectFactory() {

    var checkedItems = $(".factory-id-checkbox:checked");
    if(checkedItems.length == 0){
        alert("请选择一项");
    } else if (checkedItems.length > 1) {
        alert("只能选择一项");
    } else {
        
        $.ajax({
            type: 'POST',
            url: selectFactoryForProductItemDraftUrl,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "id": quotationProductItemDraftId,
                "factoryId": checkedItems.val()
            }),
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
        
    }
}

$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        selectFactory();
    });
});