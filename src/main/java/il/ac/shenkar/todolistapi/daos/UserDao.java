package il.ac.shenkar.todolistapi.daos;

import il.ac.shenkar.todolistapi.hibernatetransactionmanager.TransactionLogic;
import il.ac.shenkar.todolistapi.models.ToDoListItem;
import il.ac.shenkar.todolistapi.models.User;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public interface UserDao {

	User getUserByUserId(String userId);
	List<User> getAllUsers();
	boolean isUserExists(User user);
	String saveNewUser(User user);
	boolean deleteUserByUserId(String userId);
}
