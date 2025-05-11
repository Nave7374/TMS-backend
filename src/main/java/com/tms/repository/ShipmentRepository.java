package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // Additional custom queries can be added here if needed
}
