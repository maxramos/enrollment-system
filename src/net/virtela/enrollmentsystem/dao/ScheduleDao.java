package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Schedule;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class ScheduleDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public List<Schedule> findAll() {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from schedule")) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Schedule> schedules = new ArrayList<>();

				while (rs.next()) {
					Schedule schedule = new Schedule();
					schedule.setId(rs.getLong("id"));
					schedule.setPeriod(rs.getString("period"));
					schedules.add(schedule);
				}

				return schedules;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
