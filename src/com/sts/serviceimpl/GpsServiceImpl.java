package com.sts.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sts.dao.GpsDao;
import com.sts.services.GpsService;

public class GpsServiceImpl implements GpsService {

	
	
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	@Override
	public void insertGpsData(GpsDao gpsDao) {
		
		//System.out.println(mongoTemplate);
		if(!mongoTemplate.collectionExists(GpsDao.class)){
			mongoTemplate.createCollection(GpsDao.class);
			//System.out.println("created collection....");
		}
			
		mongoTemplate.insert(gpsDao);
	}

	
	@Override
	public GpsDao getGpsData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
