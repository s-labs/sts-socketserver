package com.sts.process.rfid;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sts.rfid.dao.RfidDaoImpl;
import com.sts.rfid.model.RfidEntity;


@Configuration
public class ProcessRfidData {
	private static final Logger logger = Logger.getLogger(ProcessRfidData.class);
	private ApplicationContext context=null;
	private ReceivedRfidData rfid=null;
	@Bean
	public ProcessRfidData processRfidData(){
		
		return new ProcessRfidData();
	}
	public ReceivedRfidData getRfid() {
		return rfid;
	}
	
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	public void setRfid(ReceivedRfidData rfid) {
		this.rfid = rfid;
	}
	
	public void processRfidDataReceived(){
		RfidDaoImpl rfidDaoImpl=context.getBean(RfidDaoImpl.class);
		RfidEntity rfidEntity=rfidDaoImpl.getRfidByNumber(rfid.getRfid_number());
		if(rfidEntity==null){
			logger.info("RFID data recieved [ "+rfid+" ] doesnot exists in DB ==> Ignoring data...");
		}
		else{
			logger.info("RFID data recieved [ "+rfid+" ] matched with [ "+rfidEntity+" ]");
		}
		
	}
}
