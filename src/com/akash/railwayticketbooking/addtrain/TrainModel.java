package com.akash.railwayticketbooking.addtrain;

import java.sql.SQLException;
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
		trainRoutes=new TrainRoutes();
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
		RailwayTicketBookingDatabase.getInstance().addTrain(train,trainRoutes);
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

//	public void checkTrain(int trainNumer) throws ClassNotFoundException, SQLException {
//		List<Train> trainList = RailwayTicketBookingDatabase.getInstance().getTrain();
//		boolean flag = true;
//		for (Train train : trainList) {
//			if (train.getTrainNo() == trainNumer) {
//				flag = false;
//				System.out.println("Train available");
//				trainView.addTrainRoutes(train);
//
//			}
//		}
//	}

	public void addTrainRoutes(List<String> routes) {
		train.setRoutes(routes);
	}

	public void bookedTickets() throws ClassNotFoundException, SQLException {
		System.out.println("Booked Ticketes details...");
		List<TicketDetails> ticketList = RailwayTicketBookingDatabase.getInstance().getTicket();
		System.out.println("Total booked Tickets : " + ticketList.size());
		for (TicketDetails ticketDetails : ticketList) {
			System.out.println(ticketDetails);
		}
	}

	public void searchPassanger(int id) {
		Passanger p=new Passanger();
		if(p.getPassangerId()==id) {
			System.out.println(p);
		}
		else {
			System.out.println("passangers is not present");
		}
	}

}
