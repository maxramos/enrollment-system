package net.virtela.enrollmentsystem.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import net.virtela.enrollmentsystem.service.ServiceRegistry;

@WebListener
public class StartupListener implements ServletContextListener {

	@Resource(name = "jdbc/enrollment")
	private DataSource datasource;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServiceRegistry.init(datasource);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
