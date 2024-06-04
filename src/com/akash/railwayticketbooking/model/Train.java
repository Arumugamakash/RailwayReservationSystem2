package com.akash.railwayticketbooking.model;

import java.util.List;

public class Train {
	int trainNo;
	String trainName;
	String depatureTime;
	String arrivalTime;
	int totalSeats;
	double fare;
	List<String> Routes;

	public List<String> getRoutes() {
		return Routes;
	}

	public void setRoutes(List<String> routes) {
		Routes = routes;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDepatureTime() {
		return depatureTime;
	}

	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Train [trainNo=" + trainNo + ", trainName=" + trainName + ", depatureTime=" + depatureTime
				+ ", arrivalTime=" + arrivalTime + ", totalSeats=" + totalSeats + ", fare=" + fare + ", Routes="
				+ Routes + "]";
	}

}
