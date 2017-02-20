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
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <title>Result Page</title>
</head>
<body>
<input id="task" type="text"/>
<button onclick="saveNewToDoListItem()">Insert New Item</button>
<center>
    <div id="container">
        <h1>Result Page</h1>
        <b>This is Sample Result Page</b><br/>
            <%=new Date()%></br>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <b>Welcome <%= user.getFirstName() + " " + user.getLastName() + " ( ID: "%><span id="userId"><%= user.getId()%></span> )
        </b>
        <br/>
        <a href="logout.jsp">Logout</a>
        </p>
        <table>
            <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Task</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody id="itemRows">
                <%
					 ToDoListItemDao toDoListItemDao = ToDoListItemDaoImpl.getInstance();
					 List<ToDoListItem> toDoListItemslist = toDoListItemDao.getAllToDoListItemsByUserId(user.getId());
					 for (ToDoListItem toDoListItem : toDoListItemslist) {
				 %>
            <tr id="itemRow<%= toDoListItem.getId()%>">
                <td><%=toDoListItem.getId()%>
                </td>
                <td><%=toDoListItem.getUserId()%>
                </td>
                <td><%=toDoListItem.getTask()%>
                </td>
                <td>
                    <select id="item<%= toDoListItem.getId()%>">
                        <option <%= toDoListItem.getStatus().equals("inProgress") ? "selected" : "" %> value="inProgress">inProgress</option>
                        <option <%= toDoListItem.getStatus().equals("done") ? "selected" : "" %> value="done">done</option>
                        <option <%= toDoListItem.getStatus().equals("newTask") ? "selected" : "" %> value="newTask">newTask</option>
                    </select>
                </td>
                <td>
                    <a onclick="updateToDoListItem('<%= toDoListItem.getId()%>')">hey</a>
                </td>
                <td>
                    <a onclick="deleteToDoListItem('<%= toDoListItem.getId()%>')">hey</a>
                </td>
            </tr>
                <%}%>
            <tbody>
        </table>
        <br/>
    </div>
    <script>
        function updateToDoListItem(itemId) {
            var status = document.getElementById("item" + itemId).value;

            return $.ajax({
                url: 'UpdateToDoListItemServlet',
                type: 'POST',
                cache: false,
                data: {itemId: itemId, status: status}
            }).done(function (data) {
                console.log("i did it");
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
                td.innerHTML = "<select id='item"+data+"'><option value='inProgress'>inProgress</option><option value='done'>done</option>" +
                    "<option selected value='newTask'>newTask</option></select>"
                tr.appendChild(td);
                td = document.createElement("TD");
                var button = document.createElement("a");
                t = document.createTextNode("hey");
                button.appendChild(t);
                button.addEventListener("click", function(){ updateToDoListItem(data);});
                td.appendChild(button);
                tr.appendChild(td);
                td = document.createElement("TD");
                button = document.createElement("a");
                t = document.createTextNode("hey");
                button.appendChild(t);
                button.addEventListener("click",function(){ deleteToDoListItem(data);});
                td.appendChild(button);
                tr.appendChild(td);
                $("#itemRows").append(tr);
            });
        }
    </script>
</center>
</body>
</html>
