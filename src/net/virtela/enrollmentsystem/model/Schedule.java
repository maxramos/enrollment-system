package net.virtela.enrollmentsystem.model;

import java.io.Serializable;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 1678744901204279280L;

	private Long id;
	private String period;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
