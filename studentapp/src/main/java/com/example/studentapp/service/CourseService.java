package com.example.studentapp.service;

import com.example.studentapp.model.Course;
import com.example.studentapp.repository.CourseRepository;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){ // inject the course repository
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }
}
