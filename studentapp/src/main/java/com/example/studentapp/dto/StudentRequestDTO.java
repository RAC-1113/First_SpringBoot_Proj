package com.example.studentapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 50, message = "Name can be of at least 5 characters or max 50 characters")
    private String name;

    @Min(value = 18, message = "Minimum age is 18 years")
    @Max(value = 22, message = "Maximum age is 22 years")
    private int age;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 10)
    private String city;

    @Size(max = 10, min = 10, message = "Phone number has to be of 10 digits")
    private String phone;

    //@Size(max = 5, min = 1, message = "Course has to be atleast 5 characters")
    //private String courseName;

    public StudentRequestDTO(){
    }

    public StudentRequestDTO(String name, int age, String city, String phone){
        this.name = name;
        this.age = age;
        this.city = city;
        this.phone = phone;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
