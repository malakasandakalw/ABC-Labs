package com.code_with_malaka.ABC_lab_system.models;

import java.sql.Date;

public class DurationFilter {
	Date startDate;
	Date endDate;
	
	public DurationFilter(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
