package il.ac.shenkar.todolistapi.services;

import java.io.IOException;

import il.ac.shenkar.todolistapi.daos.UserDao;
import il.ac.shenkar.todolistapi.daos.impl.UserDaoImpl;
import il.ac.shenkar.todolistapi.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginService {

	private UserDao userDao;

	public LoginService() {
		this.userDao = UserDaoImpl.getInstance();
	}

	public void performUserAuthentication(HttpServletRequest request, HttpServletResponse response, String userId, String password) throws IOException {
		boolean isAuthenticated = isUserAuthenticated(userId, password);
		if (isAuthenticated == true) {
			User user = userDao.getUserByUserId(userId);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	private boolean isUserAuthenticated(String userId, String password) {
		User user = userDao.getUserByUserId(userId);
		if (user != null && user.getUserId().equals(userId) && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
