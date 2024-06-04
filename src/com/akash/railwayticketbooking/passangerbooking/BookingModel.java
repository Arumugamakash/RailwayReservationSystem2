package com.akash.railwayticketbooking.passangerbooking;

import java.sql.SQLException;
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
	private List<Passanger> passangerList;
	private Passanger passanger;
	int i = 1;

	public BookingModel(BookingView bookingView) {
		availableTrain = new ArrayList<Train>();
		passangerList=new ArrayList<Passanger>();
		this.bookingView = bookingView;
	}

	public void availableTrain(String from, String to) throws ClassNotFoundException, SQLException {
		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
		boolean flag = true;
		for (Train train : trainList) {
			List<String> routes = train.getRoutes();
			if (routes.get(0).equalsIgnoreCase(from) && routes.get(routes.size() - 1).equalsIgnoreCase(to)) {
				flag = false;
				availableTrain.add(train);
			}
		}
		if (flag) {
			System.out.println("Currently No trains are Available");
		} else {
			availableTrains(availableTrain);
		}
	}

	private void availableTrains(List<Train> trainsList) throws ClassNotFoundException, SQLException {
		System.out.println("\n\tAvailable Trains\n");
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-25s", "Train No", "Train Name", "Depature Time",
				"Arrival Time", "Total Seats", "Fare", "Routes");
		System.out.println("\n");
		for (Train availableTrain : availableTrain) {
			System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-20s", availableTrain.getTrainNo(),
					availableTrain.getTrainName(), availableTrain.getDepatureTime(), availableTrain.getArrivalTime(),
					availableTrain.getTotalSeats(), availableTrain.getFare(), availableTrain.getRoutes());
			System.out.println();
		}
		bookingView.bookPassanger(trainsList);
	}

	public void bookPassanger(List<Train> trainsList, int trainNumber, String passangerName, byte age, String gender,
			int passangerCount) throws ClassNotFoundException, SQLException {
		if (i <= passangerCount) {
			passanger=new Passanger();
			System.out.println(i);
			//passanger.setPassangerId();
			passanger.setPassangerName(passangerName);
			passanger.setAge(age);
			passanger.setGender(gender);
			passangerList.add(passanger);
			if (i==passangerCount) {
				for (Train train : trainsList) {
					double totalFare = passangerCount * (train.getFare());
					bookingView.paymentProcess(passangerList, totalFare, trainNumber);
				}
			}
			i++;
		}
	}

	public void paymentProcess(int payAmount, double totalFare, List<Passanger> passangerList, int trainNumber)
			throws ClassNotFoundException, SQLException {

		if ((double) payAmount == totalFare) {
			bookingView.message("Payment Added Successfully");
			String status = "CNF";
			addTicket(passangerList, status, trainNumber, totalFare);
		}
		else {
			bookingView.message("<<<<<<Invalid Balance>>>>>");
		}
	}

	private void addTicket(List<Passanger> passangerList, String status, int trainNumber, double totalFare)
			throws ClassNotFoundException, SQLException {
		TicketDetails ticketDetails = new TicketDetails();
		long pnr=(int) (Math.random() * 99 + 99);
		ticketDetails.setPnrNumber(pnr);
//		ticketDetails.setStatus(status);
		ticketDetails.setTrainNumber(trainNumber);
		RailwayTicketBookingDatabase.getInstance().addTickets(ticketDetails,totalFare);
		for (Passanger passanger : passangerList) {
			passanger.setPassangerId();
			passanger.setStatus(status);
			passanger.setPnrNumber(pnr);
			RailwayTicketBookingDatabase.getInstance().addPassanger(passanger);
		}
		bookingView.message("Tickets Added Succesfully");
	}

	public void viewTicketDetails(long pnr) throws ClassNotFoundException, SQLException {
		boolean flag = true;
		TicketDetails availableDetails=new TicketDetails();
//		List<TicketDetails> ticketDetails = new ArrayList<TicketDetails>();
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		for (TicketDetails details : ticketList) {
			if (details.getPnrNumber() == pnr) {
				availableDetails= details;
				flag = false;
				break;
			}
		}
		if (flag) {
			bookingView.message("Your PNR number is wrong");
		} else {
			System.out.println("\nTicket Details :\n");
			bookingView.showTicketDetails(availableDetails);
		}
	}

	public void cancelTicket(long pnr) throws ClassNotFoundException, SQLException {
		boolean flag = true;
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		List<Passanger>passangerList=RailwayTicketBookingDatabase.getInstance().getPassanger();
		for (TicketDetails details : ticketList) {
			if (details.getPnrNumber() == pnr) {
				System.out.println("Do you want to cancel the ticket\n yes or No");
				String option = sc.nextLine();
				if (option.equalsIgnoreCase("yes")) {
					RailwayTicketBookingDatabase.getInstance().removeTickets(details);
					for (Passanger passanger : passangerList) {
						if (passanger.getPnrNumber()==pnr) {
							RailwayTicketBookingDatabase.getInstance().removePassanger(passanger);
						}
					}
					bookingView.message("Ticket cancel Successfully...Your refund"+details.getTotalFare() +"will be proceed soon");
					flag = false;
					return;
				} else {
					bookingView.message("Thank u Enjoy your journey");
					return;
				}
			}
		}
		if (flag) {
			bookingView.message("PNR Number is wrong");
		}
	}

	public void listOfTrainRoutes(int trainNo) throws ClassNotFoundException, SQLException {
		List<Train>trains= RailwayTicketBookingDatabase.getInstance().getTrain();
		for (Train train : trains) {
			if (trainNo==train.getTrainNo()) {
				List<String>trainRoutes=train.getRoutes();
				System.out.println(trainRoutes);
			}
		}
	}
}
