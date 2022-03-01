$("#search").click(function(){
    $("#table_body").empty();
    var queryObject = {
        pageNum:$("#pageNum").val(),
        pageSize:$("#pageSize").val(),
        keyword:$("#keyword").val(),
        typeId:$("#type-select").val(),
        warehouseId:$("#warehouse-select").val()
    };
    $.post("/stock/query", queryObject, function (result) {
        var table_body = $("#table_body");
        var stockIds = [];
        $.each($("#apply_table_body tr"), function (index, tr) {
            var id = $(this).find("td").eq(0).html();
            stockIds.push(id);
        });
        for (var resultKey of result.list) {
            var temp = '' + resultKey.id;
            if ($.inArray(temp, stockIds) > -1) {
                continue;
            }
            var tr = document.createElement("tr");
            td = document.createElement("td");
            td.innerText = resultKey.id;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.material.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.material.type.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.material.parameter;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.warehouse.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.area;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.quantity;
            tr.append(td);
            tr.id = "tr-" + resultKey.id;
            table_body.append(tr);
        }
        var TaskType = '';
        $("#table_body tr").click(function () {
            TaskType = $(this).find("td").eq(0).html();
            $("#quantity-prompt").modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $("#tr-" + TaskType).append($("<td>" + e.data + "</td>"));
                    $("#tr-" + TaskType).appendTo($("#apply_table_body"));
                    $("#table_body tr").remove("#tr-" + TaskType);
                    $("#prompt-input").val('');
                    //清空click函数，重新写入
                    $("#tr-" + TaskType).off("click");
                    $("#tr-" + TaskType).click(function () {
                        TaskType = $(this).find("td").eq(0).html();
                        $("#apply_table_body tr").remove("#tr-" + TaskType);
                        $("#search").click();
                    });
                },
                onCancel: function (e) {

                }
            });
        })
        $("#total-div").innerText = "共有" + result.total + "条记录";
        updatePagination(result);
    })
});
