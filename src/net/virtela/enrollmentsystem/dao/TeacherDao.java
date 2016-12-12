package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Teacher;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class TeacherDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public List<Teacher> findAll() {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from teacher")) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Teacher> teachers = new ArrayList<>();

				while (rs.next()) {
					Teacher teacher = new Teacher();
					teacher.setId(rs.getLong("id"));
					teacher.setName(rs.getString("name"));
					teachers.add(teacher);
				}

				return teachers;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
