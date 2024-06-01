package com.akash.railwayticketbooking.datalayer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.akash.railwayticketbooking.model.Credentials;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RailwayTicketBookingDatabase {
	private String CredentialsFile = "";
	private Credentials credentials;
	private static RailwayTicketBookingDatabase bookingDatabase;
	private List<Credentials> credentialsList;
	private List<Train> trainList;
	private List<TicketDetails> ticketList;

	public RailwayTicketBookingDatabase() {
		credentialsList = new ArrayList<Credentials>();
		trainList = new ArrayList<Train>();
		ticketList = new ArrayList<TicketDetails>();
	}

	ObjectMapper obj = new ObjectMapper();

	public static RailwayTicketBookingDatabase getInstance() {
		if (bookingDatabase == null) {
			bookingDatabase = new RailwayTicketBookingDatabase();
		}
		return bookingDatabase;
	}

	public Credentials getCredentials(String email) {
		for (Credentials credentials : credentialsList) {
			if (credentials.getEmail().equals(email)) {
				return credentials;
			}
		}
		return null;
	}

	public void addCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void addTrain(Train train) {
		trainList.add(train);
	}

	public List<Train> getTrain() {
		return trainList;
	}

	public void addTickets(TicketDetails ticketDetails) {
		ticketList.add(ticketDetails);
	}

	public List<TicketDetails> getTicket() {
		return ticketList;
	}
}
