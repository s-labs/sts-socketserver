package com.sts.dao;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rfids")
public class RfidDao {

	private String rfid_number;
	private String type;
	private String status;
	private String allocated_to;
	private String allocated_time;
	public String getRfid_number() {
		return rfid_number;
	}
	public void setRfid_number(String rfid_number) {
		this.rfid_number = rfid_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAllocated_to() {
		return allocated_to;
	}
	public void setAllocated_to(String allocated_to) {
		this.allocated_to = allocated_to;
	}
	public String getAllocated_time() {
		return allocated_time;
	}
	public void setAllocated_time(String allocated_time) {
		this.allocated_time = allocated_time;
	}
	@Override
	public String toString() {
		return "RfidDao [rfid_number=" + rfid_number + ", type=" + type
				+ ", status=" + status + ", allocated_to=" + allocated_to
				+ ", allocated_time=" + allocated_time + "]";
	}
	
}
