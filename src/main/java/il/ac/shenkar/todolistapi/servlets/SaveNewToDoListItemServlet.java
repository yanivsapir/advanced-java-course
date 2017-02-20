package il.ac.shenkar.todolistapi.servlets;

import il.ac.shenkar.todolistapi.services.ToDoListItemService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveNewToDoListItemServlet extends HttpServlet {

	private ToDoListItemService toDoListItemService;

	public SaveNewToDoListItemServlet() {
		this.toDoListItemService = new ToDoListItemService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		String task = request.getParameter("task");
		toDoListItemService.saveNewToDoListItem(request, response, userId, task);
	}
}