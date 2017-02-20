package il.ac.shenkar.todolistapi.services;

import il.ac.shenkar.todolistapi.daos.ToDoListItemDao;
import il.ac.shenkar.todolistapi.daos.impl.ToDoListItemDaoImpl;
import il.ac.shenkar.todolistapi.models.ToDoListItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ysapir on 11/26/2016.
 */
public class ToDoListItemService {

	private ToDoListItemDao toDoListItemDao;

	public ToDoListItemService(){
		toDoListItemDao = ToDoListItemDaoImpl.getInstance();
	}

	public void updateToDoListItem(HttpServletRequest request, HttpServletResponse response, int itemId, String status) {
		toDoListItemDao.updateToDoListItemStatus(itemId,status);
	}

	public void deleteToDoListItem(HttpServletRequest request, HttpServletResponse response, int itemId) {
		toDoListItemDao.deleteToDoListItem(itemId);
	}

	public void saveNewToDoListItem(HttpServletRequest request, HttpServletResponse response, int userId, String task) {
		ToDoListItem toDoListItem = new ToDoListItem(userId,task,"newTask");
		int savedItemIndex = toDoListItemDao.saveNewToDoListItem(toDoListItem);
		try {
			response.getWriter().append(String.valueOf(savedItemIndex));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
