<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<%@page import="il.ac.shenkar.todolistapi.models.User" %>
<%@ page import="il.ac.shenkar.todolistapi.models.ToDoListItem" %>
<%@ page import="il.ac.shenkar.todolistapi.daos.ToDoListItemDao" %>
<%@ page import="il.ac.shenkar.todolistapi.daos.impl.ToDoListItemDaoImpl" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <title>Result Page</title>
</head>
<body>

<button type="button" class="btn btn-default" onclick="javascript:location.href='logout.jsp'" style="float: right; margin-right: 5%">
    <span class="glyphicon glyphicon-log-out"></span> Logout
</button>

<div class="container">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <h3 style="text-align: center;">
        Welcome <%= user.getFirstName() + " " + user.getLastName() + " (User ID: "%><span id="userId"><%= user.getId()%></span>)
    </h3>
    <br/>
    <div style="text-align: center;">
        <input style="width: 20%; display: inline-block" class="form-control" id="task" type="text" placeholder="Type Task Description"/>
        <button class="btn btn-primary" onclick="saveNewToDoListItem()">Insert New Task</button>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th class="text-center">Task ID</th>
            <th class="text-center">Username</th>
            <th class="text-center">Task Name</th>
            <th class="text-center">Task Status</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody id="itemRows">
            <%
					 ToDoListItemDao toDoListItemDao = ToDoListItemDaoImpl.getInstance();
					 List<ToDoListItem> toDoListItemslist = toDoListItemDao.getAllToDoListItemsByUserId(user.getId());
					 for (ToDoListItem toDoListItem : toDoListItemslist) {
				 %>
        <tr id="itemRow<%= toDoListItem.getId()%>">
            <td class="text-center"><%=toDoListItem.getId()%>
            </td>
            <td class="text-center"><%=user.getUserId()%>
            </td>
            <td class="text-center"><%=toDoListItem.getTask()%>
            </td>
            <td>
                <select class="form-control" id="item<%= toDoListItem.getId()%>">
                    <option <%= toDoListItem.getStatus().equals("inProgress") ? "selected" : "" %> value="inProgress">inProgress</option>
                    <option <%= toDoListItem.getStatus().equals("done") ? "selected" : "" %> value="done">done</option>
                    <option <%= toDoListItem.getStatus().equals("newTask") ? "selected" : "" %> value="newTask">newTask</option>
                </select>
            </td>
            <td>
                <button type="button" class="btn btn-warning" onclick="updateToDoListItem('<%= toDoListItem.getId()%>')">
                    <span class="glyphicon glyphicon-saved"></span> Update
                </button>
                <button type="button" class="btn btn-danger" onclick="deleteToDoListItem('<%= toDoListItem.getId()%>')">
                    <span class="glyphicon glyphicon-floppy-remove"></span> Delete
                </button>
            </td>
        </tr>
            <%}%>
        <tbody>
    </table>
    <br/>
</div>
<style>
    #itemRows td{
        text-align: center;
    }

    #itemRows select{
        width: 50%;
        margin: 0 auto;
    }
</style>
<script>
    function updateToDoListItem(itemId) {
        var status = document.getElementById("item" + itemId).value;

        return $.ajax({
            url: 'UpdateToDoListItemServlet',
            type: 'POST',
            cache: false,
            data: {itemId: itemId, status: status}
        }).done(function (data) {

        });
    }

    function deleteToDoListItem(itemId) {
        return $.ajax({
            url: 'DeleteToDoListItemServlet',
            type: 'POST',
            cache: false,
            data: {itemId: itemId}
        }).done(function (data) {
            $("#itemRow" + itemId).remove();
            console.log("i did it");
        });
    }

    function saveNewToDoListItem() {
        var userId = document.getElementById("userId").innerHTML;
        var task = document.getElementById("task").value;
        return $.ajax({
            url: 'SaveNewToDoListItemServlet',
            type: 'POST',
            cache: false,
            data: {userId: userId, task: task}
        }).done(function (data) {
            var tr = document.createElement("TR");
            tr.id = "itemRow" + data;
            var td = document.createElement("TD");
            var t = document.createTextNode(data);
            td.appendChild(t);
            tr.appendChild(td);
            td = document.createElement("TD");
            t = document.createTextNode(userId);
            td.appendChild(t);
            tr.appendChild(td);
            td = document.createElement("TD");
            t = document.createTextNode(task);
            td.appendChild(t);
            tr.appendChild(td);
            td = document.createElement("TD");
            td.innerHTML = "<select class='form-control' id='item" + data + "'><option value='inProgress'>inProgress</option><option value='done'>done</option>" +
                "<option selected value='newTask'>newTask</option></select>"
            tr.appendChild(td);
            td = document.createElement("TD");

            td.innerHTML = "<button type='button' class='btn btn-warning' onclick='updateToDoListItem("+data+")'><span class='glyphicon " +
                "glyphicon-saved'></span> Update</button> <button type='button' class='btn btn-danger' onclick='deleteToDoListItem("+data+")'> " +
                "<span class='glyphicon glyphicon-floppy-remove'></span> Delete</button>";
            tr.appendChild(td);
            $("#itemRows").append(tr);
        });
    }
</script>
</body>
</html>
