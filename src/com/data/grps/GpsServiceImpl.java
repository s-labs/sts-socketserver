package com.data.grps;


import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class GpsServiceImpl implements GpsService {

	
	
	MongoTemplate mongoTemplate;
	@Override
	public void insertGpsData(GpsDao gpsDao,ApplicationContext context) {
		mongoTemplate= context.getBean("mongoTemplate",MongoTemplate.class);
		mongoTemplate.insert(gpsDao);
	}

	@Override
	public GpsDao getGpsData(ApplicationContext context) {
		mongoTemplate= context.getBean("mongoTemplate",MongoTemplate.class);
		return null;
	}
	
}
