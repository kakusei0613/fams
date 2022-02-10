$("#search").click(function(){
    $("#table_body").empty();
    var queryObject = {
        pageNum:$("#pageNum").val(),
        pageSize:$("#pageSize").val(),
        keyword:$("#keyword").val(),
        department:$("#department-select").val(),
        employeeState:$("#employeeState-select").val(),
        gender:$("#gender-select").val()
    };
    $.post("/employee/query", queryObject, function (result) {
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
            td.innerText = resultKey.birthday;
            tr.append(td);
            td = document.createElement("td");
            if (resultKey.gender == true)
                td.innerText = "男";
            else
                td.innerText = "女";
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.phone;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.email;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.address;
            tr.append(td);
            td = document.createElement("td");
            if(resultKey.admin == true)
                td.innerText = "是";
            else
                td.innerText = "否";
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.department.name;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.hireDate;
            tr.append(td);
            td = document.createElement("td");
            td.innerText = resultKey.employeeState.name;
            tr.append(td);
            td = document.createElement("td");
            td.append(createOperation(1));
            tr.append(td);
            table_body.append(tr);
            count++;
        }
    })
});