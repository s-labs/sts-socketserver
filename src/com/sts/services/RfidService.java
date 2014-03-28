package com.sts.services;

import com.sts.dao.RfidDao;

public interface RfidService {

	public RfidDao getRfidByNumber(String number);
	public RfidDao getRfidById(String id);
	
}
