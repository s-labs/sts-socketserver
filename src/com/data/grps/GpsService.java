package com.data.grps;

import org.springframework.context.ApplicationContext;

public interface GpsService {

	public void insertGpsData(GpsDao gpsDao,ApplicationContext context);
	public GpsDao getGpsData(ApplicationContext context);
}
