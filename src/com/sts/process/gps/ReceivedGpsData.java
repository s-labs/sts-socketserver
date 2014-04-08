package com.sts.process.gps;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceivedGpsData {

	@Bean
	public ReceivedGpsData receivedGpsData() {

		return new ReceivedGpsData();
	}
	
	private Character firstSign;
	private Character secondSign;
	private double lattitude;
	private double longitude;
	private String time;
	private String date;
	private String bus_id;
	public Character getFirstSign() {
		return firstSign;
	}
	public void setFirstSign(Character firstSign) {
		this.firstSign = firstSign;
	}
	public Character getSecondSign() {
		return secondSign;
	}
	public void setSecondSign(Character secondSign) {
		this.secondSign = secondSign;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	@Override
	public String toString() {
		return "ReceivedGpsData [firstSign=" + firstSign + ", secondSign="
				+ secondSign + ", lattitude=" + lattitude + ", longitude="
				+ longitude + ", time=" + time + ", date=" + date + ", bus_id="
				+ bus_id + "]";
	}
	
}
