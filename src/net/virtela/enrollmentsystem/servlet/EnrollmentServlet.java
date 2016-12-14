package net.virtela.enrollmentsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.virtela.enrollmentsystem.model.Student;
import net.virtela.enrollmentsystem.service.EnrollmentService;
import net.virtela.enrollmentsystem.service.ServiceRegistry;
import net.virtela.enrollmentsystem.service.UserService;

@WebServlet(urlPatterns = "/protected/student/enrollment", loadOnStartup = 1)
public class EnrollmentServlet extends HttpServlet {

	private static final long serialVersionUID = -6927792824352858041L;

	private EnrollmentService enrollmentService = ServiceRegistry.getInstance(EnrollmentService.class);
	private UserService userService = ServiceRegistry.getInstance(UserService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("currentUser", userService.findStudentByUsername(req.getRemoteUser()));
		session.setAttribute("classes", enrollmentService.findAllClasses());
		resp.sendRedirect(req.getContextPath() + "/protected/student/home.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Student student = (Student) session.getAttribute("currentUser");
		Long clazzId = Long.parseLong(req.getParameter("clazz"));

		boolean enrolled = enrollmentService.enrollClazz(student, clazzId);

		if (enrolled) {
			session.setAttribute("message", "Class successfully enrolled.");
		} else {
			session.setAttribute("message", "Failed to enroll class. Class already enrolled.");
		}

		resp.sendRedirect(req.getContextPath() + "/protected/student/home.jsp");
	}

}
