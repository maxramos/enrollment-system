package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Subject;
import net.virtela.enrollmentsystem.model.SubjectType;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class SubjectDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public List<Subject> findAll() {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from subject")) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Subject> subjects = new ArrayList<>();

				while (rs.next()) {
					Subject subject = new Subject();
					subject.setId(rs.getLong("id"));
					subject.setName(rs.getString("name"));
					subject.setType(SubjectType.fromCode(rs.getString("subject_type")));
					subjects.add(subject);
				}

				return subjects;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
