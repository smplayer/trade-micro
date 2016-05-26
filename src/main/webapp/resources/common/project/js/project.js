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

function dialog(dialog, options) {
    var $dialog = $(dialog);
    
    var windowHeight = $(window).height();
    var scrollTop = $(document).scrollTop();
    var dialogHeight = $dialog.height();
    
    var windowWidth = $(window).width();
    var scrollLeft = $(document).scrollLeft();
    var dialogWidth = $dialog.width();


    var top = scrollTop + (windowHeight - dialogHeight)/2;
    var left = scrollLeft + (windowWidth - dialogWidth)/2;

    $dialog.css("position", "absolute")
        .css("top", top)
        .css("left", left)
        .show();
    
    $(dialog + " .close").click(function () {
        if(options && options.onClose) {
            options.onClose();
        }
        $dialog.hide();
    });
}