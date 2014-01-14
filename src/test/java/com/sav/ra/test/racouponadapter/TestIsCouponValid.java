package com.sav.ra.test.racouponadapter;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.adapters.RACouponAdapter;
import com.sav.ra.framework.ExcelReader;

public class TestIsCouponValid {
	
	@DataProvider(name="couponDataProviderPositive")
	public Object[][] couponDataProviderPositive() {
		return ExcelReader.readExcelData("couponData.xlsx", "positive");
	}
	
	@DataProvider(name="couponDataProviderNegative")
	public Object[][] couponDataProviderNegative() {
		return ExcelReader.readExcelData("couponData.xlsx", "negative");
	}
	
	@Test(dataProvider="couponDataProviderPositive")
	public void testIsCouponValidPositive(String cCode) {
		String couponCode;
		if (cCode.indexOf(".0")!= -1) {
			couponCode = cCode.substring(0, cCode.indexOf(".0"));
		} else {
			couponCode = cCode;
		}
		RACouponAdapter raca = new RACouponAdapter();
		Assert.assertTrue(raca.isCouponValid(couponCode));
	}
	
	@Test(dataProvider="couponDataProviderNegative")
	public void testIsCouponValidNegative(String cCode) {
		String couponCode;
		if (cCode.indexOf(".0")!= -1) {
			couponCode = cCode.substring(0, cCode.indexOf(".0"));
		} else {
			couponCode = cCode;
		}
		RACouponAdapter raca = new RACouponAdapter();
		Assert.assertFalse(raca.isCouponValid(couponCode));
	}
}
