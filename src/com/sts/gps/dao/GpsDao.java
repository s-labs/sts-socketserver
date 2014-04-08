package com.sts.gps.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sts.gps.model.GpsEntity;
import com.sts.process.gps.ReceivedGpsData;
@Repository
public interface GpsDao {

	public List<GpsEntity> getAllCoordinates();
	public void insertGpsData(ReceivedGpsData recv);
	public void createRfidCollection(Class<?> cls);
}
