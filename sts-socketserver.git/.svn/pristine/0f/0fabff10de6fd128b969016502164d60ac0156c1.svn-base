package com.main.app;

public class ProcessData {

	public boolean isDatavalid(String data) {
		data=data.trim();
		try {
			String arr[] = data.split(",");
			if (arr[0].equals("R") || arr[0].equals("G")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
	
	public String findDataType(String data){
		data=data.trim();
		data=data.trim();
		try {
			String arr[] = data.split(",");
			if (arr[0].equals("R") ) {
				return "RFID";
			} 
			else if (arr[0].equals("G") ) {
				return "GPRS";
			} 
			else
				return null;
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
}
