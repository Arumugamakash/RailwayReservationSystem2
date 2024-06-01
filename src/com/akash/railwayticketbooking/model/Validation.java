package com.akash.railwayticketbooking.model;

import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;

public class Validation {

	// signUp
	public boolean isValidEmail(String email) {
		Credentials credentials = RailwayTicketBookingDatabase.getInstance().getCredentials(email);
		return credentials == null;
	}

	// signin
	public boolean validCredentials(String email, String password) {
		Credentials credentials = RailwayTicketBookingDatabase.getInstance().getCredentials(email);
		if (credentials != null && credentials.password.equals(password)) {
			return true;
		}
		return false;
	}
}
