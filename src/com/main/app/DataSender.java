package com.main.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSender {

	@Bean
	public DataSender dataSender(){
		return new DataSender();
	}
	private String data;	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
