package com.appsdeveloperblog.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	

	UserEntity findByEmail(String email);
}
