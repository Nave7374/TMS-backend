package com.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Additional custom queries can be added here if needed
	
	List<Vehicle> findByStatus(VehicleStatus status);
}
