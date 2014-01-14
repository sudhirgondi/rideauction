package com.sav.ra.test.racouponadapter;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.adapters.RACouponAdapter;
import com.sav.ra.domain.RACoupon;
import com.sav.ra.framework.ExcelReader;

public class TestGetGoupon {
	@DataProvider(name="couponDataProviderPositive")
	public Object[][] couponDataProviderPositive() {
		return ExcelReader.readExcelData("couponData.xlsx", "getCouponDataPos");
	}
	
	@DataProvider(name="couponDataProviderNegative")
	public Object[][] couponDataProviderNegative() {
		return ExcelReader.readExcelData("couponData.xlsx", "getCouponDataNeg");
	}
	 
	@Test(dataProvider="couponDataProviderPositive")
	public void testGetCouponPositive(String cId, String cCode, String cDiscount, String cStart, String cEnd, String cStatus, String cSymbol, String cCount) {
		long couponId = Long.parseLong(stripDecimal(cId));
		String couponCode = stripDecimal(cCode);
		String couponDiscount = stripDecimal(cDiscount);
		Date couponStartDate = new Date((new BigDecimal(cStart)).longValue());
		Date couponEndDate = new Date((new BigDecimal(cEnd)).longValue());
		int couponStatus = Integer.parseInt(stripDecimal(cStatus));
		String couponSymbol = stripDecimal(cSymbol);
		int couponCount = Integer.parseInt(stripDecimal(cCount));

		RACouponAdapter raca = new RACouponAdapter();
		RACoupon rac = raca.getCoupon(couponCode);
		
		assertEquals(couponId, rac.getCouponId());
		assertEquals(couponCode, rac.getCouponCode());
		assertEquals(couponDiscount, rac.getCouponDiscount());
		assertEquals(couponStartDate, rac.getCouponStart());
		assertEquals(couponEndDate, rac.getCouponEnd());
		assertEquals(couponStatus, rac.getCouponStatus());
		assertEquals(couponSymbol, rac.getCouponSymbol());
		assertEquals(couponCount, rac.getCouponQty());
	}
	
	@Test(dataProvider="couponDataProviderNegative")
	public void testGetCouponNegative(String cId, String cCode, String cDiscount, String cStart, String cEnd, String cStatus, String cSymbol, String cCount) {
		long couponId = Long.parseLong(stripDecimal(cId));
		String couponCode = stripDecimal(cCode);
		String couponDiscount = stripDecimal(cDiscount);
		Date couponStartDate = new Date((new BigDecimal(cStart)).longValue());
		Date couponEndDate = new Date((new BigDecimal(cEnd)).longValue());
		int couponStatus = Integer.parseInt(stripDecimal(cStatus));
		String couponSymbol = stripDecimal(cSymbol);
		int couponCount = Integer.parseInt(stripDecimal(cCount));

		RACouponAdapter raca = new RACouponAdapter();
		RACoupon rac = raca.getCoupon(couponCode);
		
		assertThat(couponId, not(equalTo(rac.getCouponId())));
		assertEquals(couponCode, rac.getCouponCode());
		assertThat(couponDiscount, not(equalTo(rac.getCouponDiscount())));
		assertThat(couponStartDate, not(equalTo(rac.getCouponStart())));
		assertThat(couponEndDate, not(equalTo(rac.getCouponEnd())));
		assertThat(couponStatus, not(equalTo(rac.getCouponStatus())));
		assertThat(couponSymbol, not(equalTo(rac.getCouponSymbol())));
		assertThat(couponCount, not(equalTo(rac.getCouponQty())));
	}
	private String stripDecimal(String dString) {
		if (dString.indexOf(".0")!= -1) {
			return dString.substring(0, dString.indexOf(".0"));
		} else {
			return dString;
		}
	}
}
