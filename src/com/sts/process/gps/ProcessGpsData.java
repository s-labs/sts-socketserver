package com.sts.process.gps;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessGpsData {
	private static final Logger logger = Logger.getLogger(ProcessGpsData.class);
	private ApplicationContext context = null;
	private ReceivedGpsData gps = null;

	@Bean
	public ProcessGpsData processGpsData() {
		return new ProcessGpsData();
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void setGps(ReceivedGpsData gps) {
		this.gps = gps;
	}
	public void processGpsDataReceived(){
				
		try {
			
			if (gps.getFirstSign()=='N' ||gps.getFirstSign()=='S' ) {
				if (gps.getSecondSign()=='E'  ||gps.getSecondSign()=='W' ) {

					GpsCoordinateConverter gpsCoordinateConverter=context.getBean(GpsCoordinateConverter.class);
					gpsCoordinateConverter.setCoordinate(gps.getLattitude());
					Double processed_lattitude=gpsCoordinateConverter.convertCoordinate();
					gpsCoordinateConverter.setCoordinate(gps.getLongitude());
					Double processed_longitude=gpsCoordinateConverter.convertCoordinate();
					
					String signs[]=gpsCoordinateConverter.assignSigns(gps.getFirstSign().toString(),gps.getSecondSign().toString()).split(",");
					logger.info("Gps data after processed is: [ "+(signs[0]+processed_lattitude)+" , "+(signs[1]+processed_longitude)+" ]");
					
				} 
				else{
					logger.info("Gps data recieved [ "+gps+" ] is invalid... ==> ignoring");
				}

			} else {
				logger.info("Gps data recieved [ "+gps+" ] is invalid... ==> ignoring");
			}
		} catch (Exception e) {
			logger.info("Gps data recieved [ "+gps+" ] is invalid... ==> ignoring");
		}
	}
}
