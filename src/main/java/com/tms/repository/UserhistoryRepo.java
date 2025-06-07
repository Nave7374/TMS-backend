package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.UserShipmentHistory;

@Repository
public interface UserhistoryRepo extends JpaRepository<UserShipmentHistory, Long>{

}