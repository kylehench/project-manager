package com.kylehench.projectmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kylehench.projectmanager.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	// this method retrieves all the users from the database
    List<User> findAll();
    // this method retrieves one user from the database
    Optional<User> findByEmail(String email);
}