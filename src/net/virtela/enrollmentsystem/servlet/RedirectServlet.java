package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/protected/redirect", loadOnStartup = 1)
public class RedirectServlet extends HttpServlet {

	private static final long serialVersionUID = -1383158384273345047L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.isUserInRole("admin")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/admin/administration");
			dispatcher.forward(req, resp);
		} else if (req.isUserInRole("student")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/student/enrollment");
			dispatcher.forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}
