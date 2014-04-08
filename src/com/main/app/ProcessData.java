package com.main.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessData {

	@Bean
	public ProcessData processData(){
		
		return new ProcessData();
	}
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
	
		try {
			String arr[] = data.split(",");
			if (arr[0].equals("R") ) {
				return "RFID";
			} 
			else if (arr[0].equals("G") ) {
				return "GPS";
			} 
			else
				return null;
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
}
