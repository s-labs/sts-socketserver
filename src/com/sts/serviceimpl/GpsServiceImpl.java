package com.sts.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.sts.dao.DailyGpsDao;
import com.sts.services.DailyGpsService;

public class GpsServiceImpl implements DailyGpsService {

	private MongoTemplate mongoTemplate;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void insertGpsData(DailyGpsDao gpsDao) {

		// System.out.println(mongoTemplate);
		if (!mongoTemplate.collectionExists(DailyGpsDao.class)) {
			mongoTemplate.createCollection(DailyGpsDao.class);
			// System.out.println("created collection....");
		}

		mongoTemplate.insert(gpsDao);
	}

	@Override
	public DailyGpsDao getGpsData() {
		// TODO Auto-generated method stub
		return null;
	}

}
