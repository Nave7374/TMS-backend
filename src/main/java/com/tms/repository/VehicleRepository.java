package com.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.DriverStatus;
import com.tms.entity.Vehicle;
import com.tms.entity.VehicleStatus;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	List<Vehicle> findByStatus(VehicleStatus status);
	List<Vehicle> findByDriverstatusAndStatus(DriverStatus driverstatus,VehicleStatus status);
	
}