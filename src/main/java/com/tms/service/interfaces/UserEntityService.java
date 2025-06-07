package com.tms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.dto.PaginationDto;
import com.tms.entity.User;

public interface UserEntityService {

	Optional<User> findUserByUsername(String username);

	Optional<User> findUserByEmail(String email);

	User saveUser(User user);

	List<User> fetchAll();

	Optional<User> findUserById(Long id);

	List<SHipmentHistoryForUser> getShipmentHistory(Long id);

	User updateByUserUpdateid(Long id, UserUpdate u);

	UserUpdate getUserUpdateById(Long id);

	ResponseEntity<?> deleteUser(Long id);

	PaginationDto<User> findbypage(Integer pageno);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
