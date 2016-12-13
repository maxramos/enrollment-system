package net.virtela.enrollmentsystem.service;

import java.util.List;

import net.virtela.enrollmentsystem.dao.ClazzDao;
import net.virtela.enrollmentsystem.dao.UserDao;
import net.virtela.enrollmentsystem.model.Clazz;
import net.virtela.enrollmentsystem.model.Student;
import net.virtela.enrollmentsystem.model.User;

public class UserService {

	private UserDao userDao = ServiceRegistry.getInstance(UserDao.class);
	private ClazzDao clazzDao = ServiceRegistry.getInstance(ClazzDao.class);

	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public Student findStudentByUsername(String username) {
		Student student = userDao.findStudentByUsername(username);
		List<Clazz> enrolledClasses = clazzDao.findAllEnrolledByStudentId(student.getId());
		student.setEnrolledClasses(enrolledClasses);
		return student;
	}

}
