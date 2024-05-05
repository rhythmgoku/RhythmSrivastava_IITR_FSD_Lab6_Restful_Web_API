package com.greatlearning.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentmanagement.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	public Users findByUsername(String s);
}