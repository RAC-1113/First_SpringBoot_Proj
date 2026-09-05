package com.example.studentapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentProfileResponseDTO {

    @NotBlank(message = "City cannot be blank")
    @Max(value = 15)
    private String city;

    @Size(max = 10, min = 10, message = "Phone number has to be of 10 digits")
    private String phone;

    public StudentProfileResponseDTO(){}

    public StudentProfileResponseDTO(String city, String phone) {
        this.city = city;
        this.phone = phone;
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
