package com.akash.railwayticketbooking.login;

import java.sql.SQLException;
import java.util.Scanner;

import com.akash.railwayticketbooking.RailwayTicketBooking;
import com.akash.railwayticketbooking.addtrain.TrainView;
import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.model.Credentials;
import com.akash.railwayticketbooking.model.Validation;
import com.akash.railwayticketbooking.passangerbooking.BookingView;

public class LoginView {
	Scanner sc = new Scanner(System.in);
	private LoginModel loginModel;
	private Validation validation;
	Credentials credentials;

	public LoginView() {
		validation = new Validation();
		loginModel = new LoginModel(this);
		credentials = new Credentials();
	}

	public void start() throws ClassNotFoundException, SQLException {
		System.out.println("--------------" +RailwayTicketBooking.getInstance().getAppName()
				+ " ---------------- \n\t\t  version " + "(" + RailwayTicketBooking.getInstance().getAppVersion() + ")");

		System.out.println("\nWelcome to Home_page");
		System.out.println("\n1.SignIn\n2.SignUp\n3.Exit\nEnter Your Choice");
		String choice = sc.nextLine();
		while (true) {
			switch (choice) {
			case "1":
				loginModel.start();
				break;
			case "2":
				startSignUp();
				break;
			case "3":
				System.out.println("Thanyou For Using this App");
				System.exit(0);
				return;
			default:
				start();
			}
		}
	}

	public void adminSetUp() throws ClassNotFoundException, SQLException {
		String name = "Akash";
		String email = "akash@gmail.com";
		String password = "123";
		boolean isAdmin = true;
		loginModel.createCredentials(name, email, password, isAdmin);
	}

	public void startSignIn() throws ClassNotFoundException, SQLException {
		System.out.println("\n\tSignIn here.....\n");
		System.out.println("Enter Your MailId");
		String email = sc.nextLine();
		System.out.println("Enter Your password");
		String password = sc.nextLine();
		loginModel.validateProcess(email, password);
	}

	public void startSignUp() throws ClassNotFoundException, SQLException {
		System.out.println("\n\tSignUp here.....\n");
		System.out.println("Enter Your Name");
		String name = sc.nextLine();
		System.out.println("Enter Your MailId");
		String email = sc.nextLine();
		System.out.println("Enter Your password");
		String password = sc.nextLine();
		if (validation.isValidEmail(email)) {
			loginModel.createCredentials(name, email, password, credentials.getAdmin());
		} else {
			message("Already Have an account pls SignIn");
			startSignIn();
		}
	}

	public void loginSuccess(String email) throws ClassNotFoundException, SQLException {

		Credentials credentials = RailwayTicketBookingDatabase.getInstance().getCredentials(email);
		if (credentials.getAdmin() == true) {
			System.out.println("Admin Login Sucess");
			TrainView trainView=new TrainView();
			trainView.start();
		} else {
			System.out.println("User Login Sucess");
			BookingView bookingView=new BookingView(); 
			bookingView.start();
		}
	}

	public void message(String message) {
		System.out.println(message);
	}

}
