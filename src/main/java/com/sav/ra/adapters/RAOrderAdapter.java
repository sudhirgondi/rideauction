package com.sav.ra.adapters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.sav.ra.db.adapters.RADatabaseAdapter;

/** test
 * 
 * �����-������� ��� ������ � ��������. �������� ������ ��� ������ � ����� ������
 * Class-adapter for working with orders. Contains methods for working with database
 *
 */
public class RAOrderAdapter {

	/**
	 * ��������� ���������� � ������
	 * Getting the order info
	 * 
	 */
	static public Map<String, String> getOrderInfo( String orderID){
		
		// ������ �������
		// The query string
		String query = "select OrderNumber, OrderDateTime, OrderStatus, tblOrderDetail.*, " +
				"CONCAT(tblVendorFN.meta_value, ' ', tblVendorLN.meta_value) as Vendor, " +
				"tblVendors.VendorPhone, " +
				"case when tblGuests.GuestFN is null then CONCAT(ifnull(tblFN.meta_value, ''), ' ', ifnull(tblLN.meta_value, '')) else CONCAT(tblGuests.GuestFN, ' ', tblGuests.GuestLN) end as Customer, " +
				"case when tblGuests.GuestFN is null then tblph.meta_value else GuestPhone end as CustomerPhone, " +
				"case when tblGuests.GuestFN is null then wp_users.user_email else tblGuests.GuestMail end as CustEMail " +
				"from tblOrder " +
				"left join tblOrderDetail " +
				"on tblOrder.OrderId = tblOrderDetail.OrderId " +
				"left join tblVendors " +
				"on tblOrderDetail.VendorId = tblVendors.UserId " +
				"left join wp_users " +
				"on tblOrderDetail.CustId = wp_users.ID " +
				"left join (select * from wp_usermeta where wp_usermeta.meta_key = 'userphone') as tblph " +
				"on tblOrderDetail.CustId = tblph.user_id " +
				"left join tblGuests " +
				"on tblOrderDetail.GuestId = tblGuests.GuestId " +
				"left join (select * from wp_usermeta where meta_key = 'first_name') as tblFN " +
				"on tblOrderDetail.CustId = tblFN.user_id " +
				"left join (select * from wp_usermeta where meta_key = 'last_name') as tblLN " +
				"on tblOrderDetail.CustId = tblLN.user_id " +
				"left join (select * from wp_usermeta where meta_key = 'first_name') as tblVendorFN " +
				"on tblOrderDetail.VendorId = tblVendorFN.user_id " +
				"left join (select * from wp_usermeta where meta_key = 'last_name') as tblVendorLN " +
				"on tblOrderDetail.VendorId = tblVendorLN.user_id " +
				"where tblOrder.orderId = " + orderID;
		
		// ��������� ������
		// Perform a query
		ResultSet resultQuery = RADatabaseAdapter.executeQuery( query);
		HashMap<String, String> map = new HashMap<String, String>();
		
		// ���� ������� ������, ���������� null 
		// If the selection is empty, return null
		if (resultQuery == null) {
			return null;
		}
		
		// ��������� ������� �������
		// Populate the table with data
		try {
			if (resultQuery.next()) {
				map.put("orderId", resultQuery.getString("OrderId"));
				map.put("orderNumber", resultQuery.getString("OrderNumber"));
				map.put("time", resultQuery.getString("OrderDateTime"));
				map.put("status", resultQuery.getString("OrderStatus"));
				map.put("service", resultQuery.getString("OrderService"));
				map.put("customer", resultQuery.getString("Customer"));
				map.put("vendor", resultQuery.getString("Vendor"));
				map.put("vendorPhone", resultQuery.getString("VendorPhone"));
				map.put("custPhone", resultQuery.getString("CustomerPhone"));
				map.put("custMail", resultQuery.getString("CustEMail"));
				return map;
			}
		} catch (SQLException e) {
			
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * ��������� ������� ������ �� ��� ID
	 * Getting the order status by its ID
	 * 
	 */
	static public String getStatus( String orderID) {

		try {
			
			// Perform a query
			String query = "select orderStatus from tblOrder where orderId = " + orderID;
			ResultSet resultQuery = RADatabaseAdapter.executeQuery( query);

			// ���� � ������� ���� ������, ���������� ������
			// If selection has data, return status
			if (resultQuery.next()) {

				return resultQuery.getString("orderStatus");

			}
		} catch (SQLException e) {
//			logger.severe("getStatus error: " + e1.getMessage());
		}
		
		// � ������ ��������� ������ ��� ������ ���������� null
		// If order or error is absent, return null
		return null;

	}
	
	/**
	 * 
	 * ��������� ������� ������ � ID = orderID
	 * Sets the status of the order with ID = orderId
	 * 
	 */
	static public void setStatus(String orderID, String status){
		
		// Perform a query
		String query = "update tblOrder SET OrderStatus = '" + status + "' where orderId = " + orderID;
		RADatabaseAdapter.executeUpdate( query);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		//sendChangeStatus(orderID, map);
		
	}
	
	/**
	 * 
	 * ���������� ����������� ���������� ��� ��������� ��������
	 * We return the information necessary for communications with vendors
	 * 
	 */
	static public Map<String, String> getMessageInfo( String orderID){
		// ������ �������
		// The query string
		String query = "select 'FromAirport' as type, tbldet.OrderId, tbldet.Price, tbldet.VendorPrice, tblfromair.DestinationAddress, null as OriginationAddress, tblcit.CityName as toPlace, tblair.Name as fromPlace, tbldet.PickUpTime as orderDate, tbldet.NoOfPassengers as Pax, case when tbldet.ExtraStop = '' or tbldet.ExtraStop = 'No' then null else tbldet.NoOfStops end as ExtraStop, case when tbldet.Extras = '' then null else tbldet.Extras end as Extras, tbldet.Car as carType, null as numOfHours, tblfromair.InternationalDomestic as typeLine, tblfromair.Gate as gate, tblfromair.Airline as airline " +
				"from tblOrderDetail as tbldet left join tblFromAirport as tblfromair on tbldet.OrderId = tblfromair.OrderId " +
				"left join tblAirports as tblair " +
				"on tblfromair.FromAirport = tblair.AirportId " +
				"left join tblCity as tblcit " +
				"on tblfromair.To = tblcit.CityId " +
				"where tbldet.OrderId = " + orderID + " and tblfromair.OrderId is not null " +
				"" +
				"union " +
				"" +
				"select 'ToAirport' as type, tbldet.OrderId, tbldet.Price, tbldet.VendorPrice, null, tbltoair.OriginationAddress, tblair.Name, tblcit.CityName, tbldet.PickUpTime, tbldet.NoOfPassengers, case when tbldet.ExtraStop = '' or tbldet.ExtraStop = 'No' then null else tbldet.NoOfStops end, case when tbldet.Extras = '' then null else tbldet.Extras end, tbldet.Car, null, null, null, null " +
				"from tblOrderDetail as tbldet " +
				"left join tblToAirport as tbltoair " +
				"on tbldet.OrderId = tbltoair.OrderId " +
				"left join tblAirports as tblair " +
				"on tbltoair.ToAirport = tblair.AirportId " +
				"left join tblCity as tblcit " +
				"on tbltoair.From = tblcit.CityId " +
				"where tbldet.OrderId = " + orderID + " and tbltoair.OrderId is not null " +
				"" +
				"union " +
				"" +
				"select 'Hourly' as type, tbldet.OrderId, tbldet.Price, tbldet.VendorPrice, tblhour.DestinationAddress, tblhour.OriginalAddress, tblcitto.CityName, tblcitfrom.CityName, tbldet.PickUpTime, tbldet.NoOfPassengers, null, case when tbldet.Extras = '' then null else tbldet.Extras end, tbldet.Car, tblhour.NumberOfHours, null, null, null " +
				"from tblOrderDetail as tbldet " +
				"left join tblHourly as tblhour " +
				"on tbldet.OrderId = tblhour.OrderId " +
				"left join tblCity as tblcitfrom " +
				"on tblhour.OriginationCity = tblcitfrom.CityId " +
				"left join tblCity as tblcitto " +
				"on tblhour.DestinationCity = tblcitto.CityId " +
				"where tbldet.OrderId = " + orderID + " and tblhour.OrderId is not null " +
				"" +
				"union " +
				"select 'Transfer' as type, tbldet.OrderId, tbldet.Price, tbldet.VendorPrice, tbltr.DestinationAddress, tbltr.OriginationAddress, tblcitto.CityName, tblcitfrom.CityName, tbldet.PickUpTime, tbldet.NoOfPassengers, case when tbldet.ExtraStop = '' or tbldet.ExtraStop = 'No' then null else tbldet.NoOfStops end, case when tbldet.Extras = '' then null else tbldet.Extras end, tbldet.Car, null, null, null, null " +
				"from tblOrderDetail as tbldet " +
				"left join tblTransfers as tbltr " +
				"on tbldet.OrderId = tbltr.OrderId " +
				"left join tblCity as tblcitfrom " +
				"on tbltr.From = tblcitfrom.CityId " +
				"left join tblCity as tblcitto " +
				"on tbltr.To = tblcitto.CityId " +
				"where tbldet.OrderId = " + orderID + " and tbltr.OrderId is not null";
		
		Map<String, String> map = new HashMap<String, String>();
		ResultSet resultQuery;
		
		try {
			// ��������� ������
			// Perform a query 
			resultQuery = RADatabaseAdapter.executeQuery( query);

			// ���� ������� �� ������, ��������� ������� � �������
			// If the selection is not empty, we fill in a table with data
			if (resultQuery.next()) {

				map.put("type", resultQuery.getString("type"));
				map.put("from", resultQuery.getString("fromPlace"));
				map.put("to", resultQuery.getString("toPlace"));
				map.put("pax", resultQuery.getString("Pax"));
				map.put("destination", resultQuery.getString("DestinationAddress"));
				map.put("origination", resultQuery.getString("OriginationAddress"));
				SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yy hh:mm a");
				format.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat formatSMS = new java.text.SimpleDateFormat("MM/dd/yy");
				formatSMS.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat formatDate = new java.text.SimpleDateFormat("d MMMM yyyy");
				formatDate.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat formatTime = new java.text.SimpleDateFormat("h m a");
				formatTime.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat formatFullTime = new java.text.SimpleDateFormat("hh:mm a");
				formatFullTime.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date date = new java.util.Date(resultQuery.getLong("orderDate") * 1000);
				map.put("dateTime", format.format(date));
				map.put("dateSMS", formatSMS.format(date));
				map.put("date", formatDate.format(date));
				map.put("time", formatTime.format(date));
				map.put("fullTime", formatFullTime.format(date));
				map.put("extraStop", resultQuery.getString("ExtraStop"));
				map.put("extras", resultQuery.getString("Extras"));
				DecimalFormat f = new DecimalFormat("###.##");
				map.put("price", resultQuery.getString("Price"));
				map.put("vendorPrice", resultQuery.getString("VendorPrice"));
				float deposit = resultQuery.getFloat("Price") - resultQuery.getFloat("VendorPrice");
				map.put("deposit", f.format(Math.round((deposit) * 100f) / 100f));
//				map.put("deposit", Float.toString(Math.round((resultQuery.getFloat("Price") - resultQuery.getFloat("VendorPrice")) * 100) / 100));
				map.put("hours", resultQuery.getString("numOfHours"));
				map.put("carType", resultQuery.getString("carType"));
				map.put("typeLine", resultQuery.getString("typeLine"));
				map.put("gate", resultQuery.getString("gate"));
				map.put("airline", resultQuery.getString("airline"));
				
				// ���������� �������������� �������
				// We returning a generated table
				return map;
			}
		} catch (SQLException e1) {
//			logger.severe("sendSMS details error: " + e1.getMessage());
		}

		return new HashMap<String, String>();
	}
	
	// ���� �������� ��������� ������, ���������� ���������� �� ����
	// If change the status of an order, send a notice on the site
	static public void sendChangeStatus(String id, HashMap<String, String> map){
		String query = "";
		if (map.containsKey("status")) query = "OrderStatus=" + map.get("status");
		URL url;
		try {
			url = new URL("https://www.rideauction.com/wp-admin/admin-ajax.php?action=order-change-attr&OrderId=" + id + "&" + query);
			url.openStream().close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
