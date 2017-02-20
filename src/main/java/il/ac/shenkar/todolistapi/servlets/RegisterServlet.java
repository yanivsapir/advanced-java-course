package il.ac.shenkar.todolistapi.servlets;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.shenkar.todolistapi.models.User;
import il.ac.shenkar.todolistapi.services.RegisterService;

public class RegisterServlet extends HttpServlet {

	private RegisterService registerService;

	public RegisterServlet() {
		registerService = new RegisterService();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		registerService.register(response, new User(firstName, middleName, lastName, email, userId, password, new ArrayList<>()));
	}
}