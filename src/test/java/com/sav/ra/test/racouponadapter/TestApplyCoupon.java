package com.sav.ra.test.racouponadapter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.adapters.RACouponAdapter;
import com.sav.ra.domain.RACoupon;
import com.sav.ra.framework.ExcelReader;

public class TestApplyCoupon {
	
	@DataProvider(name="couponDataProviderPositive")
	public Object[][] couponDataProviderPositive() {
		return ExcelReader.readExcelData("couponData.xlsx", "positive");
	}
	
	@DataProvider(name="couponDataProviderNegative")
	public Object[][] couponDataProviderNegative() {
		return ExcelReader.readExcelData("couponData.xlsx", "negative");
	}
	
	@Test(dataProvider="couponDataProviderPositive")
	public void testApplyCouponPositive(String cCode) {
		String couponCode;
		if (cCode.indexOf(".0")!= -1) {
			couponCode = cCode.substring(0, cCode.indexOf(".0"));
		} else {
			couponCode = cCode;
		}
		
		RACouponAdapter raca = new RACouponAdapter();
		RACoupon rac;
		int beforeCouponQty;
		int afterCouponQty;
		try {
			System.out.println(couponCode);
			rac = raca.getCoupon(couponCode);
			beforeCouponQty =rac.getCouponQty();
			System.out.println(beforeCouponQty);
			
			raca.applyCoupon(couponCode);
			
			rac = raca.getCoupon(couponCode);
			afterCouponQty =rac.getCouponQty();
			System.out.println(afterCouponQty);
			
			Assert.assertEquals(afterCouponQty, beforeCouponQty-1);
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test(dataProvider="couponDataProviderNegative")
	public void testApplyCouponNegative(String couponCode) {
		RACouponAdapter raca = new RACouponAdapter();
		try {
			raca.applyCoupon(couponCode);
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
