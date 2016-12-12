package net.virtela.enrollmentsystem.service;

import java.util.List;

import net.virtela.enrollmentsystem.dao.ClazzAlreadyExistsException;
import net.virtela.enrollmentsystem.dao.ClazzDao;
import net.virtela.enrollmentsystem.dao.ScheduleDao;
import net.virtela.enrollmentsystem.dao.SubjectDao;
import net.virtela.enrollmentsystem.dao.TeacherDao;
import net.virtela.enrollmentsystem.model.Clazz;
import net.virtela.enrollmentsystem.model.Schedule;
import net.virtela.enrollmentsystem.model.Subject;
import net.virtela.enrollmentsystem.model.Teacher;

public class EnrollmentService {

	private SubjectDao subjectDao = ServiceRegistry.getInstance(SubjectDao.class);
	private ScheduleDao scheduleDao = ServiceRegistry.getInstance(ScheduleDao.class);
	private TeacherDao teacherDao = ServiceRegistry.getInstance(TeacherDao.class);
	private ClazzDao clazzDao = ServiceRegistry.getInstance(ClazzDao.class);

	public List<Subject> findAllSubjects() {
		return subjectDao.findAll();
	}

	public List<Schedule> findAllSchedules() {
		return scheduleDao.findAll();
	}

	public List<Teacher> findAllTeachers() {
		return teacherDao.findAll();
	}

	public List<Clazz> findAllClasses() {
		return clazzDao.findAll();
	}

	public List<Clazz> findAllEnrolledClassesByStudentId(Long studentId) {
		return clazzDao.findAllEnrolledByStudentId(studentId);
	}

	public void addClazz(Long subjectId, Long scheduleId, Long teacherId) throws ClazzAlreadyExistsException {
		clazzDao.add(subjectId, scheduleId, teacherId);
	}

}
