package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.AcademixRepository;
@Service
public class UserServiceImplementation implements UserService{
	
	AcademixRepository repo;
	@Autowired
	public UserServiceImplementation(AcademixRepository repo) {
		super();
		this.repo=repo;
	}	

	@Override
	public String addUser(User user) {
		repo.save(user);
		return "student added successfully!";
	}

	@Override
	public boolean checkMail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public boolean validate(String email, String password) {
		if(repo.existsByEmail(email))
		{
			User u=repo.getByEmail(email);
			String dbPassword=u.getPassword();
			if(password.equals(dbPassword))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
 	}

	@Override
	public User getUser(String email) {
		return repo.getByEmail(email);
	}

	@Override
	public String getUserRole(String email) {
		User u=repo.getByEmail(email);
		return u.getRole();
	}

	@Override
	public void updateUser(User user) {
		repo.save(user);
	}

}
