var applicationDefaultOpts = {
    totalPages: $("#ous_pages").val() || 1,
    startPage: 1,
    visiblePages: 2,
    first: "首页",
    prev: "&laquo;",
    next: "&raquo;",
    last: "尾页",
    activeClass: "am-active",
    disabledClass: "am-disabled",
    initiateStartPageClick: false,
    onPageClick: function (event, page) {
        $("#ous_pageNum").val(page);
        //AJAX查询记录
        $.post("/order/used",
            {
                pageNum : $("#ous_pageNum").val(),
                orderId : $("#id").val()
            } , function (result) {
                var ous_table_body = $("#ous_table_body");
                ous_table_body.empty();
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
                    td.innerText = resultKey.stock.warehouse.name;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.stock.area;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.quantity;
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
                    ous_table_body.append(tr);
                    count++;
                }
                //更新分页
                $("#ous_pagination").twbsPagination('destroy');
                $("#ous_pagination").twbsPagination($.extend({}, applicationDefaultOpts,
                    {
                        startPage: page || 1
                    })
                );
            })
    }
};
$("#ous_pagination").twbsPagination(applicationDefaultOpts);