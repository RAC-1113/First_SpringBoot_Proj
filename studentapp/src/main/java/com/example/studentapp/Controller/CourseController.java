package com.example.studentapp.Controller;

import com.example.studentapp.model.Course;
import com.example.studentapp.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){ //injected course service
        this.courseService = courseService;
    }

    @PostMapping("/addCourse") //So we added a new course
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course newCourse = courseService.addCourse(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
    }
}
