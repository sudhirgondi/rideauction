package com.sav.ra.test.ravendoradapter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sav.ra.adapters.RAVendorAdapter;
import com.sav.ra.domain.RAVendor;
import com.sav.ra.framework.ExcelReader;

public class TestGetVendor {
	/*@DataProvider(name="vendorDataProviderPositive")
	public Object[][] vendorDataProviderPositive() {
		return ExcelReader.readExcelData("vendorData.xlsx", "vendorIdsPos");
	}
	
	@DataProvider(name="vendorDataProviderNegative")
	public Object[][] vendorDataProviderNegative() {
		return ExcelReader.readExcelData("vendorData.xlsx", "vendorIdsNeg");
	}*/
	
	/*@Test(dataProvider="vendorDataProviderPositive")
	public void testGetVendorPsitive(String vId) {
		String vendorId;
		if (vId.indexOf(".0")!= -1) {
			vendorId = vId.substring(0, vId.indexOf(".0"));
		} else {
			vendorId = vId;
		}

		RAVendorAdapter rava = new RAVendorAdapter();
		RAVendor rav;
		rav = rava.getVendor(vendorId);
		
		System.out.println(rav.getUserID());
		
		try {
			rav = rava.getVendor(vendorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

//	@Test(dataProvider="vendorDataProviderNegative")
//	public void testGetVendorNegative(String vId) {
//		String vendorId;
//		if (vId.indexOf(".0")!= -1) {
//			vendorId = vId.substring(0, vId.indexOf(".0"));
//		} else {
//			vendorId = vId;
//		}
//	}
}
