package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.virtela.enrollmentsystem.service.ServiceRegistry;
import net.virtela.enrollmentsystem.service.UserService;

@WebServlet(urlPatterns = "/protected/tuition", loadOnStartup = 1)
public class TuitionServlet extends HttpServlet {

	private static final long serialVersionUID = -1548763424047374757L;

	private UserService userService = ServiceRegistry.getInstance(UserService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
