package com.sts.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sts.dao.RfidDao;
import com.sts.services.RfidService;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

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
		//System.out.println(mongoTemplate.findOne(new Query(where("rfid_number").is(number)),RfidDao.class));
		return mongoTemplate.findOne(new Query(where("rfid_number").is(number).and("status").is("notavailable")),RfidDao.class);

	}

	@Override
	public RfidDao getRfidById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
