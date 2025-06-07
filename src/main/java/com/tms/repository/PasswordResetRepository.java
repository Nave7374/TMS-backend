package com.tms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.entity.PasswordResetToken;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {

	Optional<PasswordResetToken> findByToken(String token);
	
	boolean existsByToken(String token);
	
}