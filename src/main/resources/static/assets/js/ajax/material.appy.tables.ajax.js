$("#search").click(function(){
    $("#table_body").empty();
    var queryObject = {
        pageNum:$("#pageNum").val(),
        pageSize:$("#pageSize").val(),
        keyword:$("#keyword").val(),
        employeeId:$("#employee-select").val(),
        stateId:$("#state-select").val(),
        orderId:$("#orderId").val()
    };
    $.post("/material/application/query", queryObject, function (result) {
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
            td.innerText = resultKey.order.id;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.order.customer.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.employee.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.stock.id;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.stock.material.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.stock.material.type.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.stock.warehouse.name
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.stock.area;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.quantity;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.state.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.applicationTime;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.operator.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.operationTime;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.comments;
            tr.append(td);
            table_body.append(tr);
            count++;
        }
        $("#total-div").innerText = "共有" + result.total + "条记录";
        updatePagination(result);
    })
});