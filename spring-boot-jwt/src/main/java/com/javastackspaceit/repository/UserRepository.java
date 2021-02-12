package com.javastackspaceit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javastackspaceit.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String username);

}
