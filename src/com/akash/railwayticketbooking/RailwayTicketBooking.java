package com.akash.railwayticketbooking;

import java.sql.SQLException;

import com.akash.railwayticketbooking.login.LoginView;

public class RailwayTicketBooking {

	private static RailwayTicketBooking railwayTicketBooking;
	private static String appName = "Railway Ticket Booking";
	private static String appVersion = "0.0.1";

	public static String getAppName() {
		return appName;
	}

	public static String getAppVersion() {
		return appVersion;
	}

	public static RailwayTicketBooking getInstance() {
		if (railwayTicketBooking == null) {
			railwayTicketBooking = new RailwayTicketBooking();
		}
		return railwayTicketBooking;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		railwayTicketBooking.getInstance().init();
	}

	private void init() throws ClassNotFoundException, SQLException {
		LoginView loginView=new LoginView();
		loginView.start();
	}
}
