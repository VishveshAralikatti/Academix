package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

public interface AcademixRepository extends JpaRepository<User, Integer>{
	boolean existsByEmail(String email);
	//Users getByEmail(String email);

	User getByEmail(String email);
	
	User getByEmailAndPassword(String email,String password);
}
