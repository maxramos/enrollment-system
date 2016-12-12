package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.virtela.enrollmentsystem.service.SecurityService;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

@WebServlet(urlPatterns = "/protected/redirect", loadOnStartup = 1)
public class RedirectServlet extends HttpServlet {

	private static final long serialVersionUID = -1383158384273345047L;

	private SecurityService securityService = ServiceRegistry.getInstance(SecurityService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("currentUser", securityService.findUserByUsername(req.getRemoteUser()));

		if (req.isUserInRole("admin")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/administration");
			dispatcher.forward(req, resp);
		} else if (req.isUserInRole("student")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/enrollment");
			dispatcher.forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}
