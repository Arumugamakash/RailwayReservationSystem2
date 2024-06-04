package com.akash.railwayticketbooking.addtrain;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.akash.railwayticketbooking.datalayer.RailwayTicketBookingDatabase;
import com.akash.railwayticketbooking.model.Passanger;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;
import com.akash.railwayticketbooking.model.TrainRoutes;

public class TrainModel {

	private TrainView trainView;
	private Train train;
	private TrainRoutes trainRoutes;

	public TrainModel(TrainView trainView) {
		this.trainView = trainView;
		train = new Train();
		trainRoutes = new TrainRoutes();
	}

	public void addTrain(int trainNo, String trainName, String depature, String arrival, int seats, double fare,
			List<String> routes) throws ClassNotFoundException, SQLException {

		train.setTrainNo(trainNo);
		train.setTrainName(trainName);
		train.setDepatureTime(depature);
		train.setArrivalTime(arrival);
		train.setTotalSeats(seats);
		train.setFare(fare);
		trainRoutes.setTrainNo(trainNo);
		trainRoutes.setTrainRoutes(routes);
		RailwayTicketBookingDatabase.getInstance().addTrain(train, trainRoutes);
		trainView.message("Train Added Successfully");
	}

	public void getAllTrain() throws ClassNotFoundException, SQLException {
		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
		boolean flag = true;
		for (Train train : trainList) {
			System.out.println(train);
			flag = false;
		}
		if (flag) {
			System.out.println("no trains are added ");
		}
	}
	
	public void addTrainRoutes(List<String> routes) {
		train.setRoutes(routes);
	}

	public void bookedTickets() throws ClassNotFoundException, SQLException {
//		Set<Integer> set = new HashSet<Integer>();
		System.out.println("Booked Ticketes details...\n");
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
		System.out.println("Total booked Tickets : " + ticketList.size());
		System.out.println("Train Details :\n");
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-18s", "Train No", "Train Name", "Depature Time",
				"Arrival Time", "Total Seats", "Fare", "Routes");
		System.out.println("\n");
		for (TicketDetails ticketDetails : ticketList) {
//			int trainNo = ticketDetails.getTrainNumber();
//			if (set.add(trainNo)) {
			for (Train availableTrain : trainList) {
//					if (train.getTrainNo() == trainNo) {
				System.out.printf("%-15s %-25s %-15s %-15s %-15s %-15s %-20s", availableTrain.getTrainNo(),
						availableTrain.getTrainName(), availableTrain.getDepatureTime(),
						availableTrain.getArrivalTime(), availableTrain.getTotalSeats(), availableTrain.getFare(),
						availableTrain.getRoutes());
				System.out.println();
//					}
//				}
			}
		}
		System.out.println("passanger Details : \n");
		System.out.printf("%-15s %-15s %-15s %-15s %-15s", "Id", "Name", "age", "Gender", "Status");
		System.out.println("\n");
		List<Passanger> passangerList = RailwayTicketBookingDatabase.getInstance().getPassanger();
		for (TicketDetails ticketDetails : ticketList) {
			for (Passanger passanger : passangerList) {
				if (ticketDetails.getPnrNumber() == passanger.getPnrNumber()) {
					System.out.printf("%-15s %-15s %-15s %-15s %-15s", passanger.getPassangerId(),
							passanger.getPassangerName(), passanger.getAge(), passanger.getGender(),
							passanger.getStatus());
					System.out.println();
				}
			}
		}

	}

	public void searchPassanger(int id) throws ClassNotFoundException, SQLException {
		boolean flag = true;
		List<Passanger> passangerList = RailwayTicketBookingDatabase.getInstance().getPassanger();
		for (Passanger passanger : passangerList) {
			if (passanger.getPassangerId() == id) {
				flag = false;
				System.out.println("passanger Details : \n");
				System.out.printf("%-15s %-15s %-15s %-15s %-15s", "Id", "Name", "age", "Gender", "Status");
				System.out.println("\n");

				System.out.printf("%-15s %-15s %-15s %-15s %-15s", passanger.getPassangerId(),
						passanger.getPassangerName(), passanger.getAge(), passanger.getGender(), passanger.getStatus());
				System.out.println();

			}
		}
		if (flag) {
			System.out.println("passangers is not present");
		}
	}

	public void changeStatus(long pnr) throws ClassNotFoundException, SQLException {
		boolean flag=false;
		List<Passanger>passangerList=RailwayTicketBookingDatabase.getInstance().getPassanger();
		for (Passanger passanger : passangerList) {
			if(passanger.getPnrNumber()==pnr) {
				flag=true;
				RailwayTicketBookingDatabase.getInstance().removePassanger(passanger);
				passanger.setStatus("Cancel");
				RailwayTicketBookingDatabase.getInstance().addPassanger(passanger);
			}
		}
		if (flag) {
			trainView.message("Status Changed Successfully...");
		}
	}

}
