/**
 * Created by yrx on 2016/5/25.
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

function getQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null)
        return  unescape(r[2]); return null;
}

function dialog2(options) {
    this.dialogNode = $(options.id);
}

function dialog(dialog, options) {
    var $dialog = $(dialog);
    
    if (options && options.close === true) {
        if(options && options.onClose) {
            options.onClose();
        }
        $dialog.hide();
        return;
    }
    $(dialog + " .dialog-close").click(function () {
        if(options && options.onClose) {
            options.onClose();
        }
        $dialog.hide();
    });
    
    var top = 0;
    if (options && (options.top || options.top == 0)) {
        top = options.top;
    } else {
        var windowHeight = $(window).height();
        var scrollTop = $(document).scrollTop();
        var dialogHeight = $dialog.height();
        var top = scrollTop + (windowHeight - dialogHeight)/2;
        if (options && options.topOffset) {
            top += options.topOffset;
        }
    }

    var left = 0;
    if (options && (options.left || options.left == 0)) {
        left = options.left;
    } else {
        var windowWidth = $(window).width();
        var scrollLeft = $(document).scrollLeft();
        var dialogWidth = $dialog.width();
        var left = scrollLeft + (windowWidth - dialogWidth)/2;
        if (options && options.leftOffset) {
            left += options.leftOffset;
        }
    }

    $dialog.css("position", "absolute")
        .css("top", top)
        .css("left", left)
        .show();
    
    return $dialog;
}

function dialogAlert(dialogId, options) {
    var $dialog = $(dialogId);
    $(".dialog-alert-text-content", $dialog).html(options.textContent);
    return dialog(dialogId, options);
}



var beforeOpenDialog = [];
var beforeCloseDialog = [];

function openCommonDialog(a, defaultModuleNotice) {
    var id = $(a).attr('id');
    if (beforeOpenDialog[id]) {
        beforeOpenDialog[id](a);
    }
    var url = $(a).attr("href");
    $("#iframe-common").attr("src", url);
    var width = $(window).width();
    var height = $(window).height() - 80;
    $("#dialog-common").css("width", width);
    $("#dialog-common").css("height", height);
    $("#iframe-common").css("width", width);
    $("#iframe-common").css("height", height);
    setModuleNotice($(a).attr("alt"));
    
    var dialogId = "#dialog-common";
    dialog(dialogId, {top: 80, onClose: function(){
        beforeCloseDialog[id](a);
        setModuleNotice(defaultModuleNotice);
    }});
}

function closeCommonDialog(iDialogId) {
    var dialogId = "#dialog-common";
    if (iDialogId) {
        dialogId = iDialogId;
    }
    dialog(dialogId,{close: true, onClose: function () {
        setModuleNotice(document.title);
    }});
}

function openSimpleCommonDialog(iDialogId) {
    var dialogId = "#dialog-common";
    if (iDialogId) {
        dialogId = iDialogId;
    }

    var width = $(window).width();
    var height = $(window).height() - 80;
    $(dialogId).css("width", width);
    $(dialogId).css("height", height);
    $("iframe", dialogId).css("width", width);
    $("iframe", dialogId).css("height", height);
    dialog(dialogId, {top: 80});
}

function closeSimpleCommonDialog(iDialogId) {
    var dialogId = "#dialog-common";
    if (iDialogId) {
        dialogId = iDialogId;
    }
    dialog(dialogId, {close: true});
}

$(function () {
    $(".open-in-dialog").click(function (e) {
        e.preventDefault();
        openCommonDialog(this, document.title);
    });




})