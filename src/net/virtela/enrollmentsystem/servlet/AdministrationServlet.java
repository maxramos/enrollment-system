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
import net.virtela.enrollmentsystem.service.UserService;

@WebServlet(urlPatterns = "/protected/administration", loadOnStartup = 1)
public class AdministrationServlet extends HttpServlet {

	private static final long serialVersionUID = -8488608435479164803L;

	private EnrollmentService enrollmentService = ServiceRegistry.getInstance(EnrollmentService.class);
	private UserService userService = ServiceRegistry.getInstance(UserService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("currentUser", userService.findUserByUsername(req.getRemoteUser()));
		session.setAttribute("subjects", enrollmentService.findAllSubjects());
		session.setAttribute("schedules", enrollmentService.findAllSchedules());
		session.setAttribute("teachers", enrollmentService.findAllTeachers());
		session.setAttribute("classes", enrollmentService.findAllClasses());
		resp.sendRedirect(req.getContextPath() + "/protected/admin/classes.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Long subjectId = Long.parseLong(req.getParameter("subject"));
		Long scheduleId = Long.parseLong(req.getParameter("schedule"));
		Long teacherId = Long.parseLong(req.getParameter("teacher"));

		boolean added = enrollmentService.addClazz(subjectId, scheduleId, teacherId);

		if (added) {
			session.setAttribute("classes", enrollmentService.findAllClasses());
			session.setAttribute("message", "Class successfully added.");
		} else {
			session.setAttribute("message", "Failed to add class. Class already exists.");
		}

		resp.sendRedirect(req.getContextPath() + "/protected/admin/classes.jsp");
	}

}
