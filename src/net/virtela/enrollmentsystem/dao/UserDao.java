package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Student;
import net.virtela.enrollmentsystem.model.User;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class UserDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public User findByUsername(String username) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from users where username = ?")) {
			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					return user;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public Student findStudentByUsername(String username) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn
						.prepareStatement(
								"select u.id, u.username, s.name from users u join student s on u.id = s.id where username = ?")) {
			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Student student = new Student();
					student.setId(rs.getLong("id"));
					student.setUsername(rs.getString("username"));
					student.setName(rs.getString("name"));
					return student;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}
