package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    // You can add custom queries here if needed
}
