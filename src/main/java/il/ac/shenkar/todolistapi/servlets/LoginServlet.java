package il.ac.shenkar.todolistapi.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.shenkar.todolistapi.services.LoginService;

public class LoginServlet extends HttpServlet {

	private LoginService loginService;

	public LoginServlet() {
		this.loginService = new LoginService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		loginService.performUserAuthentication(request, response, userId, password);
	}
}