package il.ac.shenkar.todolistapi.daos.impl;

import il.ac.shenkar.todolistapi.daos.UserDao;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.TransactionLogic;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.HibernateTransactionManager;
import il.ac.shenkar.todolistapi.models.ToDoListItem;
import il.ac.shenkar.todolistapi.models.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public class UserDaoImpl implements UserDao{
	private static UserDao userDao;
	private static HibernateTransactionManager hibernateTransactionManager;
	private UserDaoImpl(){}

	public static UserDao getInstance(){
		if(userDao == null) {
			userDao = new UserDaoImpl();
			hibernateTransactionManager = new HibernateTransactionManager();
		}
		return userDao;
	}

	@Override public User getUserByUserId(String userId) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				String userId = (String)args[0];
				Query query = session.createQuery("from User where userId = '" + userId + "'");
				return query.uniqueResult();
			}
		};
		return (User) hibernateTransactionManager.performTransaction(transactionLogic,userId);
	}

	@Override public List<User> getAllUsers(){
		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				return session.createQuery("from User").list();
			}
		};
		return (List<User>) hibernateTransactionManager.performTransaction(transactionLogic);
	}

	@Override public boolean isUserExists(User user) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				String userId = (String)args[0];
				Query query = session.createQuery("from User where userId='" + userId + "'");
				if(query.uniqueResult() != null)
					return true;
				return false;
			}
		};
		return (boolean) hibernateTransactionManager.performTransaction(transactionLogic, user.getUserId());
	}

	@Override public String saveNewUser(User user) {
		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				User user = (User)args[0];
				session.saveOrUpdate(user);
				return user.getUserId();
			}
		};
		return (String) hibernateTransactionManager.performTransaction(transactionLogic, user);
	}

	@Override public boolean deleteUserByUserId(String userId) {

		TransactionLogic transactionLogic = new TransactionLogic() {
			@Override public Object performTransactionLogic(Session session, Object... args) {
				String userId = (String) args[0];
				User userByUserId = getUserByUserId(userId);
				session.delete(userByUserId);
				return true;
			}
		};
		return (boolean) hibernateTransactionManager.performTransaction(transactionLogic, userId);
	}

}
