package com.sav.ra.domain;

public class RACustomer {
 private long custId;
 private int userId;
 private String state;
 private String city;
 private String zip;
 private String address1;
 private String userAddress2;
 
 public RACustomer(){
	 
 }
public RACustomer(long custId, int userId, String state, String city,
		String zip, String address1, String userAddress2) {
	super();
	this.custId = custId;
	this.userId = userId;
	this.state = state;
	this.city = city;
	this.zip = zip;
	this.address1 = address1;
	this.userAddress2 = userAddress2;
}
public long getCustId() {
	return custId;
}
public void setCustId(long custId) {
	this.custId = custId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getAddress1() {
	return address1;
}
public void setAddress1(String address1) {
	this.address1 = address1;
}
public String getUserAddress2() {
	return userAddress2;
}
public void setUserAddress2(String userAddress2) {
	this.userAddress2 = userAddress2;
}
 
 
}
