package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{

}
