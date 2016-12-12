package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/logout", loadOnStartup = 1)
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 6552083898635162124L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.logout();
		resp.sendRedirect(req.getContextPath() + "/protected/redirect");
	}

}
