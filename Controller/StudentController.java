package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Comments;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Lesson;
import com.example.demo.Entity.User;
import com.example.demo.Service.CommentService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TrainerService;
import com.example.demo.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	UserService us;
	
	@Autowired
	TrainerService ts;
	
	@Autowired
	StudentService ss;
	
	@Autowired
	CommentService cs;
	
	@GetMapping("/purchase")
	public String showCourses(Model model,HttpSession session)
	{
		User user=(User) session.getAttribute("loggedInUser");
		
		List<Course> courseList=ts.courseList();
		model.addAttribute("courseList", courseList);
		model.addAttribute("loggedInUser",user);
		return "purchase";
	}
	
	@GetMapping("/fetchCourses")
	public String fetchCourses(Model model, HttpSession session)
	{
		User loggedUser=(User) session.getAttribute("loggedInUser");
		
		String email=loggedUser.getEmail();
		
		User user=us.getUser(email);
		
		List<Course> courseList=user.getCourses();
		model.addAttribute("courseList", courseList);
		return "myCourses";
	}
	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId")int lessonId,
			Model model,HttpSession session) {
		//	Users user = (Users) session.getAttribute("loggedInUser");
		Lesson lesson = ss.getLesson(lessonId);
		// Extract the YouTube video id from the URL
		String youtubeUrl = lesson.getLink();
		
		String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
		lesson.setLink(videoId);
		
		
		model.addAttribute("lesson",lesson);
		List<Comments> commentsList=cs.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}
	@PostMapping("/addComment")
	public String comments(@RequestParam("comment") String comment
						,Model model) {
		Comments c=new Comments();
		c.setComment(comment);
		cs.addComment(c);
		
		List<Comments> commentsList=cs.commentList();
		model.addAttribute("comments",commentsList);
		
		return "myLesson";
	}

}
