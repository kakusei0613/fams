// 默认配置
var defaultOpts = {
    totalPages: $("#incompleteOrders_pages").val() || 1,
    startPage: $("#incompleteOrders_pageNum").val() || 1,
    visiblePages: 5,
    prev: "&laquo;",
    next: "&raquo;",
    activeClass: "am-active",
    disabledClass: "am-disabled",
    first: "",
    last: "",
    initiateStartPageClick: false,
    onPageClick: function (event,page) {
        $("#incompleteOrders_pageNum").val(page);
        $.post("/order/incompleteOrders", {pageNum:page}, function (result) {
            var table_body = $("#incompleteOrders_table_body");
            table_body.empty();
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
                td.innerText = resultKey.customer.name;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.ddl;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.createTime;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.creator.name;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.state.name;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.processTime;
                tr.append(td);
                td = document.createElement("td");
                td.innerText = resultKey.comments;
                tr.append(td);
                td = document.createElement("td");
                td.append("Nicess");
                tr.append(td);
                table_body.append(tr);
                count++;
            }
            $("#total-div").innerText = "共有" + result.total + "条记录";
            updatePagination(result);
        })
    }
}
$("#incompleteOrders_Pagination").twbsPagination(defaultOpts);
//创建AJAX后列表的编辑、删除按钮
function createOperation(desc, id) {
    var tempNode = document.createElement("div");
    tempNode.innerHTML = "<td><div class='tpl-table-black-operation'><a href='/" + desc + "/" + id + "'><i class='am-icon-pencil'></i> 编辑</a>&nbsp;<a href='/" + desc + "/delete/" + id + "' class='tpl-table-black-operation-del'><i class='am-icon-trash'></i> 删除</a></div></td>";
    return tempNode.firstChild;
}
//AJAX查询后更新页码表
function updatePagination(result) {
    $("#incompleteOrders_Pagination").twbsPagination('destroy');
    $("#incompleteOrders_Pagination").twbsPagination($.extend({}, defaultOpts,
        {
            totalPages: result.pages || 1,
            startPage: result.pageNum || 1,
        })
    );
}