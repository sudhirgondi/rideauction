package com.sav.ra.adapters;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.apache.log4j.Logger;




import com.sav.ra.db.adapters.RADatabaseAdapter;
import com.sav.ra.domain.RAConsants;
import com.sav.ra.domain.RAOrder;
import com.sav.ra.domain.RAVendor;

public class RAVendorAdapter {
	static Logger logger=Logger.getLogger(RAVendorAdapter.class);
	/**
	 * Select of vendors from the database to send messages
	 */
	static public ArrayList<RAVendor> getVendorsList( RAOrder order) {

		// ������� ������ ��������
		// Create a list of Vendors
		ArrayList<RAVendor> list = new ArrayList<RAVendor>();
		
		ResultSet resultQuery;
		try {

			// Get Order ID          
			String id = order.getID();
			
			// �������� ���� ������� �� ������
			// Getting the travel date from the order 
			ResultSet res = RADatabaseAdapter.executeQuery( 
					"select PickUpTime as DateTime from tblOrderDetail where OrderId = " + id
					);
			
			
			if (!res.next())
				return list;
			
			// ������� ������� Calendar
			// Create objects Calendar
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = null;
			cal.setTimeZone(TimeZone.getTimeZone("GMT"));
			cal.setTimeInMillis(res.getLong("DateTime") * 1000);
			cal2 = (Calendar) cal.clone();
			
			// �������� ���� ������
			// Getting the day of the week
			String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, 1, java.util.Locale.ENGLISH);
			
			// �������� �������� UnixTime �� ������ ���
			// Getting the value of UnixTime for the beginning of the day
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			String beginTime = Long.toString(cal.getTimeInMillis() / 1000);
			
			// �������� �������� UnixTime �� ����� ���
			// Getting the value of UnixTime for the end of the day
			cal.set(Calendar.MILLISECOND, 999);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.HOUR_OF_DAY, 23);			
			String endTime = Long.toString(cal.getTimeInMillis() / 1000);
			
			// �������� �������� UnixTime ��� ������� ������ ������ ���
			// Getting the value for the time of the order within the day
			cal2.set(Calendar.MILLISECOND, 0);
			cal2.set(Calendar.YEAR, 1970);
			cal2.set(Calendar.MONTH, 0);
			cal2.set(Calendar.DAY_OF_MONTH, 1);			
			String time = Long.toString(cal2.getTimeInMillis() / 1000);
			
			// ����� ��������
			// Select vendors
			String query = "select distinct " +
					"tblvendor.Id as vendorID, " +
					"tblvendor.UserId as userID, " +
					"tblvendor.VndorRating as vendorRating, " +
					"tblvendorinfo.toPhone, " +
					"tblvendorinfo.fromPhone, " +
					"wp_users.user_email as mail, " +
					"IFNULL(tblcount.countMessages, 0) as countMessages, " +
					"tblsettings.SmsLimit as messageLimit, " +
					"tblsettings.sendMethod, " +
					"tbladdress.priority, " +
					"tblvendor.ZipCode "+
					
				//	# List of vendors
					"from tblVendors as tblvendor " +
				
				//	# Info
					"left join wp_users " +
					"on tblvendor.UserId = wp_users.ID " + 
				
				//	# Filter by location
					"inner join " +
					"(select UserId, min(priority) as priority from " +
					// # pref vendors ->
					"(select tblPreVendors.VendorId as UserId, 0 as priority from tblPreVendors " +
//					"left join tblVendors " +
//					"on tblPreVendors.VendorId = tblVendors.UserId " +
					"inner join " +
					"(select distinct tblCity.CountyId from " +
					"(select tblToAirport.`From` as city from tblToAirport where OrderId = " + id + " " +
					"union select tblFromAirport.`To` from tblFromAirport where OrderId = " + id + " " +
					"union select tblHourly.OriginationCity from tblHourly where OrderId = " + id + " " +
					"union select tblHourly.DestinationCity from tblHourly where OrderId = " + id + " " +
					"union select tblTransfers.`From` from tblTransfers where OrderId = " + id + " " +
					"union select tblTransfers.`To` from tblTransfers where OrderId = " + id + ") as tblct " +
					"left join tblCity on tblct.city = tblCity.CityId) as tblcnt " +
					"on tblPreVendors.StateId = tblcnt.CountyId " +
//					"on tblVendors.County = tblcnt.CountyId " +
					// # pref vendors <-
					"union " +
					"select tblvn.UserId, 1 from tblVendors as tblvn " +
					"inner join tblFromAirport as tblairport " +
					"on tblvn.City = tblairport.To " +
					"where tblairport.OrderId = " + id + " " +
					"union " +
					"select tblvn.UserId, 1 from tblVendors as tblvn " +
					"inner join tblToAirport as tblairport " +
					"on tblvn.City = tblairport.From " +
					"where tblairport.OrderId = " + id + " " +
					"union " +
					"select tblvn.UserId, 2 from tblVendors as tblvn " +
					"inner join " +
					"(select CityId from tblCity " +
					"inner join " +
					"(select CountyId from tblCity " +
					"inner join " +
					"(select CityId from " +
					"(select tblfrom.to as CityId from tblFromAirport as tblfrom " +
					"where tblfrom.OrderId = " + id + " " +
					"union " +
					"select tblto.from from tblToAirport as tblto " +
					"where tblto.OrderId = " + id + " " +
					"union " +
					"select tbltr.from from tblTransfers as tbltr " +
					"where tbltr.OrderId = " + id + " " +
					"union " +
					"select tbltr2.to from tblTransfers as tbltr2 " +
					"where tbltr2.OrderId = " + id + " " + 
					"union " +
					"select tblhrl.OriginationCity from tblHourly as tblhrl " +
					"where tblhrl.OrderId = " + id + " " +
					"union " +
					"select tblhrl2.DestinationCity from tblHourly as tblhrl2 " +
					"where tblhrl2.OrderId = " + id + ") as tbldata" +
					") as tbldata " +
					"on tblCity.CityId = tbldata.CityId " +
					") as tbldata " +
					"on tblCity.CountyId = tbldata.CountyId " +
					") as tbldata " +
					"on tblvn.City = tbldata.CityId " +
					") as tbldata " +
					"group by UserId" +
					")as tbladdress " +
					"on tblvendor.UserId = tbladdress.UserId " +
					
				//	# Filter by blocking time
					"inner join " +
					"(select UserId from tblVendors " +
					"left join (" +
					"select vendorid from tblVendorBlockDay where Block" + day + " > 0 " +
					"union " +
					"select vendorid from tblVendorBlockHoliday where StartTime between " + beginTime + " and " + endTime + " " +
					"union " +
					"select vendorid from tblVendorBlockHour where " + time + " between TIME_TO_SEC(CONVERT_TZ(FROM_UNIXTIME(StartTime), @@time_zone, '+0:00')) and TIME_TO_SEC(CONVERT_TZ(FROM_UNIXTIME(EndTime), @@time_zone, '+0:00'))" +
					") as tbldata " +
					"on tblVendors.UserId = tbldata.vendorid " +
					"where tbldata.vendorid is null) as tblblock " +
					"on tblvendor.UserId = tblblock.UserId " +
					
				//	# Filter by fleet (Type and Passengers)
					"inner join " +
					"(select distinct UserId from tblFleet " +
					"inner join " +
					"(select NoOfPassengers, Car from tblOrderDetail as tblfrom where tblfrom.OrderId = " + id + ") as tblpass " +
					"on tblFleet.FleetPassengers >= tblpass.NoOfPassengers " +
					"and (tblpass.Car = tblFleet.FleetType or tblpass.Car like 'I don`t%' or tblpass.Car = '') " +
					") as tblpass " +
					"on tblvendor.UserId = tblpass.UserId " +
					
				//	# Filter by status
					"left join " +
					"tblUsersStatus as tblUserStatus " +
					"on tblvendor.UserId = tblUserStatus.UserId " +
					
				//	# Filter messages sent over the last 2 minutes
					"left join " +
					"(select UserId, max(fromPhone) as fromPhone, toPhone from " +
					"(select tblVendors.UserId, tblData.phone as fromPhone, tblVendors.VendorPhone as toPhone, sum(k) as k from " +
					"(select tblVendors.UserId as vendorid, tblPhones.Phone as phone, 1 as k from tblVendors, tblPhones " +
					"union all " +
					"select distinct tblSmsStatistic.vendorid, tblPhones.Phone, -1 from " +
					"tblSmsStatistic left join tblPhones on " +
					"tblSmsStatistic.fromphoneid = tblPhones.PhoneId " +
					"where UNIX_TIMESTAMP() - sendtime < 130) as tblData " +
					"left join tblVendors on tblData.vendorid = tblVendors.UserId " +
					"group by tblData.vendorid, tblData.phone, tblVendors.VendorPhone " +
					"having (sum(k) > 0)) as tblData " +
					"group by UserId, toPhone) as tblvendorinfo " +
					"on tblvendor.UserId = tblvendorinfo.UserId " +
					
				//	# Filter by number of messages from the beginning of the day
					"left join " +
					"(select VendorId, count(Id) as countMessages from tblSmsStatistic " +
					"where sendtime >= UNIX_TIMESTAMP(CURDATE()) " +
					"group by VendorId) as tblcount " +
					"on tblvendor.UserId = tblcount.VendorId " +
					"left join " +
					"(select UserId, SmsLimit, case when SendMethod = '' then 'SMS' else SendMethod end as sendMethod from tblVendorSettings) as tblsettings " +
					"on tblvendor.UserId = tblsettings.UserId " +
					
					"where " +
					"(tblUserStatus.Status = 'Active' " + // # ������ �� ���������� ��������
					"and VndorRating > 2 " + // # ������ �� ��������
					"and ifnull(tblcount.countMessages, 0) < tblsettings.SmsLimit " +
					"and tblvendorinfo.UserId is not null " + // # ������ �� ���������� ���������
					"and tblvendor.Del = 0) " +
					"or tblvendor.UserId in (select VendorId as UserId from tblPreVendors) " + // # ��� ���� ��� pref vendor
					
					"order by " +
					"priority, " +
					"VndorRating Desc " +
					
				//	# Filter by number of vendors
					"limit 10";
				System.out.println(query);
			// ��������� ������
			// Perform a query
			resultQuery = RADatabaseAdapter.executeQuery( query);

			// ���� ��������� null, ���������� ������ ������
			// If relult is null, return empty list
			if (resultQuery == null)
				return list;
			
		
			
			// ����� ��������� ������� ��������
			// Otherwise, fill out a list of vendors
			while (resultQuery.next()) {
				
				list.add(new RAVendor(resultQuery.getString("vendorID"),
						resultQuery.getString("userID"),
						resultQuery.getString("toPhone"), 
						resultQuery.getString("fromPhone"),
						resultQuery.getString("sendMethod"),
						resultQuery.getString("mail"),
						resultQuery.getString("ZipCode")));
				
				logger.info("OrderId = " + id + ", VebdorId = " + resultQuery.getString("vendorID"));

			}
		} catch (SQLException e) {
			
			return new ArrayList<RAVendor>();
			
		}

		// ���������� ���������
		// Return result
		return list;

	}

	/**
	 * Set winner in the database
	 */
	static public int setWinner( String orderID) {

		
		
		try {
			
			// Get the winner
			String query = "select " +
							"tblst.OrderId as OrderId, " +
							"tblst.responcetime, " +
							"tblst.responcetext + 0 as price, " +
							"tblvn.UserId, " +
							"tblvn.VndorRating as rating, " +
							"case " +
							"when ((tblst.responcetext + 0) > (tbldetail.VendorPrice + 0)) " +
							"then 4 " +
							"when tblvn.UserId in (select VendorId from tblPreVendors) " +
							"then 0 " +
							"when (tblst.responcetext + 0) > 0 and (tblst.responcetext + 0) < (tbldetail.VendorPrice + 0) " +
							"then 1 " +
							"when tblvn.VndorRating = 4 or tblvn.VndorRating = 5 " +
							"then 2 " +
							"else 3 " +
							"end as priority, " +
							"tbldetail.VendorPrice as price, " +
							"(tblst.responcetext + 0) as vendorPrice " +
							"from tblSmsStatistic as tblst " +
							"left join " +
							"(select VendorPrice " +
							"from tblOrderDetail " +
							"where orderID = " + orderID + ") as tbldetail " +
							"on true " +
							"left join " +
							"tblVendors as tblvn " +
							"on tblst.vendorid = tblvn.UserId " +
							"where orderID = " + orderID + " " +
							"and tblst.responcetime <> 0 " +
							//"and (tblst.responcetext + 0 < tbldetail.price) " +
							"order by " +
							"priority, " +
							"vendorPrice, " +
							"rating desc, " +
							"tblst.responcetime " +
							"limit 1";

			ResultSet resultQuery = RADatabaseAdapter.executeQuery( query);

			// if the winner is received, write it to the database
			if (resultQuery.next()) {
				
				// ���� ��� ����������� ����, ���������� 0, �� ��������� ���������� � ����
				// If this is a price offer, return 0, without writing down a winner into the database
				if (resultQuery.getInt("priority") == 4)
					return 0;

				String vendorId = resultQuery.getString("UserId");

				logger.info("Winner: " + vendorId + " for order = " + resultQuery.getString("OrderId"));

				query = "update tblOrderDetail SET VendorID = " + vendorId + " where OrderId = " + resultQuery.getString("OrderId");
				
				int res = RADatabaseAdapter.executeUpdate( query);
				if (res == -1)
					return res;
				
				// ��� �������� sms � email ���������� �������� ��� ������
				// For sending an sms and the e-mail to the winner we are getting his data
				//RATwilioAdapter tw = new RATwilioAdapter(conn);
				RAVendor winner = getVendor( vendorId);
				if (winner != null){													
					
					// ��������� ����� ������
					// Shaping the text of the letter
					
					// �������� ������ ������
					// Receiving the necessary data
					Map<String, String> orderInfo = RAOrderAdapter.getOrderInfo( orderID);
					Map<String, String> smsInfo = RAOrderAdapter.getMessageInfo( orderID);
					
					for (Entry<String, String> entry : smsInfo.entrySet())
						orderInfo.put(entry.getKey(), entry.getValue());
					
					// ���������� ���������� sms
					// Send sms to winner
//					String message = RATemplates.getTemplate(conn, "sms", "ToWinner", 0);
//					
//					if (message == null)
//						message = "";
//					
//					message = RATemplates.replaceMap(message, orderInfo, "sms");
//					
//					message = RATemplates.replaceCond(message);
//					
//					message = RATemplates.replaceTemplate(conn, message, "sms");
					
					String message = "You are winner!";					
					
					//tw.sendSMS(winner, message, "");
					logger.info("Winner is "+winner+ "Message is ="+message);
					
					
					//message = RATemplates.getTemplate(conn, "mail", "ToWinner", 0);
					 
				//	if (message != null){					
						
//						message = RATemplates.replaceMap(message, orderInfo, "mail");
//						
//						// ������ ������� � �������
//						// Replacement of conditions in the template
//						message = RATemplates.replaceCond(message);
//						// ������ ������������
//						// Replacement of mapping
//						message = RATemplates.replaceTemplate(conn, message, "mail");
//						
//						// ���������� ������ ����������
//						// Send email to winner 
//						try {
//							new Builder().host("rideauction.com")
//									     .from(RAConsants.MAIL_LOGIN)
//									     .password(RAConsants.MAIL_PASS)
//									     .subject("You are a Winner!")
//									     .message(message)
//									     .to(winner.getMail())
//									     //.to("sshakirov@rideauction.com")
//									     .cc("tester@rideauction.com")
//									     .bcc("denisenko@keysoft.su")
//									     .build()
//									     .send()
//									     ;
//							logger.info("Send email to Winner (" + winner.getMail() + ")");
//							
//						} catch (UnsupportedEncodingException e) {
//							logger.error(e.getMessage());
//						} catch (MessagingException e) {
//							logger.error(e.getMessage());
//						} catch (MailBuildException e) {
//							logger.error(e.getMessage());
//						}
												
					}
					
					
					// ���������� ������ �������
					// Send email to customer
//					message = RATemplates.getTemplate(conn, "mail", "ToWinCustomer", 0);
//					 
//					if (message != null){					
//						
//						message = RATemplates.replaceMap(message, orderInfo, "mail");
//						
//						// ������ ������� � �������
//						// Replacement of conditions in the template
//						message = RATemplates.replaceCond(message);
//						
//						// ������ ������������
//						// Replacement of mapping
//						message = RATemplates.replaceTemplate(conn, message, "mail");
//						
//						// ���������� ������
//						// Send email 
//						try {
//							new Builder().host("rideauction.com")
//									     .from(RAConsants.MAIL_LOGIN)
//									     .password(RAConsants.MAIL_PASS)
//									     .subject("Thank You for Your Order")
//									     .message(message)
//									     .to(orderInfo.get("custMail"))
//									     .cc("tester@rideauction.com")
//									     .bcc("denisenko@keysoft.su")
//									     .build()
//									     .send()
//									     ;
//							logger.info("Send email to Customer (" + orderInfo.get("custMail") + ")");
//							
//						} catch (UnsupportedEncodingException e) {
//							logger.error(e.getMessage());
//						} catch (MessagingException e) {
//							logger.error(e.getMessage());
//						} catch (MailBuildException e) {
//							logger.error(e.getMessage());
//						}
												
//					}
					
//				}
				
//				logger.close();
//				logger = null;
				
				// ���������� ��������� ���������� ������ � ����
				// Returning the result from the updating data in the database
				return res;
				
			}
		} catch (SQLException e) {
			 logger.error(e.getMessage());
		}
		
		//logger.close();
		//logger = null;
		
		return -1;

	}
	
	/**
	 * �������� ������� �� ��� ID
	 * Get the Vendor by his ID
	 */
	public static RAVendor getVendor( String vendorId){
		
		// ��������� ������
		// Perform a query
		String query = "select tblVendors.id, tblVendors.userId, tblVendors.VendorPhone as toPhone, wp_users.user_email as mail from tblVendors " +
				"left join wp_users " +
				"on tblVendors.UserId = wp_users.ID " +
				"where UserID = " + vendorId;
		ResultSet res = RADatabaseAdapter.executeQuery( query);
		
		try {
			// ���� ������ ������, ������� � ���������� ������ � ����������� �������
			// If the vendor has been found, create and return an object with the obtained data 
			if (res.next()){
				RAVendor vendor = new RAVendor(res.getString("id"), res.getString("userId"), res.getString("toPhone"), "+14152372615", "sms", res.getString("mail"),"");
				return vendor;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		// ����� ���������� null
		// Else return null
		return null;
		
	}

}
