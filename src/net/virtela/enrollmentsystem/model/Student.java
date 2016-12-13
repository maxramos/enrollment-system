package net.virtela.enrollmentsystem.model;

import java.util.List;

public class Student extends User {

	private static final long serialVersionUID = 7451353532517038370L;

	private String name;
	private List<Clazz> enrolledClasses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Clazz> getEnrolledClasses() {
		return enrolledClasses;
	}

	public void setEnrolledClasses(List<Clazz> enrolledClasses) {
		this.enrolledClasses = enrolledClasses;
	}

}
