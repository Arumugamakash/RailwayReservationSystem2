package com.akash.railwayticketbooking.model;

import java.util.List;

public class TrainRoutes {
	int trainNo;
	List<String> trainRoutes;

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public List<String> getTrainRoutes() {
		return trainRoutes;
	}

	public void setTrainRoutes(List<String> trainRoutes) {
		this.trainRoutes = trainRoutes;
	}

	@Override
	public String toString() {
		return "TrainRoutes [trainRoutes=" + trainRoutes + "]";
	}

}
