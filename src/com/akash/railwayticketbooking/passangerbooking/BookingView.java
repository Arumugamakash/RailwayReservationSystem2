package com.akash.railwayticketbooking.passangerbooking;

import java.util.List;
import java.util.Scanner;

import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.model.Passanger;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;

public class BookingView {
	Scanner sc = new Scanner(System.in);
	private BookingModel bookingModel;

	public BookingView() {
		bookingModel = new BookingModel(this);
	}

	public void start() {
		System.out.println("\n\tWelcome To IRCTC");
		while (true) {
			System.out.println("\n1.Booking\n2.PNR Status\n3.Cancel Tickets\nExit\nEnter Your choice");
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
			case "7":
				getTicket();
				break;
			default:
				System.out.println("Invalid option try Again...");
				start();
			}
		}
	}

	private void cancelTickets() {
		System.out.println("Enter your PNR number");
		long pnr = sc.nextLong();
		sc.nextLine();
		bookingModel.cancelTicket(pnr);
	}

	private void getTicket() {
		List<TicketDetails> ticketDetials = RailwayTicketBookingDatabase.getInstance().getTicket();
		for (TicketDetails ticketDetails : ticketDetials) {
			System.out.println(ticketDetails);
		}
	}

	private void startBooking() {
		System.out.println("Enter Your From Station");
		String from = sc.nextLine();
		System.out.println("Enter Your To Station");
		String to = sc.nextLine();
		bookingModel.availableTrain(from, to);

	}

	public void bookPassanger(List<Train> trainsList) {
		System.out.println("Enter the Train Number");
		int trainNumber = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Number of passenger you have to add");
		int passangerCount = sc.nextInt();// 2
		sc.nextLine();
		for (int i = 1; i <= passangerCount; i++) {
			System.out.println("\nEnter the Passenger " + i + " Details...");
			System.out.println("Enter the Passanger Name");
			String passangerName = sc.nextLine();
			System.out.println("Enter the Passanger age");
			byte age = sc.nextByte();
			System.out.println("Enter the Passanger Gender");
			char gender = sc.next().charAt(0);
			sc.nextLine();
			bookingModel.bookPassanger(trainsList, trainNumber, passangerName, age, gender, passangerCount);
		}

	}

	public void paymentProcess(Passanger passanger, double totalFare, Train train) {
		System.out.println("Your Total Fare : " + totalFare);
		System.out.println("Enter or Pay amount");
		int payAmount = sc.nextInt();
		sc.nextLine();
		bookingModel.paymentProcess(payAmount, totalFare, passanger, train);
	}

	private void ticketDetails() {
		System.out.println("Enter Your PNR number");
		long pnr = sc.nextLong();
		sc.nextLine();
		bookingModel.viewTicketDetails(pnr);
	}

	public void message(String msg) {
		System.out.println(msg);
	}
}
