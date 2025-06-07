package com.tms.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.tms.entity.ShipmentHistory;
import com.tms.repository.ShipmentHistoryRepository;
import com.tms.service.interfaces.ShipmentHistoryEntityService;

@Service
public class ShipmentHistoryService implements ShipmentHistoryEntityService {

	@Autowired
	private ShipmentHistoryRepository shipmentHistoryRepository;
	
	@Override
	public void save(ShipmentHistory sh) {
		shipmentHistoryRepository.save(sh);
	}
}
