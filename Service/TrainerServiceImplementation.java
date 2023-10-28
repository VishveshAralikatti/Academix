package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Lesson;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.LessonRepository;
 @Service
public class TrainerServiceImplementation implements TrainerService {
	
	CourseRepository crepo;
 	@Autowired
	public TrainerServiceImplementation(CourseRepository crepo) {
		super();
		this.crepo=crepo;
	}
 	@Autowired
 	LessonRepository lrepo;

	@Override
	public Course addCourse(Course course) {
		return crepo.save(course);
		  
	}

	@Override
	public String saveCourse(Course course) {
		crepo.save(course);
		return "Course saved successfully!";
	}

	@Override
	public String addLesson(Lesson lesson) {
		lrepo.save(lesson);
		return "Lesson added successfully!";
	}

	@Override
	public Course getCourse(int courseId) {
		return crepo.findById(courseId).get();
		 
	}

	@Override
	public List<Course> courseList() {
		return crepo.findAll();
	}
}
