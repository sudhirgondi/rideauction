package com.sav.ra.domain;

import java.util.Date;

public class RAOrder {
	String ID;
	Date pickDate;
	int custId;
	

	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	
	

}
