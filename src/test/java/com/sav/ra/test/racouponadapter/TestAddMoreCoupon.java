package com.sav.ra.test.racouponadapter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.adapters.RACouponAdapter;
import com.sav.ra.domain.RACoupon;
import com.sav.ra.framework.ExcelReader;

public class TestAddMoreCoupon {
	
	@DataProvider(name="couponQuantityDataProviderPositive")
	public Object[][] couponQuantityDataProviderPositive() {
		return ExcelReader.readExcelData("couponData.xlsx", "addCouponCodesPos");
	}
	
	@DataProvider(name="couponQuantityDataProviderNegative")
	public Object[][] couponQuantityDataProviderNegative() {
		return ExcelReader.readExcelData("couponData.xlsx", "addCouponCodesNeg");
	}
	
	@Test(dataProvider="couponQuantityDataProviderPositive")
	public void testAddCouponQuantityPsitive(String cCode, String strQty) {
		String couponCode;
		if (cCode.indexOf(".0")!= -1) {
			couponCode = cCode.substring(0, cCode.indexOf(".0"));
		} else {
			couponCode = cCode;
		}
		int intQty = Integer.parseInt(strQty.substring(0, strQty.indexOf(".0")));

		RACouponAdapter raca = new RACouponAdapter();
		RACoupon rac;
		int beforeCouponQty;
		int afterCouponQty;
		try {
			rac = raca.getCoupon(couponCode);
			beforeCouponQty =rac.getCouponQty();
			
			raca.addMoreCoupon(couponCode, intQty);
			
			rac = raca.getCoupon(couponCode);
			afterCouponQty =rac.getCouponQty();
			
			Assert.assertEquals(afterCouponQty, beforeCouponQty+intQty);
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test(dataProvider="couponQuantityDataProviderNegative")
	public void testAddCouponQuantityNegative(String cCode, String strQty) {
		int intQty = Integer.parseInt(strQty.substring(0, strQty.indexOf(".0")));
		String couponCode;
		if (cCode.indexOf(".0")!= -1) {
			couponCode = cCode.substring(0, cCode.indexOf(".0"));
		} else {
			couponCode = cCode;
		}
		
		RACouponAdapter raca = new RACouponAdapter();
		try {
			raca.addMoreCoupon(couponCode, intQty);
			Assert.fail(); // if addMoreCoupon doesn't throw an exception (as
							// the coupon codes are not valid), fail the test
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
