package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.ShipmentHistory;

@Repository
public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Long>  {

}
