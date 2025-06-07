package com.tms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.entity.UserShipmentHistory;
import com.tms.repository.UserhistoryRepo;
import com.tms.service.interfaces.UserShipmenthistoryEntityService;

@Service
public class UserShipmentHistoryService implements UserShipmenthistoryEntityService {
	
	@Autowired
	private UserhistoryRepo userhistoryRepo;

	@Override
	public List<SHipmentHistoryForUser> getAllShipmentHistory() {
		List<UserShipmentHistory> list =  userhistoryRepo.findAll();
		List<SHipmentHistoryForUser> l = list.stream().map(i->new SHipmentHistoryForUser(i)).collect(Collectors.toList());
		return l;
	}
	
}