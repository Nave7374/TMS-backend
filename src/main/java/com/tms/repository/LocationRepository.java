package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tms.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
   
}
