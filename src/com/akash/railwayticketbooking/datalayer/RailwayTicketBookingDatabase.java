package com.akash.railwayticketbooking.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.akash.railwayticketbooking.model.Credentials;
import com.akash.railwayticketbooking.model.Passanger;
import com.akash.railwayticketbooking.model.TicketDetails;
import com.akash.railwayticketbooking.model.Train;
import com.akash.railwayticketbooking.model.TrainRoutes;

public class RailwayTicketBookingDatabase {
	private static Connection con;
	private Credentials credentials;
	private static RailwayTicketBookingDatabase bookingDatabase;
	private Set<Credentials> credentialsSet;
	private List<Train> trainList;
	private Set<Train> trainSet;
	private List<TicketDetails> ticketList;

	// Constructor
	public RailwayTicketBookingDatabase() throws ClassNotFoundException, SQLException {
		connection();
		trainSet = new HashSet<Train>();
		credentialsSet = new HashSet<Credentials>();
		trainList = new ArrayList<Train>();
		ticketList = new ArrayList<TicketDetails>();
	}

	// singleton object
	public static RailwayTicketBookingDatabase getInstance() throws ClassNotFoundException, SQLException {
		if (bookingDatabase == null) {
			bookingDatabase = new RailwayTicketBookingDatabase();
		}
		return bookingDatabase;
	}

	//
	public Credentials getCredentials(String validEmail) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Connection conection = connection();
		PreparedStatement ps = conection.prepareStatement("select * from Credentials");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("times");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String name = rs.getString("name");
			boolean isAdmin = rs.getBoolean("isAdmin");

			Credentials credentials = new Credentials();
			credentials.setName(name);
			credentials.setEmail(email);
			credentials.setPassword(password);
			credentials.setAdmin(isAdmin);
			if (credentialsSet.isEmpty()) {
				credentialsSet.add(credentials);
			} else {
				for (Credentials credentials2 : credentialsSet) {
					if (credentialsSet.contains(credentials2)) {
						flag = true;
					}
				}
				if (flag) {
					credentialsSet.add(credentials);
				}

			}
		}
		System.out.println(credentialsSet.size());
		for (Credentials credentials : credentialsSet) {
			System.out.println(credentials.getName());
			if (credentials.getEmail().equals(validEmail)) {
				return credentials;
			}
		}
		return null;
	}

	public void addCredentials(Credentials credentials) throws ClassNotFoundException, SQLException {
		Connection con = connection();
		PreparedStatement p = con.prepareStatement("insert into Credentials values(?,?,?,?)");
		p.setString(1, credentials.getEmail());
		p.setString(2, credentials.getPassword());
		p.setString(3, credentials.getName());
		p.setBoolean(4, credentials.getAdmin());
		p.executeUpdate();
//		con.close();
	}

	public void addTrain(Train train, TrainRoutes trainRoutes) throws SQLException, ClassNotFoundException {

		Connection con = connection();
		PreparedStatement p = con.prepareStatement("insert into Train values(?,?,?,?,?,?)");

		p.setInt(1, train.getTrainNo());
		p.setString(2, train.getTrainName());
		p.setString(3, train.getDepatureTime());
		p.setString(4, train.getArrivalTime());
		p.setInt(5, train.getTotalSeats());
		p.setDouble(6, train.getFare());
		p.executeUpdate();
		p = con.prepareStatement("INSERT INTO TrainRoutes VALUES (?,?)");

		List<String> routes = trainRoutes.getTrainRoutes();
		for (String string : routes) {
			p.setString(1, string);
			p.setInt(2, trainRoutes.getTrainNo());
			p.addBatch();
		}
		p.executeBatch();
//		int[] updateCounts = p.executeBatch();
//		System.out.println("Inserted records count: " + updateCounts.length);
	}

//	@SuppressWarnings("resource")
	public List<Train> getTrain() throws SQLException, ClassNotFoundException {
//		Connection conection = connection();
//		PreparedStatement ps = conection.prepareStatement("select * from Train");
//		ResultSet rs = ps.executeQuery();
//		int trainNo = 0;
//		String trainName = "";
//		String depatureTime = "";
//		String arrivalTime = "";
//		int totalSeats = 0;
//		double fare = 0.0;
//		while (rs.next()) {
//			System.out.println("kok");
//			trainNo = rs.getInt(1);
//			trainName = rs.getString(2);
//			depatureTime = rs.getString(3);
//			arrivalTime = rs.getString(4);
//			totalSeats = rs.getInt(5);
//			fare = rs.getDouble(6);
//			Train train = new Train();
//			train.setTrainNo(trainNo);
//			train.setTrainName(trainName);
//			train.setDepatureTime(depatureTime);
//			train.setArrivalTime(arrivalTime);
//			train.setTotalSeats(totalSeats);
//			train.setFare(fare);
//			List<String> routeList = new ArrayList<String>();
//
//			PreparedStatement ps1 = conection.prepareStatement("select * from TrainRoutes");
//			// 1
////			ps1.setInt(1, trainNo);
//			ResultSet rs_routes = ps.executeQuery();
//
//			while (rs_routes.next()) {
//				if (trainNo == rs.getInt(2)) {
//					routeList.add(rs.getString(1));
//				}
//				rs_routes.close();
//				ps1.close();
////			
//				train.setRoutes(routeList);
////			if (!trainSet.isEmpty()) {
////				for (Train trains : trainSet) {
////					if (trains.getTrainNo() != trainNo) {
////						trainSet.add(train);
////					}
////				}
////			} else {
//				trainSet.add(train);
////			}
//				trainList.addAll(trainSet);
//				for (Train string : trainList) {
//					System.out.println("hi");
//					System.out.println(string);
//				}
//			}
//		}
//
////		trainList.add((Train) trainSet);
//
//		return trainList;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = connection(); // Assume this method gets a connection to the database

			// Fetch train details
			ps = connection.prepareStatement("SELECT * FROM Train");
			rs = ps.executeQuery();

			while (rs.next()) {
				int trainNo = rs.getInt(1);
				String trainName = rs.getString(2);
				String departureTime = rs.getString(3);
				String arrivalTime = rs.getString(4);
				int totalSeats = rs.getInt(5);
				double fare = rs.getDouble(6);

				Train train = new Train();
				train.setTrainNo(trainNo);
				train.setTrainName(trainName);
				train.setDepatureTime(departureTime);
				train.setArrivalTime(arrivalTime);
				train.setTotalSeats(totalSeats);
				train.setFare(fare);

				// Fetch train routes
				PreparedStatement psRoutes = connection.prepareStatement("SELECT * FROM TrainRoutes WHERE TrainNo = ?");
				psRoutes.setInt(1, trainNo);
				ResultSet rsRoutes = psRoutes.executeQuery();

				List<String> routeList = new ArrayList<>();
				while (rsRoutes.next()) {
					routeList.add(rsRoutes.getString(1));
				}
				rsRoutes.close();
				psRoutes.close();

				train.setRoutes(routeList);

				trainSet.add(train);
			}

			trainList.addAll(trainSet);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
//			if (connection != null)
//				connection.close();
		}

		return trainList;
	}

	public void addTickets(TicketDetails ticketDetails, double totalFare) throws ClassNotFoundException, SQLException {
//		ticketList.add(ticketDetails);
		Connection con = connection();
		PreparedStatement p = con.prepareStatement("insert into TicketDetails values(?,?,?)");
		
		p.setInt(1, ticketDetails.getTrainNumber());
		p.setLong(2, ticketDetails.getPnrNumber());
		p.setDouble(3, totalFare);
		p.executeUpdate();
	}

	public List<TicketDetails> getTicket() {
		return ticketList;
	}

	public void addPassanger(Passanger passanger) throws ClassNotFoundException, SQLException {
		System.out.println("jii");
		Connection con = connection();
		PreparedStatement p = con.prepareStatement("insert into Passanger values(?,?,?,?,?,?)");
		p.setInt(1, passanger.getPassangerId());
		p.setString(2, passanger.getPassangerName());
		p.setByte(3, passanger.getAge());
		p.setString(4, passanger.getGender());
		p.setString(5, passanger.getStatus());
		p.setLong(6, passanger.getPnrNumber());
		p.executeUpdate();
	}

	public static Connection connection() throws ClassNotFoundException, SQLException {
		if (con == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_db", "root", "Root");
		}
		return con;
	}

}
