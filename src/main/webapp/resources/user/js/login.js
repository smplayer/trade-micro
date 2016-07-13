/**
 * Created by yrx on 2016/5/27.
 */
function alertIfError() {
    if ($.trim(error) != '') {
        dialogAlert("#dialog-alert", {
            topOffset: -100,
            leftOffset: 420,
            textContent: "用户名或密码错误<br/><br/>请重新输入",
            onClose: function () {
                $("#password").focus();
            }
        })
    }
}

$(function () {
   alertIfError();

    $("#password").focus();
});