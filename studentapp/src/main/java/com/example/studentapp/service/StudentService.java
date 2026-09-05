package com.example.studentapp.service;

import com.example.studentapp.dto.StudentRequestDTO;
import com.example.studentapp.dto.StudentResponseDTO;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.StudentProfile;
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

    /*public Student getStudentById(int id){
        for(Student student : getStudents){
            if(student.getId() == id) return student;
        }

        throw new StudentNotFoundException("Student with ID: "+id+" not found");
    }*/
    //This uses the studentRepository to find the student by their id
    public Student getStudentById(int id){
        return studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Student with id:" + id + " is not found"));
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(StudentRequestDTO requestDTO){
        //Adding the details of student profile to be added to the database
        StudentProfile profile = new StudentProfile();
        profile.setCity(requestDTO.getCity());
        profile.setPhone(requestDTO.getPhone());

        Student student = new Student();
        student.setName(requestDTO.getName());
        student.setAge(requestDTO.getAge());

        student.setProfile(profile);

        return studentRepository.save(student);
    }

    public Student updateStudentById(int id, StudentRequestDTO requestDTO) {
        /*for(Student student : getStudents){
            if(student.getId() == id){
                student.setName(stud.getName());
                student.setAge(stud.getAge());
                student.setId(stud.getId());
                return student;
            }
        }
        return null;*/

        Student studentToBeUpdated = studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Student with id "+id+" does not exist"));

        studentToBeUpdated.setName(requestDTO.getName());
        studentToBeUpdated.setAge(requestDTO.getAge());

        return studentRepository.save(studentToBeUpdated);
    }

    public Student deleteStudentById(int id) {
        Student studentToDelete = studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Student with id "+id+" is not found"));

        studentRepository.delete(studentToDelete);

        return studentToDelete;
    }
}
