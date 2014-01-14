package com.sav.ra.domain;

public class RAVendor {
	String	vendorID;
	String userID;
	String toPhone;
	String fromPhone;
	String sendMethod;
	String mail;
	String zipCode;
	
	

	public String getZipCode() {
		return zipCode;
	}







	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}







	public String zipCode() {
		return vendorID;
	}







	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}







	public String getUserID() {
		return userID;
	}







	public void setUserID(String userID) {
		this.userID = userID;
	}







	public String getToPhone() {
		return toPhone;
	}







	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}







	public String getFromPhone() {
		return fromPhone;
	}







	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}







	public String getSendMethod() {
		return sendMethod;
	}







	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}







	public String getMail() {
		return mail;
	}







	public void setMail(String mail) {
		this.mail = mail;
	}







	public RAVendor(String vendorID, String userID, String toPhone,
			String fromPhone, String sendMethod,String mail,String ZipCode) {
		super();
		this.vendorID = vendorID;
		this.userID = userID;
		this.toPhone = toPhone;
		this.fromPhone = fromPhone;
		this.sendMethod = sendMethod;
		this.zipCode=ZipCode;
	}
	
	
	
	



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
