package il.ac.shenkar.todolistapi.daos;

import il.ac.shenkar.todolistapi.models.ToDoListItem;

import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public interface ToDoListItemDao {

	List<ToDoListItem> getAllToDoListItemsByUserId(int userId);
	List<ToDoListItem> getAllToDoListItems();
	ToDoListItem getItemByItemId(int itemId);
	int saveNewToDoListItem(ToDoListItem toDoListItem);
	boolean deleteToDoListItem(int itemId);
	boolean updateToDoListItemStatus(int itemId, String status);
}
