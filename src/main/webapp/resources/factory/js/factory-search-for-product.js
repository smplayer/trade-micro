/**
 * Created by yrx on 2016/5/9.
 */
function selectFactory() {

    var checkedItems = $(".factory-id-checkbox:checked");
    if(checkedItems.length == 0){
        alert("请选择一项");
    } else if (checkedItems.length > 1) {
        alert("只能选择一项");
    } else {
        var context = checkedItems.parents(".item").first();
        var factoryName = $(".factoryName", context).text();
        $("#factoryId", window.opener.document).val(checkedItems.val());
        $("#factoryName", window.opener.document).val(factoryName);
        CloseWebPage();
    }
}

$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        selectFactory();
    });
});