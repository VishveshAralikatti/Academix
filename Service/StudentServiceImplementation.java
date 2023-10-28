package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Lesson;
import com.example.demo.Repository.LessonRepository;
@Service
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	LessonRepository lr;

	@Override
	public Lesson getLesson(int lessonId) {
		return lr.findById(lessonId).get();
	}

}
