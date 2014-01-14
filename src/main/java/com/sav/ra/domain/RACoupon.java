package com.sav.ra.domain;

import java.util.Date;

public class RACoupon {
private long couponId;
private String couponCode;
private String couponDiscount;
private Date couponStart;
private Date couponEnd;
private int couponStatus;
private String couponSymbol;
private int couponQty;
/**
 * @return the couponId
 */
public long getCouponId() {
	return couponId;
}
/**
 * @param couponId the couponId to set
 */
public void setCouponId(long couponId) {
	this.couponId = couponId;
}
/**
 * @return the couponCode
 */
public String getCouponCode() {
	return couponCode;
}
/**
 * @param couponCode the couponCode to set
 */
public void setCouponCode(String couponCode) {
	this.couponCode = couponCode;
}
/**
 * @return the couponDiscount
 */
public String getCouponDiscount() {
	return couponDiscount;
}
/**
 * @param couponDiscount the couponDiscount to set
 */
public void setCouponDiscount(String couponDiscount) {
	this.couponDiscount = couponDiscount;
}
/**
 * @return the couponStart
 */
public Date getCouponStart() {
	return couponStart;
}
/**
 * @param couponStart the couponStart to set
 */
public void setCouponStart(Date couponStart) {
	this.couponStart = couponStart;
}
/**
 * @return the couponEnd
 */
public Date getCouponEnd() {
	return couponEnd;
}
/**
 * @param couponEnd the couponEnd to set
 */
public void setCouponEnd(Date couponEnd) {
	this.couponEnd = couponEnd;
}
/**
 * @return the couponStatus
 */
public int getCouponStatus() {
	return couponStatus;
}
/**
 * @param couponStatus the couponStatus to set
 */
public void setCouponStatus(int couponStatus) {
	this.couponStatus = couponStatus;
}
/**
 * @return the couponSymbol
 */
public String getCouponSymbol() {
	return couponSymbol;
}
/**
 * @param couponSymbol the couponSymbol to set
 */
public void setCouponSymbol(String couponSymbol) {
	this.couponSymbol = couponSymbol;
}
/**
 * @return the couponQty
 */
public int getCouponQty() {
	return couponQty;
}
/**
 * @param couponQty the couponQty to set
 */
public void setCouponQty(int couponQty) {
	this.couponQty = couponQty;
}




}
