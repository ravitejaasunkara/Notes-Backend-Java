package com.notes.saver.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notes.saver.core.entities.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
	List<Auth> findByUsername(String username);

	Auth findByEmail(String email);
		
//	@Query("SELECT a from Auth where a.username = :username AND a.password = :password")
//	Auth findUser(@Param("username") String username,@Param("password") String password);
}
