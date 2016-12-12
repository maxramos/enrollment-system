package net.virtela.enrollmentsystem.model;

import java.io.Serializable;

public class Clazz implements Serializable {

	private static final long serialVersionUID = -4538331481712852352L;

	private Long id;
	private Subject subject;
	private Schedule schedule;
	private Teacher teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
