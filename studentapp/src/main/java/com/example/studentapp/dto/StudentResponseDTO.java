package com.example.studentapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentResponseDTO {
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 50, message = "Name can be of at least 5 characters or max 50 characters")
    private String name;

    @Min(value = 18, message = "Minimum age is 18 years")
    @Max(value = 22, message = "Maximum age is 22 years")
    private int age;

    private StudentProfileResponseDTO profile; //Imp remember

    public StudentResponseDTO(String name, int age, int id, StudentProfileResponseDTO profile){
        this.name = name;
        this.age = age;
        this.id = id;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentProfileResponseDTO getProfile() {
        return profile;
    }

    public void setProfile(StudentProfileResponseDTO profile) {
        this.profile = profile;
    }
}
