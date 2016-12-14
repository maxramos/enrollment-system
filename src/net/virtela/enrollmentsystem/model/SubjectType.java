package net.virtela.enrollmentsystem.model;

import java.util.stream.Stream;

public enum SubjectType {

	UNDERGRADUATE("U"),
	GRADUATE("G");

	private String code;

	private SubjectType(String code) {
		this.code = code;
	}

	public static SubjectType fromCode(String code) {
		return Stream.of(SubjectType.values()).filter(subjectType -> subjectType.code.equals(code)).findFirst().get();
	}

	public String getCode() {
		return code;
	}

}
