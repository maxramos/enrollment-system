package net.virtela.enrollmentsystem.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Tuition implements Serializable {

	private static final long serialVersionUID = -6120281362428422458L;

	private List<Map.Entry<Clazz, BigDecimal>> courseFeeList;
	private BigDecimal miscellaneous;
	private BigDecimal total;

	public void calculateTotal() {
		total = courseFeeList.stream().map(entry -> entry.getValue()).reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
		total = total.add(miscellaneous);
	}

	public List<Map.Entry<Clazz, BigDecimal>> getCourseFeeList() {
		return courseFeeList;
	}

	public void setCourseFeeList(List<Map.Entry<Clazz, BigDecimal>> courseFeeList) {
		this.courseFeeList = courseFeeList;
	}

	public BigDecimal getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(BigDecimal miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return String.format("Tuition [courseFee=%s, miscellaneous=%s, total=%s]", courseFeeList, miscellaneous, total);
	}

}
