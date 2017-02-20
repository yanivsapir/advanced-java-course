package il.ac.shenkar.todolistapi.services;

import il.ac.shenkar.todolistapi.daos.UserDao;
import il.ac.shenkar.todolistapi.daos.impl.UserDaoImpl;
import il.ac.shenkar.todolistapi.models.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterService {

	private UserDao userDao;

	public RegisterService(){
		userDao = UserDaoImpl.getInstance();
	}

	public void register(HttpServletResponse response, User user) {
		PrintWriter out = null;
		try {
			boolean userExists = userDao.isUserExists(user);
			out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Successful</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");
			if (!userExists) {
				userDao.saveNewUser(user);
				out.println("<h1>Thanks for Registering with us :</h1>");
				out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");
			} else {
				out.println("<h1>Registration Failed</h1>");
				out.println("To try again<a href=register.jsp>Click here</a>");
			}
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}