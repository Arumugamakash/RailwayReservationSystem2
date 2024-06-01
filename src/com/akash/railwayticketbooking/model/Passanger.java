package com.akash.railwayticketbooking.model;

public class Passanger {
	int passangerId;
	String passangerName;
	byte age;
	char gender;

	public int getPassangerId() {
		return passangerId;
	}

	public void setPassangerId(int passangerId) {
		this.passangerId = passangerId;
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Passanger [passangerId=" + passangerId + ", passangerName=" + passangerName + ", age=" + age
				+ ", gender=" + gender + "]";
	}

}
