package il.ac.shenkar.todolistapi.servlets;

import il.ac.shenkar.todolistapi.services.ToDoListItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateToDoListItemServlet extends HttpServlet {

	private ToDoListItemService toDoListItemService;

	public UpdateToDoListItemServlet() {
		this.toDoListItemService = new ToDoListItemService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String status = request.getParameter("status");
		toDoListItemService.updateToDoListItem(request, response, itemId, status);
	}
}