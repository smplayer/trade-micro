/**
 * Created by yrx on 2016/6/9.
 */
function showUploadedImage() {
    // console.log(window.parent.document);
    var $img = $("img[data-product-id=" + id + "]", window.parent.document);
    $img.attr("src", ctx + "/resources/upload/" + uploadedImagePath);
    $img.parents(".not-has-image").first().addClass("has-image").removeClass("not-has-image");
    window.parent.uploadImageFinish();
}

$(function () {
    showUploadedImage();
});