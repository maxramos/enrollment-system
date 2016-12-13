package net.virtela.enrollmentsystem.service;

import java.util.HashMap;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.dao.ClazzDao;
import net.virtela.enrollmentsystem.dao.ScheduleDao;
import net.virtela.enrollmentsystem.dao.SubjectDao;
import net.virtela.enrollmentsystem.dao.TeacherDao;
import net.virtela.enrollmentsystem.dao.UserDao;

public class ServiceRegistry {

	private static HashMap<Class<?>, Object> instanceMap;

	private ServiceRegistry() {
		super();
	}

	public static void init(DataSource datasource) {
		instanceMap = new HashMap<>();
		instanceMap.put(DataSource.class, datasource);
		instanceMap.put(UserDao.class, new UserDao());
		instanceMap.put(SubjectDao.class, new SubjectDao());
		instanceMap.put(ScheduleDao.class, new ScheduleDao());
		instanceMap.put(TeacherDao.class, new TeacherDao());
		instanceMap.put(ClazzDao.class, new ClazzDao());
		instanceMap.put(UserService.class, new UserService());
		instanceMap.put(EnrollmentService.class, new EnrollmentService());
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> clazz) {
		return (T) instanceMap.get(clazz);
	}

}
