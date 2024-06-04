package com.akash.railwayticketbooking.model;

public class Passanger {
	int passangerId;
	String passangerName;
	byte age;
	String gender;
	String status;
	long pnrNumber;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPassangerId() {
		return passangerId;
	}
//
	public void setPassangerId() {
		this.passangerId = (int) (Math.random() * 999 + 999);
	}

	public String getPassangerName() {
		return passangerName;
	}

	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(long pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

}
