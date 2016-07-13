/**
 * Created by yrx on 2016/5/9.
 */
function selectAll() {
    var $ids = $("[type=checkbox][name=id]");
    var $checked = $("[name=id]:checked");
    var status;
    if($ids.length > $checked.length && $checked.length >= 0) {
        status = 0;
    } else if ($ids.length == $checked.length) {
        status = 1;
    }
    if( status == 0) {
        $ids.each(function (e) {
            this.checked = true;
        });
    } else {
        $ids.each(function (e) {
            this.checked = false;
        });
    }
}

$(function () {
    $("#select-all").click(selectAll);
    
    // $("#create-factory").click(function (e) {
    //     e.preventDefault();
    //     open($(this).attr("href"),'_blank');
    // });
    
    // $(".modify-factory").click(function (e) {
    //     e.preventDefault();
    //     open($(this).data("url"),'_blank');
    // });
    
    $("#search").click(function () {
        // var keywords = $("#keywords").val();
        // $('<form action="' + searchUrl + '" target="_self"></form>')
        //     .append($('<input type="text" name="keywords"/>').val(keywords))
        //     .submit();
        $("#form-search").submit();
    });
    
    $("#del").click(function (e) {
        e.preventDefault();

        var ids = [];
        $("[name=id]:checked").each(function () {
            ids.push($(this).val())
        });
        if (ids.length > 0) {
            $.ajax({
                type: 'POST',
                url: ctx + "/factory/delete",
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function(data){
                    window.location.reload();
                },
                error: function(xhr, type){
                    // alert('数据加载失败' + type);
                }
            });
        }
    });
});