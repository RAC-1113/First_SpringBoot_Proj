package com.example.studentapp.Controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    //This is the constructor injection
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //This is the Postman GET mapping which returns the format in JSON
    @GetMapping("/getAllStudents")
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudent")
    public Student getStudent(){
        return studentService.getStudent();
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    //A @PathVariable takes the value from the path and puts it into a Java variable like the id in this case
    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }
}
