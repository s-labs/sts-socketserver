package com.sts.rfid.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Query;

import com.sts.rfid.model.RfidEntity;

@Configuration
public class RfidDaoImpl  implements RfidDao{

	@Bean
	public RfidDaoImpl rfidDaoImpl(){
		
		return new RfidDaoImpl();
	}
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public RfidEntity getRfidByNumber(String rfid_number) {
		createRfidCollection(RfidEntity.class);
		
		return mongoTemplate.findOne(new Query(where("rfid_number").is(rfid_number).and("status").is("notavailable")),RfidEntity.class);
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public void createRfidCollection(@SuppressWarnings("rawtypes") Class cls) {
		if(!mongoTemplate.collectionExists(cls)){
			
			mongoTemplate.createCollection(cls);
		}
		
	}

	

	

}
