package com.example.studentapp.service;

import com.example.studentapp.dto.StudentRequestDTO;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    //We inject the student repository
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

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

        throw new StudentNotFoundException("Student with ID: "+id+" not found");
    }

    public List<Student> getAllStudents(){
        return getStudents;
    }

    public Student addStudent(StudentRequestDTO requestDTO){
        Student student = new Student();
        student.setName(requestDTO.getName());
        student.setAge(requestDTO.getAge());

        return studentRepository.save(student);
    }

    public Student updateStudentById(int id, Student stud) {
        for(Student student : getStudents){
            if(student.getId() == id){
                student.setName(stud.getName());
                student.setAge(stud.getAge());
                student.setId(stud.getId());
                return student;
            }
        }
        return null;
    }

    public Student deleteStudentById(int id) {
        for(Student student : getStudents){
            if(student.getId() == id) {
                getStudents.remove(student);
                return student;
            }
        }
        return null;
    }
}
