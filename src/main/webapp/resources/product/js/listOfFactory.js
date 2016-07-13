function initSearch() {
    $("#search").click(function (e) {
        e.preventDefault();
        var $keywords = $("#keywords");
        $keywords.val($.trim($keywords.val()));
        if ($keywords.val() != '') {
            $("#form-search").append($keywords.clone()).submit();
        } else {
            $("#form-search").submit();
        }
    });
}

$(function () {
    initSearch();
})