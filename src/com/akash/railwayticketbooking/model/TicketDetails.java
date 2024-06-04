package com.akash.railwayticketbooking.model;

public class TicketDetails {
	int trainNumber;
//	Passanger passanger;
	long pnrNumber;
	double totalFare;

	
//	public Train getTrain() {
//		return train;
//	}
//
//	public void setTrain(Train train) {
//		this.train = train;
//	}

//	public Passanger getPassanger() {
//		return passanger;
//	}
//
//	public void setPassanger(Passanger passanger) {
//		this.passanger = passanger;
//	}

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
