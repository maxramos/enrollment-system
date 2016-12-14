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

@WebServlet(urlPatterns = "/protected/student/tuition", loadOnStartup = 1)
public class TuitionServlet extends HttpServlet {

	private static final long serialVersionUID = -1548763424047374757L;

	private EnrollmentService enrollmentService = ServiceRegistry.getInstance(EnrollmentService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Student student = (Student) session.getAttribute("currentUser");
		session.setAttribute("tuition", enrollmentService.calculateTuition(student));
		resp.sendRedirect(req.getContextPath() + "/protected/student/tuition.jsp");
	}

}
