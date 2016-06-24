/**
 * Created by yrx on 2016/5/6.
 */
var sm = {};

(function ($sm, $) {
    $sm.isBlank = function (v) {
        if(v === undefined || v === ""){
            return true;
        } else {
            return false;
        }
    }

    $sm.dialog = function (id, options) {
        var position = !$sm.isBlank(options.position) ? options.position : "center";
        var width = !$sm.isBlank(options.width) ? options.width : "100%";
        var height = !$sm.isBlank(options.height) ? options.height : "100%";

        var style = "width: " + width + "; height: " + height + "; position: absolute";

        var container = $("<div style='' ></div>");
        var $dialog = $("#" + id);
        $dialog.css("margin-left", "-" + $dialog.width() / 2 + "px");
        $dialog.css("margin-top", "-" + $dialog.height() / 2 + "px");

        var open = !$sm.isBlank(options.open) && options.open == false ? false : true;
        if(open){
            $dialog.show();
        } else {
            $dialog.hide();
        }
    }

    $sm.safeExecute = function (func, params) {
        if (typeof(func) == "function") {
            func(params);
        }
    }

    $sm.ajax = function (params) {
        $.ajax({
            type: params.type,
            url: params.url,
            data: params.data,
            dataType: params.dataType,
            contentType: params.contentType,
            success: function (data) {
                $sm.safeExecute(params.success, data);
            },
            error: function (xhr, type) {
                params.error(xhr, type);
            }
        });
    }
    
})(sm, $);