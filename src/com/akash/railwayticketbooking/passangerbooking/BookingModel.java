package com.akash.railwayticketbooking.passangerbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.model.Passanger;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;

public class BookingModel {
	Scanner sc = new Scanner(System.in);
	private BookingView bookingView;
	private List<Train> availableTrain;
	private Passanger passanger;
	int i = 1;

	public BookingModel(BookingView bookingView) {
		availableTrain = new ArrayList<Train>();
		passanger = new Passanger();
		this.bookingView = bookingView;
	}

	public void availableTrain(String from, String to) {
		int fromIndex = 0;
		int toIndex = 0;
		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
		boolean flag = true;
		for (Train train : trainList) {
			List<String> routes = train.getRoutes();
			if (routes.contains(from) && routes.contains(to)) {
				for (int i = 0; i < routes.size(); i++) {
					if (routes.get(i).equalsIgnoreCase(from))
						fromIndex = i;
					if (routes.get(i).equalsIgnoreCase(to))
						toIndex = i;
				}
				if (fromIndex < toIndex) {
					availableTrain.add(train);
					flag = false;
				}
			}
		}
		if (flag) {
			System.out.println("Currently No trains are Available");
		} else {
			availableTrains(availableTrain);
		}
	}

	private void availableTrains(List<Train> trainsList) {
		for (Train availableTrain : availableTrain) {
			System.out.println(availableTrain);
		}
		bookingView.bookPassanger(trainsList);
	}

	public void bookPassanger(List<Train> trainsList, int trainNumber, String passangerName, byte age, char gender,
			int passangerCount) {
		if (i <= passangerCount) {
			System.out.println(i);
			passanger.setPassangerId((int) (Math.random()*99+99));
			passanger.setPassangerName(passangerName);
			passanger.setAge(age);
			passanger.setGender(gender);
			if (i == passangerCount) {
				for (Train train : trainsList) {
					if (trainNumber == train.getTrainNo()) {
						double totalFare = passangerCount * (train.getFare());
						bookingView.paymentProcess(passanger, totalFare, train);
					}
				}
			}
			i++;
		}
	}

	public void paymentProcess(int payAmount, double totalFare, Passanger passanger, Train train) {

		if ((double) payAmount == totalFare) {
			bookingView.message("Payment Added Successfully");
			String status = "CNF";
			addTicket(passanger, status, train, totalFare);
		}
	}

	private void addTicket(Passanger passanger, String status, Train train, double totalFare) {
		TicketDetails ticketDetails = new TicketDetails();
		ticketDetails.setPassanger(passanger);
		ticketDetails.setPnrNumber((int) (Math.random() * 99 + 99));
		ticketDetails.setStatus(status);
		ticketDetails.setTrain(train);
		RailwayTicketBookingDatabase.getInstance().addTickets(ticketDetails);
		System.out.println("Payment added Successfully");
	}

	public void viewTicketDetails(long pnr) {
		boolean flag = true;
		List<TicketDetails> ticketDetails = new ArrayList<TicketDetails>();
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		for (TicketDetails details : ticketList) {
			if (details.getPnrNumber() == pnr) {
				ticketDetails.add(details);
				flag = false;
			}
		}
		if (flag) {
			bookingView.message("PNR number is wrong");
		} else {
			System.out.println("\nTicket Details :\n");
			System.out.println(ticketDetails);
		}
	}

	public void cancelTicket(long pnr) {
		boolean flag = true;
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		for (TicketDetails details : ticketList) {
			if (details.getPnrNumber() == pnr) {
				System.out.println("Do you want to cancel the ticket\n yes or No");
				String option = sc.nextLine();
				if (option.equalsIgnoreCase("yes")) {
					ticketList.remove(details);
					bookingView.message("Ticket cancel Successfully...Your refund will be proceed soon");
					flag = false;
					return;
				} else {
					bookingView.message("Thank u Enjoy your journey");
					return;
				}
			}
		}
		if (flag) {
			bookingView.message("PNR number is wrong");
		}
	}
}
