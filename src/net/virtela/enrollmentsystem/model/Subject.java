package net.virtela.enrollmentsystem.model;

import java.io.Serializable;

public class Subject implements Serializable {

	private static final long serialVersionUID = 1957548033849836474L;

	private Long id;
	private String name;
	private SubjectType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubjectType getType() {
		return type;
	}

	public void setType(SubjectType type) {
		this.type = type;
	}

}
