package com.sts.gps.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;
@Configuration
@Document(collection="dailygpsdata")
public class GpsEntity {

	@Bean
	public GpsEntity gpsEntity(){
		return new GpsEntity();
	}
	private String bus_id;
	private Double lattitude;
	private Double longitude;
	private String date;
	private String time;
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "GpsEntity [bus_id=" + bus_id + ", lattitude=" + lattitude
				+ ", longitude=" + longitude + ", date=" + date + ", time="
				+ time + "]";
	}
	
}
