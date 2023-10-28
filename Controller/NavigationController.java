package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nav")
public class NavigationController {
	
	UserService us;
	@Autowired
	public NavigationController(UserService us) {
		super();
		this.us=us;
	}
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	@PostMapping("/addUser")
	public String addUser(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("role") String role)
	{
		boolean emailexists=us.checkMail(email);
		if(emailexists==false)
		{
			User user=new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);
			us.addUser(user);
			
			System.out.println("User registerd successfully!");
			return "redirect:/nav/login";
		}
		else
		{
			System.out.println("User already exists...!");
			return "/registerfail";
		}
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session)
	{
		if(us.checkMail(email))
		{
			boolean val=us.validate(email, password);
			if(val==true)
			{
				User user=us.getUser(email);
				session.setAttribute("loggedInUser", user);
				if(us.getUserRole(email).equals("trainer"))
				{
					return "trainerHome";
				}
				else
				{
					return "studentHome";
				}
			}
			else
			{
				System.out.println("incorrect credentials, try again!");
				return "loginfail";
			}
		}
		else
		{
			return "loginfail";
		}
	}
	@GetMapping("/createCourse")
	public String createCourse()
	{
		return "addCourse";
	}
	@GetMapping("/addLessons")
	public String addLesson()
	{
		return "addLesson";
	}
	@GetMapping("/showCourse")
	public String showCourse()
	{
		return "showCourse";
	}

}
