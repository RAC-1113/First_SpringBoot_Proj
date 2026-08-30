package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents = new ArrayList<>(
            List.of(
                    new Student(1, "Abhishek", 22),
                    new Student(2, "Aditya", 23),
                    new Student(3, "Sanath", 24)
            )
    );
    //We make an ArrayList because just List.of() creates an immutable list so adding students won't be allowed
    //Using ArrayList the list now becomes free to add and delete stuff

    public Student getStudent(){
        return getStudents.get(0);
    }

    /*public Student getStudentById(int id){
        return getStudents.get(id-1); This way also works but it has the possibility of the id being out of bounds which
        will give an error
    }*/

    public Student getStudentById(int id){
        for(Student student : getStudents){
            if(student.getId() == id) return student;
        }
        return null;
    }

    public List<Student> getAllStudents(){
        return getStudents;
    }

    public Student addStudent(Student student){
        getStudents.add(student);
        return student;
    }
}
