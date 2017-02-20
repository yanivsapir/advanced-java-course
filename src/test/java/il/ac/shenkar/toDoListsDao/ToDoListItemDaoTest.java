package il.ac.shenkar.toDoListsDao;

import il.ac.shenkar.todolistapi.daos.ToDoListItemDao;
import il.ac.shenkar.todolistapi.daos.impl.ToDoListItemDaoImpl;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.HibernateSessionConfigurer;
import il.ac.shenkar.todolistapi.models.ToDoListItem;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public class ToDoListItemDaoTest {

	private static SessionFactory factory;
	private ToDoListItemDao toDoListItemDao;
	@Before
	public void init(){
		toDoListItemDao = ToDoListItemDaoImpl.getInstance();
		factory = HibernateSessionConfigurer.getSessionFactory();
	}


	@Test
	public void getToDoListItemByItemIdTest(){
		ToDoListItem itemByItemId = toDoListItemDao.getItemByItemId(1);
		Assert.assertTrue(itemByItemId.getId() == 1);
	}

	@Test
	public void getAllToDoListItemsByUserIdTest(){
		List<ToDoListItem> allUserToDoListItems = toDoListItemDao.getAllToDoListItemsByUserId(2);
		Assert.assertNotNull(allUserToDoListItems);
	}

	@Test
	public void getAllToDoListItemsTest(){
		List<ToDoListItem> allToDoListItems = toDoListItemDao.getAllToDoListItems();
		Assert.assertNotNull(allToDoListItems);
	}

	@Test
	public void saveNewToDoListItemTest(){
		ToDoListItem toDoListItem = new ToDoListItem(2,"sleep","inProgress");
		int savedItemIndex = toDoListItemDao.saveNewToDoListItem(toDoListItem);
		List<ToDoListItem> allToDoListItemsByUserId = toDoListItemDao.getAllToDoListItemsByUserId(2);
		Assert.assertTrue(allToDoListItemsByUserId.contains(toDoListItem));
		toDoListItemDao.deleteToDoListItem(savedItemIndex);
	}

	@Test
	public void updateToDoListItemStatusTest(){
		ToDoListItem toDoListItem = new ToDoListItem(2,"sleep","inProgress");
		int savedItemIndex = toDoListItemDao.saveNewToDoListItem(toDoListItem);
		toDoListItem.setStatus("done");
		toDoListItemDao.updateToDoListItemStatus(savedItemIndex, "done");
		List<ToDoListItem> allToDoListItemsByUserId = toDoListItemDao.getAllToDoListItemsByUserId(2);
		Assert.assertTrue(allToDoListItemsByUserId.contains(toDoListItem));
		toDoListItemDao.deleteToDoListItem(savedItemIndex);
	}

	@Test
	public void deleteToDoListItemTest(){
		ToDoListItem toDoListItem = new ToDoListItem(2,"sleep","inProgress");
		int savedItemIndex = toDoListItemDao.saveNewToDoListItem(toDoListItem);
		toDoListItemDao.deleteToDoListItem(savedItemIndex);
		List<ToDoListItem> allToDoListItemsByUserId = toDoListItemDao.getAllToDoListItemsByUserId(2);
		Assert.assertFalse(allToDoListItemsByUserId.contains(toDoListItem));
	}
}
