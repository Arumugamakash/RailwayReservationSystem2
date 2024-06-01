package com.akash.railwayticketbooking.model;

public class TicketDetails {
	Train train;
	Passanger passanger;
	long pnrNumber;
	String status;

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Passanger getPassanger() {
		return passanger;
	}

	public void setPassanger(Passanger passanger) {
		this.passanger = passanger;
	}

	public long getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(long pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TicketDetails [train=" + train + ", passanger=" + passanger + ", pnrNumber=" + pnrNumber + ", status="
				+ status + "]";
	}

}
