var defaultOpts = {
    totalPages: [[${pageResult.pages}]] || 1,
    startPage: [[${pageResult.pageNum}]] || 1,
    visiblePages: 2,
    first: "首页",
    prev: "&laquo;",
    next: "&raquo;",
    last: "尾页",
    activeClass: "am-active",
    disabledClass: "am-disabled",
    initiateStartPageClick: false,
    onPageClick: function (event, page) {
        $("#pageNum").val(page);
        //AJAX查询记录
        $.post("/order/state",
            {
                pageNum : $("#pageNum").val(),
                orderId : $("#id").val()
            } , function (result) {
                var table_body = $("#table_body");
                table_body.empty();
                var count = 1;
                for (var resultKey of result.list) {
                    var tr = document.createElement("tr");
                    var td = document.createElement("td");
                    td.innerText = count;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.orderId;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.state;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.occurrenceTime;
                    tr.append(td);
                    td = document.createElement("td");
                    td.innerText = resultKey.operator;
                    tr.append(td);
                    table_body.append(tr);
                    count++;
                }
                //更新分页
                $("#pagination").twbsPagination('destroy');
                $("#pagination").twbsPagination($.extend({}, defaultOpts,
                    {
                        startPage: page || 1
                    })
                );
            })
    }
};
$("#pagination").twbsPagination(defaultOpts);