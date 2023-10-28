package com.example.demo.Service;

import com.example.demo.Entity.User;

public interface UserService {
	
	String addUser(User user);
	
	boolean checkMail(String email);
	
	boolean validate(String email, String password);

	User getUser(String email);

	String getUserRole(String email);

	void updateUser(User user);


}
