package com.procttor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procttor.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
