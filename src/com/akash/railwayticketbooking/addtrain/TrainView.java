package com.akash.railwayticketbooking.addtrain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.akash.railwayticketbooking.login.LoginView;

public class TrainView {
	Scanner sc = new Scanner(System.in);
	private TrainModel trainModel;

	public TrainView() {
		trainModel = new TrainModel(this);
	}

	public void start() throws ClassNotFoundException, SQLException {
		System.out.println("\nFeatures....");
		while (true) {
			System.out.println("\n1.AddTrain\n2.Booked Tickets\n3.SearchPassanger\n4.Change Ticket Status\n5.Exit\nEnter your choice");
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				addTrain();
				break;
			case "2":
				trainModel.bookedTickets();
				break;
			case "3":
				searchPassanger();
				break;
			case "4":
				changeStatus();
				break;
			case "5":
				System.out.println("ThanYou for using  this App");
				LoginView loginView = new LoginView();
				loginView.start();
			default:
				System.out.println("Invalid choice pls try again");
				start();
			}
		}
	}

	private void changeStatus() throws ClassNotFoundException, SQLException {
		System.out.println("Enter PNR Number");
		long pnr=sc.nextLong();
		sc.nextLine();
		trainModel.changeStatus(pnr);
	}

	private void searchPassanger() throws ClassNotFoundException, SQLException {
		System.out.println("Enter your passanger Id");
		int id = sc.nextInt();
		sc.nextLine();
		trainModel.searchPassanger(id);
	}

	private void addTrain() throws ClassNotFoundException, SQLException {
		List<String> routes =new ArrayList<String>();
		System.out.println("Enter Train Number");
		int trainNo = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Train Name");
		String trainName = sc.nextLine();
		System.out.println("Enter Depature Time");
		String depature = sc.nextLine();
		System.out.println("Enter arrival Time");
		String arrival = sc.nextLine();
		System.out.println("Enter Total Seats");
		int seats = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Fare Ammount");
		double fare = sc.nextDouble();
		sc.nextLine();
		System.out.println("how many routes you hava add");
		int routeCount = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < routeCount; i++) {
			System.out.println("Enter your Routes");
			routes.add(sc.nextLine());
		}
		trainModel.addTrain(trainNo, trainName, depature, arrival, seats, fare, routes);
		
	}

	private void getTrain() throws ClassNotFoundException, SQLException {
		trainModel.getAllTrain();
	}

	public void message(String msg) {
		System.out.println(msg);
	}
}
