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

        console.log($("#iframe-common2", window.parent.document)[0].contentWindow);
        console.log($("#factoryId", $($("#iframe-common", window.parent.document)[0].contentWindow.document)));
        console.log($("#factoryId", $($("#iframe-common", window.parent.document)[0].contentWindow.document)).val());

        $("#factoryId", $($("#iframe-common", window.parent.document)[0].contentWindow.document)).val(checkedItems.val());
        $("#factoryName", $($("#iframe-common", window.parent.document)[0].contentWindow.document)).val(factoryName);
        window.parent.closeFactorySelectionDialogForProduct();
    }
}

$(function () {
    $("#btn-confirm").click(function (e) {
        e.preventDefault();
        selectFactory();
    });
});