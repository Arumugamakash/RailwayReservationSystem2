package com.akash.railwayticketbooking.login;

//import com.akash.railwayticketbooking.RailwayTicketBooking;
import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.model.Credentials;
import com.akash.railwayticketbooking.model.Validation;

public class LoginModel {
	private LoginView loginView;
	private Validation validation;
	private Credentials credentials;

	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
		// credentials=new Credentials();
		validation = new Validation();
	}

	public void start() {
		loginView.adminSetUp();
	}

	public void createCredentials(String name, String email, String password, boolean isAdmin) {
		credentials = new Credentials();
		credentials.setName(name);
		credentials.setEmail(email);
		credentials.setPassword(password);
		credentials.setAdmin(isAdmin);
		if (validation.isValidEmail(email)) {
			RailwayTicketBookingDatabase.getInstance().addCredentials(credentials);
			loginView.message("\n\tSignUp Successfull....");
		}
		loginView.startSignIn();

	}

	public void validateProcess(String email, String password) {

		if (validation.validCredentials(email, password)) {
			loginView.loginSuccess(email);
		} else {
			loginView.message("you Dont have an Account pls signUp first");
			loginView.startSignUp();

		}
	}

}
