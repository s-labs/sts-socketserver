package com.sts.services;

import com.sts.dao.DailyGpsDao;



public interface DailyGpsService {

	public void insertGpsData(DailyGpsDao gpsDao);
	public DailyGpsDao getGpsData();
}
