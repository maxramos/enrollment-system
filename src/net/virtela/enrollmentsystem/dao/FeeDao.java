package net.virtela.enrollmentsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.virtela.enrollmentsystem.model.Fee;
import net.virtela.enrollmentsystem.service.ServiceRegistry;

public class FeeDao {

	private DataSource datasource = ServiceRegistry.getInstance(DataSource.class);

	public Map<String, Fee> findAllAsMap() {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from fee")) {
			try (ResultSet rs = ps.executeQuery()) {
				Map<String, Fee> feeMap = new HashMap<>();

				while (rs.next()) {
					Fee fee = new Fee();
					fee.setId(rs.getLong("id"));
					fee.setType(rs.getString("fee_type"));
					fee.setAmount(rs.getBigDecimal("amount"));
					feeMap.put(fee.getType(), fee);
				}

				return feeMap;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
