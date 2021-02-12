package com.javastackspaceit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javastackspaceit.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	List<UserDetails> findAll();
}
