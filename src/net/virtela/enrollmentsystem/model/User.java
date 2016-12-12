package net.virtela.enrollmentsystem.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1178211008930755514L;

	private Long id;
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
