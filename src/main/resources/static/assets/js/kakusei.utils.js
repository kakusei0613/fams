function createOperation(id) {
    // var td = document.createElement("td");
    // var div = document.createElement("div");
    // div.className = "tpl-table-black-operation";
    // var a = document.createElement("a");
    // var i = document.createElement("i");
    // i.className = "am-icon-pencil";
    // a.append(i); a.append(" 编辑");
    // div.append(a);
    // a = document.createElement("a");
    // i = document.createElement("i");
    // a.className = "tpl-table-black-operation-del";
    // i.className = "am-icon-trash";
    // a.append(i); a.append(" 删除");
    // div.append(a);
    // td.append(div);
    // return td;
    var tempNode = document.createElement("div");
    tempNode.innerHTML = "<td><div class='tpl-table-black-operation'><a href='/employee/" + id + "'><i class='am-icon-pencil'></i> 编辑</a>&nbsp;<a href='/employee/delete/" + id + "'class='tpl-table-black-operation-del'><i class='am-icon-trash'></i> 删除</a></div></td>";
    return tempNode.firstChild;
}