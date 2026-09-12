package com.example.studentapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String courseId;

    private String courseName;

    @OneToMany(mappedBy = "course") //This makes it bidirectional
    private List<Student> students;

    public Course(){};

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
