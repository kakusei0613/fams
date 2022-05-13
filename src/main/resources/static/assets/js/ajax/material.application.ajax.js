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
        // var stockIds = [];
        // $.each($("#apply_table_body tr"), function (index, tr) {
        //     var id = $(this).find("td").eq(0).html();
        //     stockIds.push(id);
        // });
        // console.log(stockIds);
        // console.log("In ajax list size:" + result.list.length);
        for (var resultKey of result.list) {
            var temp = "" + resultKey.id;
            // if ($.inArray(temp, stockIds) > -1) {
            //     continue;
            // }
            // console.log(temp + "不存在");
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
            console.log("添加进table");
            table_body.append(tr);
        }
        setTableTrClick();
        $("#total-div").innerText = "共有" + result.total + "条记录";
        updatePagination(result);
    })
});
