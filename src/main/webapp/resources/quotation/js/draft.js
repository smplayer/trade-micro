/**
 * Created by yrx on 2016/5/4.
 */
// function initEmptyLine() {
//     $("input.empty").each(function () {
//         $(this).data("line-id", Math.uuid())
//         console.log($(this).data("line-id"));
//     });
// }
var planning = [];
var synchronizing = [];
var timerIds = [];

function createNewItem(item, callback) {
    $.ajax({
        type: 'POST',
        // async: false,
        url: ctx + "/ajax/quotation/productItemDraft/create",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(
            item
        ),
        success: function (data) {
            callback(data.item.id);
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
}

function modifyItemProp(id, propName, propValue) {

    $.ajax({
        type: 'POST',
        // async: false,
        url: ctx + "/ajax/quotation/productItemDraft/modifyProp",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "propName": propName,
            "propValue": propValue
        }),
        success: function (data) {
            // if(synchronizing[data['lineNumber'] + data['propName']] === false) {
            //     var context = $("#" + data.item.id).parents('.item').first();
            //     for ( var pName in data.item) {
            //         if(pName != data.propName)
            //             if(
            //                 // pName == "orderedProductQuantity" ||
            //                 // pName == "totalVolume" ||
            //                 // pName == "totalAmount"
            //                 false
            //             ) {
            //                 $("input[name=" + pName + "]", context).val(data.item[pName]);
            //             } else {
            //                 if(data.item[pName] != 0)
            //                     $("input[name=" + pName + "]", context).val(data.item[pName]);
            //                 else
            //                     $("input[name=" + pName + "]", context).val("");
            //             }
            //     }
            //
            //     getAccumulativeTotal();
            // }

            var context = $("#" + data.item.id).parents('.item').first();
            $("input[name=orderedProductQuantity]", context).val(data.item["orderedProductQuantity"] != 0 ? data.item["orderedProductQuantity"] : "");
            $("input[name=totalVolume]", context).val(data.item["totalVolume"] != 0 ? data.item["totalVolume"] : "");
            $("input[name=totalAmount]", context).val(data.item["totalAmount"] != 0 ? data.item["totalAmount"] : "");
            $("input[name=quotedPrice]", context).val(data.item["quotedPrice"] != 0 ? data.item["quotedPrice"] : "");
            getAccumulativeTotal();
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
}

function syncItem0(n) {
    // stopSync();
    var context = $(n).parents(".item").first();
    var lineNumber = Number($(".line-number", context).attr('data-line-number'));
    synchronizing[fieldId] = true;
    if(planning[fieldId]){
        //避免重复操作
        clearTimeout(planning[fieldId]);
    }
    timerIds[fieldId] = timerIds[fieldId] ? ++timerIds[fieldId] : 1;
    planning[fieldId] = setTimeout(function () {
        var curTimerId = timerIds[fieldId];
        
        var quotationId = $("#quotation-id").val();

        var context = $(n).parents('.item').first();
        var id = $("input[name=id]", context).attr("id");

        if (!id || id == "") {
            // createNewItem({"quotationId", quotationId}, function (newId) {
            //     $("input[name=id]", context).attr("id", newId);
            //     syncItem(n);
            // });
            return;
        } else {
            var propName = $(n).attr("name");
            var propValue = $(n).val();

            if (propName != "remark") {
                if(propName == "cartonSize") {
                    // var reg = /^[1-9]+[0-9]*[Xx\*][1-9]+[0-9]*[Xx\*][1-9]+[0-9]*$/;
                    // if(!reg.test(propValue)) {
                    //     return false;
                    // }
                } else if (
                    propName == "packingQuantity" ||
                    propName == "orderedCartonQuantity" ||
                    propName == "orderedProductQuantity"
                ) {
                    if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$/.test($.trim(propValue))) ) {
                        propValue = "0";
                    }
                } else if (
                    propName == "factoryPrice" ||
                    propName == "grossWeight" ||
                    propName == "netWeight" ||
                    propName == "quotedPrice" ||
                    propName == "totalVolume" ||
                    propName == "totalAmount"
                ) {
                    if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$|^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/.test($.trim(propValue))) ) {
                        propValue = "0";
                    }
                }

                modifyItemProp(id, propName, propValue,lineNumber);
            }
        }
        if(curTimerId == timerIds[fieldId]){
            synchronizing[fieldId] = false;
        }
        
        //延迟提交, 避免重复操作
    }, 200);
}

function syncItem(n) {
        var quotationId = $("#quotation-id").val();

        var context = $(n).parents('.item').first();
        var id = $("input[name=id]", context).attr("id");
        var propName = $(n).attr("name");
        var propValue = $(n).val();

        if (propName != "remark") {
            if(propName == "cartonSize") {
                // var reg = /^[1-9]+[0-9]*[Xx\*][1-9]+[0-9]*[Xx\*][1-9]+[0-9]*$/;
                // if(!reg.test(propValue)) {
                //     return false;
                // }
            } else if (
                propName == "packingQuantity" ||
                propName == "orderedCartonQuantity" ||
                propName == "orderedProductQuantity"
            ) {
                if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$/.test($.trim(propValue))) ) {
                    propValue = "0";
                }
            } else if (
                propName == "factoryPrice" ||
                propName == "grossWeight" ||
                propName == "netWeight" ||
                propName == "quotedPrice" ||
                propName == "totalVolume" ||
                propName == "totalAmount"
            ) {
                if ( $.trim(propValue) == "" || !(/^(0|[1-9][0-9]*)$|^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/.test($.trim(propValue))) ) {
                    propValue = "0";
                }
            }

            modifyItemProp(id, propName, propValue);
        }
}

function findFactory(e) {
    e.preventDefault();
    var checkedItems = $("#main-table input[name=id]:checked");
    if(checkedItems.length == 0){
        dialogAlert("#dialog-alert", {
            textContent: '请选择要查新的厂名打勾'
        });
    } else if (checkedItems.length > 1) {s
    } else {
        var context = checkedItems.parents('.item').first();
        var factoryProductNo = $("input[name=factoryProductNo]", context).val();
        var factoryName = $("input[name=factoryName]", context).val();
        var linkman = $("input[name=linkman]", context).val();
        var contactNumber = $("input[name=contactNumber]", context).val();
        if (factoryName != '') {

            // $("#iframe-common").attr("src", ctx + "/quotation/findFactoryForDraft" + "?quotationProductItemDraftId=" + checkedItems.val() + "&keywords=" + encodeURI(encodeURI(factoryName)));
            var $form = $("<form method='post'></form>");
            $form.attr("target", "iframe-common");
            $form.attr("action", ctx + "/quotation/findFactoryForDraft");
            $form.append($("<input type='hidden' name='quotationProductItemDraftId' />").val(checkedItems.val()));
            $form.append($("<input type='hidden' name='factoryProductNo' />").val(factoryProductNo));
            $form.append($("<input type='hidden' name='keywords' />").val(factoryName));
            $form.append($("<input type='hidden' name='linkman' />").val(linkman));
            $form.append($("<input type='hidden' name='contactNumber' />").val(contactNumber));
            $form.submit();


            var width = $(window).width();
            var height = $(window).height() - 80;
            $("#dialog-common").css("width", width);
            $("#dialog-common").css("height", height);
            $("#iframe-common").css("width", width);
            $("#iframe-common").css("height", height);
            setModuleNotice($(this).attr("alt"));

            dialog("#dialog-common", {top: 80,});
        } else {
            dialogAlert("#dialog-alert", {
                textContent: '请填写厂名之后再查新'
            });
        }
    }
}

function extractAsProduct() {
    var checkedItems = $("#main-table input[name=id]:checked");
    var ids = [];
    checkedItems.each(function () {
        this.checked = false;

        var ok = true;

        var context = $(this).parents(".item").first();
        var draftId = $("input[name=id]", context).val();
        var $factoryProductName = $("input[name=factoryProductName]", context);
        var $factoryProductNo = $("input[name=factoryProductNo]", context);
        if($factoryProductName.val() == "" && $factoryProductNo.val() == "") {
            // alert("品名和货号不能全空");
            // $factoryProductName.focus();
            ok = false;
            // return false;
        }
        var $factoryPrice = $("[name=factoryPrice]", context);
        if($factoryPrice.val() == "") {
            // alert("厂价不能为空");
            // $factoryPrice.focus();
            ok = false;
            // return false;
        }
        var $contactNumber = $("[name=contactNumber]", context);
        if($contactNumber.val() == "") {
            // alert("手机/电话不能为空");
            // $contactNumber.focus();
            ok = false;
            // return false;
        }
        var $factoryName = $("[name=factoryName]", context);
        if(!$factoryName.hasClass("found-factory")) {
            // $factoryName.focus();
            ok = false;
            // return false;
        }
        if (ok === true)
            ids.push(draftId);
    });

    if(ids.length > 0) {
        $.ajax({
            type: 'POST',
            url: ctx + "/quotation/generateProducts",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "ids": ids
            }),
            success: function (data) {
                $(ids).each(function () {
                    var context = $("#" + this).parents(".item").first();
                    var $factoryProductNo = $("input[name=factoryProductNo]", context);
                    var $factoryProductName = $("input[name=factoryProductName]", context);
                    $factoryProductNo.addClass("extracted-product");
                    $factoryProductName.addClass("extracted-product");
                });
            },
            error: function (xhr, type) {
                //alert('数据加载失败' + type);
            }
        });
    }
}

function getAccumulativeTotal() {
    var quotationId = $("#quotation-id").val();
    if(quotationId != "") {
        var accumulativeTotal = $.ajax({
            type: 'POST',
            url: ctx + "/quotation/accumulativeTotal",
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "quotationId": quotationId,
                "pageQuantity": pageQuantity,
                "pageIndex": pageIndex,
                "pageSize": pageSize
            }),
            success: function (data) {
                $("#accumulativeTotal-orderedCartonQuantity").text(data.cartonQuantity);
                $("#accumulativeTotal-orderedProductQuantity").text(data.productQuantity);
                $("#accumulativeTotal-totalVolume").text(data.volume.toFixed(1));
                $("#accumulativeTotal-totalAmount").text(data.amount.toFixed(0));
            },
            error: function (xhr, type) {
                //alert('数据加载失败' + type);
            }
        });
    }
}


function loadFavorQuotationList() {
    $.ajax({
        type: 'POST',
        url: ctx + "/quotation/favor/list",
        dataType: 'json',
        contentType: "application/json",
        success: function (data) {
            $("#cus-list ul.current").remove();
            var $favorCusNameList = $("#cus-list ul.template").clone(true).addClass('current').removeClass('template hidden').appendTo("#cus-list").find(".favor-cus-name");
            $(data).each(function (i,n) {
                if( i < $favorCusNameList.length) {
                    var $f = $($favorCusNameList[n.indexNumber - 1]);
                    var $a = $("a", $f);
                    $a.text(n.customerName).attr("href", ctx + "/quotation/operating?id=" + n.quotationId);
                    $f.removeClass("empty");
                    if(quotationId == n.quotationId) {
                        $f.addClass("current");
                    }
                }
            });
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
}

function initFavorQuotationList() {
    $(".favor-cus-name.empty").each(function (i, n) {
        $("a", this).attr("href", ctx + "/quotation/operating?empty=true&indexNumber=" + (i + 1));
    });
    if (indexNumber > 0) {
        $($(".favor-cus-name.empty")[indexNumber - 1]).addClass('current');
    }
}

function initPage() {
    // var emptyFactoryPriceItems = $("input[name=factoryPrice][value='0']");
    // $(emptyFactoryPriceItems).each(function () {
    //     var context = $(this).parents(".item").first();
    //     $("input[value='0']", context).val("");
    // });
    if (quotationId == '') {
        // document.href = ctx + "/quotation/operating?empty=true&indexNumber=" + (i + 1);
    }

    if (empty === true) {
        // quotationOperatingSetting();
        openCommonDialog($("#quotation-operating-setting"));
    } else {
        $("#form .item input[value='0']").val("");
    }
    loadFavorQuotationList();
    initFavorQuotationList();
}

function uploadImage(id) {
    $("#iframe-upload-image").attr("src", ctx + "/quotation/uploadImage?id=" + id);
    dialog("#dialog-upload-image");
}

function uploadImageFinish() {
    dialog("#dialog-upload-image", {close : true});
}

function setFavorQuotationList() {
    $("#iframe-favor-setting").attr("src", ctx + "/quotation/favor/setting");
    dialog("#dialog-favor-setting", {
        onClose : function () {
            loadFavorQuotationList();
            initFavorQuotationList();
        }
    });
}

function setFavorQuotationListFinish() {
    // dialog("#dialog-favor-setting", {close : true});

    window.location.reload();
    
}

function quotationOperatingSetting() {
    var url = ctx + "/quotation/operating/setting";
    if ($.trim(quotationId) != '') {
        url += "?id=" + quotationId;
    } else if (indexNumber > 0) {
        url += "?indexNumber=" + indexNumber;
    }
    // $("#iframe-operating-setting").attr("src", url);
    // dialog("#dialog-operating-setting", {top: 80});
    $("#dialog-operating-setting").attr("href", url);
}
beforeOpenDialog['quotation-operating-setting'] = quotationOperatingSetting;

function quotationOperatingSettingFinish(id, isNew) {
    dialog("#dialog-operating-setting", {close : true});
    if (isNew && isNew === true) {
        window.location.href = ctx + "/quotation/operating?id=" + id;
    } else {
        window.location.reload();
    }
}

function deleteItems() {
    var ids = [];
    $("[name=id]:checked").each(function () {
        ids.push($(this).val())
    });
    
    $.ajax({
        type: 'POST',
        url: ctx + "/quotation/deleteDraftItem",
        data: JSON.stringify({
            "ids": ids
        }),
        dataType: 'json',
        contentType: "application/json",
        success: function (data) {
            document.location.reload();
        },
        error: function (xhr, type) {
            //alert('数据加载失败' + type);
        }
    });
    
}

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

function showBigProductImage($img) {
    $("#big-img").attr("src", $img.attr("src"));
    dialog("#dialog-big-image");
}



function copyItem() {
    $(".first.item input[type=text]").each(function () {
        var name = $(this).attr("name");
        if (
            name != "orderedCartonQuantity" &&
            name != "quotedPrice" &&
            name != "orderedProductQuantity" &&
            name != "totalVolume" &&
            name != "totalAmount"
        ) {
            $("#new-item input[name=" + name + "]").val($(this).val());
            $("#new-item input[name=factoryProductName]").focus();
        }
    });
}




function disableModification() {
    $("input[type=text]").each(function () {
        this.readOnly = true;
    });
}




$(function () {
    initPage();
    
    if (generatedOrder === true) {
        disableModification();
    }
    
    $("#copy-item").click(
        function () {
            if (generatedOrder == true) {
                return false;
            }
            copyItem();
        }
    );

    $("#find-factory").click(function(e) {
        if (generatedOrder == true) {
            return false;
        }
        findFactory(e);
    });

    $(".product-image").click(function (e) {
        showBigProductImage($(this));
    });

    $("#select-all").click(selectAll);

    $("#set-favor-quotation-list").click(function (e) {
        setFavorQuotationList();
    });
    
    $(".upload-image").click(function () {
        if (generatedOrder == true) {
            return false;
        }
        var id = $(this).attr("data-product-id");
        uploadImage(id);
    });

    $("#quotation-operating-setting").click(function (e) {
        // e.preventDefault();
        // quotationOperatingSetting();
    })
    
    // initEmptyLine();
    $("#main-table .item input[type=text]").bind("keyup change", function (e) {
        //TO-DO 考虑值为空的状况
        if (
            e.keyCode != 9  &&
            e.keyCode != 16 &&
            e.keyCode != 17 &&
            e.keyCode != 18 &&
            e.keyCode != 20 &&
            e.keyCode != 27 &&
            e.keyCode != 37 &&
            e.keyCode != 38 &&
            e.keyCode != 39 &&
            e.keyCode != 40
        ) {
            if(quotationId == '') {
                dialogAlert("#dialog-alert", {
                    textContent: '请先进行设置',
                    onClose: function () {
                        $(this).val('');
                    }.bind(this)
                });
            } else {
                syncItem(this);
            }
        }
    });

    $( "[name='factoryName']" ).bind("keyup", function (e) {
        if (
            e.keyCode != 9  &&
            e.keyCode != 16 &&
            e.keyCode != 17 &&
            e.keyCode != 18 &&
            e.keyCode != 20 &&
            e.keyCode != 27 &&
            e.keyCode != 37 &&
            e.keyCode != 38 &&
            e.keyCode != 39 &&
            e.keyCode != 40
        ) {
            $(this).removeClass("found-factory");
        }
    });

    $(
        "[name='factoryProductName']," +
        "[name='factoryProductNo']," +
        "[name='packageForm']," +
        "[name='unit']," +
        "[name='factoryPrice']," +
        "[name='cartonSize']," +
        "[name='packingQuantity']," +
        "[name='grossWeight']," +
        "[name='netWeight']"
    ).bind("keyup", function (e) {
        // var context = $(this).parents(".item").first();
        // $(".extracted-product", context).removeClass("extracted-product");
    });

    $(".line-number").each(function (i,n) {
        $(this).attr('data-line-number',i);
    });
    
    $("#btn-save").click(function (e) {
        e.preventDefault();

        if (generatedOrder == true) {
            return false;
        }
        
        // var factoryProductName = $("#new-item [name=factoryProductName]").val();
        // var factoryProductNo = $("#new-item [name=factoryProductNo]").val();
        // var factoryPrice = $("#new-item [name=factoryPrice]").val();
        // var contactNumber = $("#new-item [name=contactNumber]").val();
        //
        // if (factoryProductName == "" && factoryProductNo == "") {
        //     alert("品名或货号不能全空");
        //     $("#new-item [name=factoryProductName]").focus();
        //     return false;
        // }
        // if ( $.trim(factoryPrice) == "" ) {
        //     alert("厂价不能为空");
        //     $("#new-item [name=factoryPrice]").focus();
        //     return false;
        // }
        // if ( !(/^(0|[1-9][0-9]*)$|^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/.test($.trim(factoryPrice))) ) {
        //     alert("厂价格式错误");
        //     $("#new-item [name=factoryPrice]").focus();
        //     return false;
        // }
        // if ( $.trim(contactNumber) == "" ) {
        //     alert("手机/电话不能为空");
        //     $("#new-item [name=contactNumber]").focus();
        //     return false;
        // }
        var remarkData = [];
        $("[name=id]:checked").each(function () {
            var context = $(this).parents(".item").first();
            var id = $(this).val();
            var remark = $("[name=remark]", context).val();
            remarkData.push({
                "id": id,
                "remark": remark
            })
        });

        if (remarkData.length > 0) {
            $.ajax({
                type: 'POST',
                url: ctx + "/quotation/saveRemark",
                data: JSON.stringify({
                    "remarkData": remarkData
                }),
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    document.location.reload();
                },
                error: function (xhr, type) {
                    //alert('数据加载失败' + type);
                }
            });
        }
        
    });

    $("#btn-delete").click(function (e) {
        e.preventDefault();

        if (generatedOrder == true) {
            return false;
        }

        deleteItems();
    });

    $(".create-item-after-enter, #new-item").keypress(function (e) {
        if(window.event.keyCode == 13) {
            e.preventDefault();
            if (generatedOrder == true) {
                return false;
            }

            if(quotationId == '') {
                dialogAlert("#dialog-alert", {
                    textContent: '请先进行设置'
                });
                return false;
            }

            var factoryProductName = $("#new-item input[name=factoryProductName]").val();
            var factoryProductNo = $("#new-item input[name=factoryProductNo]").val();
            var factoryPrice = $("#new-item input[name=factoryPrice]").val();
            var contactNumber = $("#new-item input[name=contactNumber]").val();

            if (factoryProductName=="" && factoryProductNo=="") {
                dialogAlert("#dialog-alert", {
                    textContent: '品名和货号不能同时为空',
                    onClose: function () {
                        $("#new-item input[name=factoryProductName]").focus();
                    }
                });
                return false;
            }

            if (factoryPrice=="") {
                dialogAlert("#dialog-alert", {
                    textContent: '厂价不能为空',
                    onClose: function () {
                        $("#new-item input[name=factoryPrice]").focus();
                    }
                });
                return false;
            }

            if (contactNumber=="") {
                dialogAlert("#dialog-alert", {
                    textContent: '手机/电话不能为空',
                    onClose: function () {
                        $("#new-item input[name=contactNumber]").focus();
                    }
                });
                return false;
            }

            var newItem = {"quotationId": quotationId};
            $("#new-item input[type=text]").each(function () {
                newItem[$(this).attr("name")] = $(this).val();
            });

            createNewItem(newItem, function (draftId) {
                document.location.reload();
            });

            return false;
        }
    });

    $("[name=factoryProductName]").first().focus();

    getAccumulativeTotal();

    // $(document).keyup(function (e) {
    //     if (e.keyCode == 13) {
    //         alert("enter");
    //     }
    // });

});