package com.sts.services;

import com.sts.dao.GpsDao;



public interface GpsService {

	public void insertGpsData(GpsDao gpsDao);
	public GpsDao getGpsData();
}
