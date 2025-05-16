package com.tms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	Optional<Shipment> findByShipmentNumber(String shipmentNumber);
	
}
