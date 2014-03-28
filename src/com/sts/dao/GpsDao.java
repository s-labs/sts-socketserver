package com.sts.dao;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="gpsdata")
public class GpsDao {

	private Double Lattitude;
	private Double Longitude;
	private String arrived_time;
	private String date;
	public Double getLattitude() {
		return Lattitude;
	}
	public void setLattitude(Double lattitude) {
		Lattitude = lattitude;
	}
	public Double getLongitude() {
		return Longitude;
	}
	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}
	public String getArrived_time() {
		return arrived_time;
	}
	public void setArrived_time(String arrived_time) {
		this.arrived_time = arrived_time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "GpsDao [Lattitude=" + Lattitude + ", Longitude=" + Longitude
				+ ", arrived_time=" + arrived_time + ", date=" + date + "]";
	}
	
	
}
