package com.sts.rfid.dao;


import org.springframework.stereotype.Repository;

import com.sts.rfid.model.RfidEntity;
@Repository
public interface RfidDao {

	public RfidEntity getRfidByNumber(String rfid_number);
	public void createRfidCollection(Class<?> cls);
	
}
