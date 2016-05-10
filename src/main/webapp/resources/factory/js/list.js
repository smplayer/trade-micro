/**
 * Created by yrx on 2016/5/9.
 */
$(function () {
    $(".modify-factory").click(function (e) {
        e.preventDefault();
        open($(this).data("url"),'_blank');
    });
    
    $("#search").click(function () {
        // var keywords = $("#keywords").val();
        // $('<form action="' + searchUrl + '" target="_self"></form>')
        //     .append($('<input type="text" name="keywords"/>').val(keywords))
        //     .submit();
        $("#form-search").submit();
    });
});