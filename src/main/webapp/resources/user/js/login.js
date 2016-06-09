/**
 * Created by yrx on 2016/5/27.
 */
function alertIfError() {
    if ($.trim(error) != '') {
        dialogAlert("#dialog-alert", {
            topOffset: -100,
            leftOffset: 420,
            textContent: error,
            onClose: function () {
                $("#password").focus();
            }
        })
    }
}

$(function () {
   if($.trim(error) != '') {
       alertIfError();
   }

    $("#password").focus();
});