package il.ac.shenkar.todolistapi.servlets;

import il.ac.shenkar.todolistapi.services.ToDoListItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteToDoListItemServlet extends HttpServlet {

	private ToDoListItemService toDoListItemService;

	public DeleteToDoListItemServlet() {
		this.toDoListItemService = new ToDoListItemService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		toDoListItemService.deleteToDoListItem(request, response, itemId);
	}
}