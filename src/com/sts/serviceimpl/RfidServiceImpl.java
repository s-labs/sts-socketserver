package com.sts.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


import com.sts.dao.RfidDao;
import com.sts.services.RfidService;

public class RfidServiceImpl implements RfidService {

	private MongoTemplate mongoTemplate;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public RfidDao getRfidByNumber(String number) {

		if (!mongoTemplate.collectionExists(RfidDao.class)) {
			mongoTemplate.createCollection(RfidDao.class);
			
		}
		return null;
	}

	@Override
	public RfidDao getRfidById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
