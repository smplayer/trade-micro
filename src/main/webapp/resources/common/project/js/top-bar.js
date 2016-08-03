/**
 * Created by yrx on 2016/5/22.
 */

function initMenu() {
    // $("#sub-menu").css("top", $("#action-product").offset().top + $("#action-product").height());
    $(".menu-item a.current").mouseover(function (e) {
        var aid = $(this).attr("id");
        var module = aid.substring(aid.indexOf("-") + 1);
        var $dropDown = $(".drop-down-menu." + module);
        if ($dropDown) {
            $dropDown.css("top", $(this).offset().top);
            $dropDown.css("left", $(this).offset().left - (($dropDown.width() - $(this).width()) / 2));
            $dropDown.show();
        }
        // $("#menu-current .current").hide();
    });

    $(".drop-down-menu").mouseleave(function (e) {
        $(this).hide();
        // $("#menu-current .current").show();
    });

    var $currentMenu = $(".menu-item a.current");
    if ($currentMenu.length > 0)
        $("#notice-current-module").css("left", $currentMenu.offset().left - (($("#notice-current-module").width() - $currentMenu.width()) / 2)).text(title).show();
}

function setModuleNotice(v) {
    $("#notice-current-module").text(v);
}

function initFavorModule() {

    $.ajax({
        type: 'POST',
        url: ctx + "/ajax/user/config/getValue",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "key": "user_favor_module"
        }),
        success: function (data) {
            $("[name=favor-module][value=" + data.value + "]").attr("checked", "checked");
        },
        error: function (xhr, type) {
            // alert('数据加载失败' + type);
        }
    });

}

$(function () {
    initMenu();

    initFavorModule();

    $("#quotation-saveToArchive").click(function (e) {
        e.preventDefault();
        if (typeof(currentModule) == "undefined" || currentModule == '' || currentModule != 'quotation') {
            alert("不在本模块不能操作");
        } else {
            document.location.href = $(this).attr("href") + "?id=" + quotationId;
        }
    });

    $("#set-start-number").click(function () {
        open(ctx + "/system/config/number", '_blank');
    });
    $("#modify-password").click(function () {
        open(ctx + "/system/setPassword", '_blank');
    });

    // $("#set-favor-module").click(function () {
    //     $("#top-bar").toggleClass("setting-favor");
    // });
    $(".top-bar .set-favor-module").click(function (e) {
        e.preventDefault();
        $(".top-bar").toggleClass("setting");
        $(".set-favor-module").toggleClass("setting");
    });

    $("#set-favor-module-confirm").click(function () {

        var $favor = $("[name=favor-module]:checked");
        if ($favor.length == 0) {
            alert("请选择模块");
            return false;
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax/user/config/setFavorModule",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "module": $favor.val()
            }),
            success: function (data) {
                // alert("设置成功");
            },
            error: function (xhr, type) {
                alert('数据加载失败' + type);
            }
        });
    });
});
