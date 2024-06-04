package com.akash.railwayticketbooking.model;

public class TicketDetails {
	int trainNumber;
	long pnrNumber;
	double totalFare;
	
	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public long getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(long pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	@Override
	public String toString() {
		return "TicketDetails :\n [trainNumber=" + trainNumber + ", pnrNumber=" + pnrNumber + ", TotalFare="+totalFare+"]";
	}

}
