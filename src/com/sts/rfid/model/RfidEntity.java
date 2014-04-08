package com.sts.rfid.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rfids")
public class RfidEntity {

	private String rfid_number;
	private String type;
	private String status;
	private String allocated_to;
	private String allocated_date;
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
	public String getAllocated_date() {
		return allocated_date;
	}
	public void setAllocated_date(String allocated_date) {
		this.allocated_date = allocated_date;
	}
	@Override
	public String toString() {
		return "RfidEntity [rfid_number=" + rfid_number + ", type=" + type
				+ ", status=" + status + ", allocated_to=" + allocated_to
				+ ", allocated_date=" + allocated_date + "]";
	}
	
	
}
