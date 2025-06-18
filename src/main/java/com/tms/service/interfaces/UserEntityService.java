package com.tms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tms.DAO.SHipmentHistoryForUser;
import com.tms.DAO.Update.UserUpdate;
import com.tms.dto.PaginationDto;
import com.tms.entity.User;

public interface UserEntityService {

	ResponseEntity<User> findUserByUsername(String username);

	ResponseEntity<User> findUserByEmail(String email);

	ResponseEntity<User> saveUser(User user);

	ResponseEntity<List<User>> fetchAll();

	Optional<User> findUserById(Long id);

	ResponseEntity<List<SHipmentHistoryForUser>> getShipmentHistory(Long id);

	ResponseEntity<User> updateByUserUpdateid(Long id, UserUpdate u);

	ResponseEntity<UserUpdate> getUserUpdateById(Long id);

	ResponseEntity<?> deleteUser(Long id);

	ResponseEntity<PaginationDto<User>> findbypage(Integer pageno);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<User> findUserByUsernameOptional(String username);

	Optional<User> findUserByEmailOptional(String email);

	ResponseEntity<List<User>> fetchAllFilterByName();

}
