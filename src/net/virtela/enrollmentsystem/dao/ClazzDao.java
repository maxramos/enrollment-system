package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Clazz;
import net.virtela.enrollmentsystem.model.Schedule;
import net.virtela.enrollmentsystem.model.Subject;
import net.virtela.enrollmentsystem.model.SubjectType;
import net.virtela.enrollmentsystem.model.Teacher;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class ClazzDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public List<Clazz> findAll() {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from v_clazz")) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Clazz> classes = new ArrayList<>();

				while (rs.next()) {
					Subject subject = new Subject();
					subject.setId(rs.getLong("subject_id"));
					subject.setName(rs.getString("subject_name"));
					subject.setType(SubjectType.fromCode(rs.getString("subject_type")));

					Schedule schedule = new Schedule();
					schedule.setId(rs.getLong("schedule_id"));
					schedule.setPeriod(rs.getString("schedule_period"));

					Teacher teacher = new Teacher();
					teacher.setId(rs.getLong("teacher_id"));
					teacher.setName(rs.getString("teacher_name"));

					Clazz clazz = new Clazz();
					clazz.setId(rs.getLong("clazz_id"));
					clazz.setSubject(subject);
					clazz.setSchedule(schedule);
					clazz.setTeacher(teacher);

					classes.add(clazz);
				}

				return classes;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Long subjectId, Long scheduleId, Long teacherId) throws ClazzAlreadyExistsException {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("insert into clazz values(clazz_seq.nextval, ?, ?, ?)")) {
			ps.setLong(1, subjectId);
			ps.setLong(2, scheduleId);
			ps.setLong(3, teacherId);
			int rowCount = ps.executeUpdate();

			if (rowCount == 1) {
				conn.commit();
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new ClazzAlreadyExistsException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
