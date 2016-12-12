package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.virtela.enrollmentsystem.service.EnrollmentService;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

@WebServlet(urlPatterns = "/protected/enrollment", loadOnStartup = 1)
public class EnrollmentServlet extends HttpServlet {

	private static final long serialVersionUID = -6927792824352858041L;

	private EnrollmentService enrollmentService = ServiceRegistry.getInstance(EnrollmentService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("classes", enrollmentService.findAllClasses());
		session.setAttribute("classes", enrollmentService.findAllEnrolledClassesByStudentId(null));
		resp.sendRedirect(req.getContextPath() + "/protected/student/home.jsp");
	}

}
