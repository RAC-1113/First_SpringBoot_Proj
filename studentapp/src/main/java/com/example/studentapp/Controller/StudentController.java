package com.example.studentapp.Controller;

import com.example.studentapp.dto.StudentProfileResponseDTO;
import com.example.studentapp.dto.StudentRequestDTO;
import com.example.studentapp.dto.StudentResponseDTO;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //This is the Postman POST mapping which we do through Postman
    /*@PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }*/

    //This is the new POST mapping where the validations defined in Student.java are applied in this mapping
    @PostMapping("/addStudent")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO requestDTO){

        Student SavedStudent = studentService.addStudent(requestDTO);

        StudentProfileResponseDTO profileResponseDTO = new StudentProfileResponseDTO(
                SavedStudent.getProfile().getCity(),
                SavedStudent.getProfile().getPhone()
        );

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                SavedStudent.getName(),
                SavedStudent.getAge(),
                SavedStudent.getId(),
                profileResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //A @PathVariable takes the value from the path and puts it into a Java variable like the id in this case
    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable int id){
        Student student = studentService.getStudentById(id);

        StudentProfileResponseDTO profileResponseDTO = new StudentProfileResponseDTO(
                student.getProfile().getCity(),
                student.getProfile().getPhone()
        );

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                student.getName(),
                student.getAge(),
                student.getId(),
                profileResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //In @PutMapping we have to update all the fields, not just the ones we desire to update
    /*@PutMapping("/updateStudent/{id}")
    public Student updateStudentById(@PathVariable int id, @RequestBody Student stud){
        Student studd = studentService.updateStudentById(id, stud);
        return studd;
    }*/

    // This is the new PUT mapping where validations are implemented as done in Student.java
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable int id, @Valid @RequestBody StudentRequestDTO stud){
        Student studd = studentService.updateStudentById(id, stud);

        StudentProfileResponseDTO profileResponseDTO = new StudentProfileResponseDTO(
                studd.getProfile().getCity(),
                studd.getProfile().getPhone()
        );

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                studd.getName(),
                studd.getAge(),
                studd.getId(),
                profileResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // As the name suggests this mapping is used to delete the student with the given Id in the path using .remove()
    @DeleteMapping("/deleteStudent/{id}")
    public Student deleteStudentById(@PathVariable int id){
        return studentService.deleteStudentById(id);
    }

    // This is how exceptions are added in the controller but it is not needed now as we are using ControllerAdvice
    /*@ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }*/
}
