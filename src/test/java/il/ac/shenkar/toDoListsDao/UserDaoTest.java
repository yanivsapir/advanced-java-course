package il.ac.shenkar.toDoListsDao;

import il.ac.shenkar.todolistapi.daos.UserDao;
import il.ac.shenkar.todolistapi.daos.impl.UserDaoImpl;
import il.ac.shenkar.todolistapi.hibernatetransactionmanager.HibernateSessionConfigurer;
import il.ac.shenkar.todolistapi.models.User;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysapir on 11/26/2016.
 */
public class UserDaoTest {

	private static SessionFactory factory;
	private UserDao userDao;

	@Before
	public void init(){
		userDao = UserDaoImpl.getInstance();
		factory = HibernateSessionConfigurer.getSessionFactory();
	}


	@Test
	public void getUserByUserIdTest(){
		User user = userDao.getUserByUserId("ysapir");
		Assert.assertEquals("ysapir",user.getUserId());
	}


	@Test
	public void getAllUsersTest(){
		List<User> allUsers = userDao.getAllUsers();
		Assert.assertNotNull(allUsers);
	}

	@Test
	public void isUserExistsTest(){
		User user = new User("yaniv","itzhak","sapir","yanivsapir4@gmail.com",
				"yanivsapir","1234",new ArrayList<>());
		Assert.assertTrue(userDao.isUserExists(user));
		user.setUserId("shlomi");
		Assert.assertFalse(userDao.isUserExists(user));
	}

	@Test
	public void saveNewUserTest(){
		User user = new User("yan","itz","sap","yaniv_sapir@walla.com",
				"yansap","1234",new ArrayList<>());
		String savedItemIndex = userDao.saveNewUser(user);
		Assert.assertEquals(user.getUserId(),userDao.getUserByUserId(savedItemIndex).getUserId());
		userDao.deleteUserByUserId(savedItemIndex);
	}

	@Test
	public void deleteUserByUserIdTest(){
		User user = new User("yan","itz","sap","yaniv_sapir@walla.com",
				"yansap","1234",new ArrayList<>());
		String savedItemIndex = userDao.saveNewUser(user);
		userDao.deleteUserByUserId(savedItemIndex);
		Assert.assertNull(userDao.getUserByUserId(savedItemIndex));
	}
}
