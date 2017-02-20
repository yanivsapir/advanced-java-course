package il.ac.shenkar.todolistapi.daos.impl;

import il.ac.shenkar.todolistapi.daos.ToDoListItemDao;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.HibernateTransactionManager;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.TransactionLogic;
import il.ac.shenkar.todolistapi.models.ToDoListItem;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public class ToDoListItemDaoImpl implements ToDoListItemDao {

	private static ToDoListItemDao toDoListItemDao;
	private static HibernateTransactionManager hibernateTransactionManager;
	private ToDoListItemDaoImpl(){}

	public static ToDoListItemDao getInstance(){
		if(toDoListItemDao == null) {
			toDoListItemDao = new ToDoListItemDaoImpl();
			hibernateTransactionManager = new HibernateTransactionManager();
		}
		return toDoListItemDao;
	}

	@Override public List<ToDoListItem> getAllToDoListItems() {
		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				return session.createQuery("from ToDoListItem").list();
			}
		};
		return (List<ToDoListItem>) hibernateTransactionManager.performTransaction(transactionLogic);
	}

	@Override public List<ToDoListItem> getAllToDoListItemsByUserId(int userId) {
		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				int userId = (int)args[0];
				return session.createQuery("from ToDoListItem where userId = " + userId).list();
			}
		};
		return (List<ToDoListItem>) hibernateTransactionManager.performTransaction(transactionLogic, userId);
	}

	@Override public ToDoListItem getItemByItemId(int itemId) {
		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				int itemId = (int)args[0];
				Query query = session.createQuery("from ToDoListItem where id = " + itemId);
				return query.uniqueResult();
			}
		};
		return (ToDoListItem) hibernateTransactionManager.performTransaction(transactionLogic,itemId);
	}

	@Override public int saveNewToDoListItem(ToDoListItem toDoListItem) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				ToDoListItem toDoListItem = (ToDoListItem) args[0];
				session.saveOrUpdate(toDoListItem);
				return true;
			}
		};
		hibernateTransactionManager.performTransaction(transactionLogic, toDoListItem);
		return toDoListItem.getId();
	}

	@Override public boolean deleteToDoListItem(int itemId) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				int itemId = (int) args[0];
				ToDoListItem itemByToDoListItemId = getItemByItemId(itemId);
				session.delete(itemByToDoListItemId);
				return true;
			}
		};
		return (boolean) hibernateTransactionManager.performTransaction(transactionLogic, itemId);
	}

	@Override public boolean updateToDoListItemStatus(int itemId, String status) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				int itemId = (int) args[0];
				String status = (String) args[1];
				ToDoListItem itemByToDoListItemId = getItemByItemId(itemId);
				itemByToDoListItemId.setStatus(status);
				session.saveOrUpdate(itemByToDoListItemId);
				return true;
			}
		};
		return (boolean) hibernateTransactionManager.performTransaction(transactionLogic, itemId, status);
	}
}
