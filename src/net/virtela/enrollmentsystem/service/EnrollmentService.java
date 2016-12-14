package net.virtela.enrollmentsystem.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.virtela.enrollmentsystem.dao.ClazzDao;
import net.virtela.enrollmentsystem.dao.FeeDao;
import net.virtela.enrollmentsystem.dao.ScheduleDao;
import net.virtela.enrollmentsystem.dao.SubjectDao;
import net.virtela.enrollmentsystem.dao.TeacherDao;
import net.virtela.enrollmentsystem.model.Clazz;
import net.virtela.enrollmentsystem.model.Fee;
import net.virtela.enrollmentsystem.model.Schedule;
import net.virtela.enrollmentsystem.model.Student;
import net.virtela.enrollmentsystem.model.Subject;
import net.virtela.enrollmentsystem.model.Teacher;
import net.virtela.enrollmentsystem.model.Tuition;

public class EnrollmentService {

	private SubjectDao subjectDao = ServiceRegistry.getInstance(SubjectDao.class);
	private ScheduleDao scheduleDao = ServiceRegistry.getInstance(ScheduleDao.class);
	private TeacherDao teacherDao = ServiceRegistry.getInstance(TeacherDao.class);
	private ClazzDao clazzDao = ServiceRegistry.getInstance(ClazzDao.class);
	private FeeDao feeDao = ServiceRegistry.getInstance(FeeDao.class);

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

	public boolean addClazz(Long subjectId, Long scheduleId, Long teacherId) {
		return clazzDao.add(subjectId, scheduleId, teacherId);
	}

	public boolean enrollClazz(Student student, Long clazzId) {
		boolean enrolled = clazzDao.enroll(student.getId(), clazzId);

		if (enrolled) {
			List<Clazz> enrolledClasses = clazzDao.findAllEnrolledByStudentId(student.getId());
			student.setEnrolledClasses(enrolledClasses);
		}

		return enrolled;
	}

	public Tuition calculateTuition(Student student) {
		Map<String, Fee> feeMap = feeDao.findAllAsMap();
		Map<Clazz, BigDecimal> courseFeeMap = new LinkedHashMap<>();
		student.getEnrolledClasses().stream()
		.forEachOrdered(clazz -> courseFeeMap.put(clazz, feeMap.get(clazz.getSubjectTypeAsString()).getAmount()));

		Tuition tuition = new Tuition();
		tuition.setCourseFeeList(new ArrayList<>(courseFeeMap.entrySet()));
		tuition.setMiscellaneous(feeMap.get("MISCELLANEOUS").getAmount());
		tuition.calculateTotal();
		return tuition;
	}

}
