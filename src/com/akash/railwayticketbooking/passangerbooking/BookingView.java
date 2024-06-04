package com.akash.railwayticketbooking.passangerbooking;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.login.LoginView;
import com.akash.railwayticketbooking.model.Passanger;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;

public class BookingView {
	Scanner sc = new Scanner(System.in);
	private BookingModel bookingModel;

	public BookingView() {
		bookingModel = new BookingModel(this);
	}

	public void start() throws ClassNotFoundException, SQLException {
		System.out.println("\n\tWelcome To IRCTC");
		while (true) {
			System.out.println("\n1.Booking\n2.PNR Status\n3.Cancel Tickets\n4.Train Routes\n5.Exit\nEnter Your choice");
			switch (sc.nextLine()) {
			case "1":
				startBooking();
				break;
			case "2":
				ticketDetails();
				break;
			case "3":
				cancelTickets();
				break;
			case "4":
				trainRoutes();
				break;
			case "5":
				System.out.println("ThanYou for using this App");
				LoginView loginView = new LoginView();
				loginView.start();
			case "7":
				getTicket();
				break;
			default:
				System.out.println("Invalid option try Again...");
				start();
			}
		}
	}

	private void trainRoutes() throws ClassNotFoundException, SQLException {
		System.out.println("Enter your Train Number");
		int trainNo = sc.nextInt();
		sc.nextLine();
		bookingModel.listOfTrainRoutes(trainNo);
	}

	private void cancelTickets() throws ClassNotFoundException, SQLException {
		System.out.println("Enter your PNR number");
		long pnr = sc.nextLong();
		sc.nextLine();
		bookingModel.cancelTicket(pnr);
	}

	private void getTicket() throws ClassNotFoundException, SQLException {
		List<TicketDetails> ticketDetials = RailwayTicketBookingDatabase.getInstance().getTicket();
		for (TicketDetails ticketDetails : ticketDetials) {
			System.out.println(ticketDetails);
		}
	}

	private void startBooking() throws ClassNotFoundException, SQLException {
		System.out.println("Enter Your From Station");
		String from = sc.nextLine();
		System.out.println("Enter Your To Station");
		String to = sc.nextLine();
		bookingModel.availableTrain(from, to);

	}

	public void bookPassanger(List<Train> availableTrainList) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		System.out.println("\nEnter the Train Number");
		int trainNumber = sc.nextInt();
		sc.nextLine();
		for (Train train : availableTrainList) {
			if (train.getTrainNo() == trainNumber) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("Enter the Number of passenger you have to add");
			int passangerCount = sc.nextInt();// 2
			sc.nextLine();
			for (int i = 1; i <= passangerCount; i++) {

				System.out.println("\nEnter the Passenger " + i + " Details...");
				System.out.println("Enter the Passanger Name");
				String passangerName = sc.nextLine();
				System.out.println("Enter the Passanger age");
				byte age = sc.nextByte();
				sc.nextLine();
				System.out.println("Enter the Passanger Gender");
				String gender = sc.nextLine();
				bookingModel.bookPassanger(availableTrainList, trainNumber, passangerName, age, gender, passangerCount);
			}
		} else {
			message("Invalid Train Number..try again\n");
			bookPassanger(availableTrainList);
		}
	}
	
	private void ticketDetails() throws ClassNotFoundException, SQLException {
		System.out.println("Enter Your PNR number");
		long pnr = sc.nextLong();
		sc.nextLine();
		bookingModel.viewTicketDetails(pnr);
	}

	public void message(String msg) {
		System.out.println(msg);
	}

	public void paymentProcess(List<Passanger> passangerList, double totalFare, int trainNumber)
			throws ClassNotFoundException, SQLException {
		System.out.println("Your Total Fare : " + totalFare);
		System.out.println("Enter or Pay amount");
		int payAmount = sc.nextInt();// 400
		sc.nextLine();
		bookingModel.paymentProcess(payAmount, totalFare, passangerList, trainNumber);
	}

	public void showTicketDetails(TicketDetails availableDetails) throws ClassNotFoundException, SQLException {
		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
		System.out.println("\nTrain Details :\n");
		System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15s %-15s %-15s %-15s", "Train No", "Train Name",
				"Depature Time", "Arrival Time", "Total Seats", "Fare", "From", "To", "PNR Number");
		System.out.println("\n");
		for (Train availableTrain : trainList) {
			if (availableDetails.getTrainNumber() == availableTrain.getTrainNo()) {
				List<String> routesList = availableTrain.getRoutes();
				System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15s %-15s %-15s %-15s", availableTrain.getTrainNo(),
						availableTrain.getTrainName(), availableTrain.getDepatureTime(),
						availableTrain.getArrivalTime(), availableTrain.getTotalSeats(),
						availableDetails.getTotalFare(), availableTrain.getRoutes().get(0),
						availableTrain.getRoutes().get(routesList.size() - 1), availableDetails.getPnrNumber());
				System.out.println();
				break;
			}
		}
		List<Passanger> passangerList = RailwayTicketBookingDatabase.getInstance().getPassanger();
		System.out.println("\nPassanger Details :\n");
		System.out.printf("%-15s %-15s %-15s %-15s %-15s", "Id", "Name", "age", "Gender", "Status");
		System.out.println("\n");
		for (Passanger passanger : passangerList) {
			if (passanger.getPnrNumber() == availableDetails.getPnrNumber()) {
				System.out.printf("%-15s %-15s %-15s %-15s %-15s", passanger.getPassangerId(),
						passanger.getPassangerName(), passanger.getAge(), passanger.getGender(), passanger.getStatus());
				System.out.println();
			}
		}
	}
}
