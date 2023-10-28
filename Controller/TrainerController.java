package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Lesson;
import com.example.demo.Service.TrainerService;
import com.example.demo.Service.UserService;

@Controller
public class TrainerController {
	
	TrainerService ts;
	@Autowired
	public TrainerController(TrainerService ts) {
		super();
		this.ts=ts;
	}
	
	@PostMapping("/addCourse")
	public String addCourse(@RequestParam("courseId") int courseId,
			@RequestParam("courseName") String courseName,
			@RequestParam("coursePrice") int coursePrice)
	{
		Course course=new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setCoursePrice(coursePrice);
		Course c=ts.addCourse(course);
		if(c!=null)
		{
			return "/trainerHome";	
		}
		else
		{
			return "/addCourseFail";
		}
	}
	
	@PostMapping("/lesson")
	 public String addLesson(@RequestParam("courseId") int courseId,
			 @RequestParam("lessonId") int lessonId,
			 @RequestParam("lessonName") String lessonName,
			 @RequestParam("topic") String topic,
			 @RequestParam("link") String link)
	 {
		Course course=ts.getCourse(courseId);
		Lesson lesson=new Lesson(lessonId,lessonName,topic,link,course);
		ts.addLesson(lesson);
		
		course.getLessons().add(lesson);
		ts.saveCourse(course);
		
		return "/trainerHome";
	 }
	@GetMapping("/showCourses")
	public String showCourses(Model model)
	{
		List<Course> courseList=ts.courseList();
		model.addAttribute("courseList", courseList);
		return "courses";
	}

}
