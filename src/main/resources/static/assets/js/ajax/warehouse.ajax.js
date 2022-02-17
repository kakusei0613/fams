$("#search").click(function(){
    $("#table_body").empty();
    var queryObject = {
        pageNum:$("#pageNum").val(),
        pageSize:$("#pageSize").val(),
        keyword:$("#keyword").val()
    };
    $.post("/warehouse/query", queryObject, function (result) {
        var table_body = $("#table_body");
        var count = 1;
        for (var resultKey of result.list) {
            var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.innerText = count;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.id;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.position;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.comments;
            tr.append(td);

            td = document.createElement("td");
            td.append(createOperation("warehouse", resultKey.id));
            tr.append(td);
            table_body.append(tr);
            count++;
        }
        $("#total-div").innerText = "共有" + result.total + "条记录";
        updatePagination(result);
    })
});