package com.sts.gps.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sts.gps.model.GpsEntity;
import com.sts.process.gps.ReceivedGpsData;

@Configuration
public class GpsDaoImpl implements GpsDao{

	@Autowired
	GpsEntity gpsEntity;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public void setGpsEntity(GpsEntity gpdEntity) {
		this.gpsEntity = gpdEntity;
	}
	@Bean
	public GpsDaoImpl gpsDaoImpl(){
		
		return new GpsDaoImpl();
	}
	@Override
	public List<GpsEntity> getAllCoordinates() {
	
		return null;
	}

	@Override
	public void insertGpsData(ReceivedGpsData recv) {
	
		createRfidCollection(GpsEntity.class);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void createRfidCollection(@SuppressWarnings("rawtypes") Class cls) {
		if(!mongoTemplate.collectionExists(cls)){
			
			mongoTemplate.createCollection(cls);
		}
		
	}
}
