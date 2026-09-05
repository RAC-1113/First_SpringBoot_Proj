package com.example.studentapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

@Entity
public class Student {

    //@Min(value=1, message = "ID must be greater than 1") //In a real proj this wouldn't be needed as the DB will make the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotBlank(message = "Name cannot be blank")
    //@Size(min = 2, max = 50, message = "Name can be between 2 and 50 characters only")
    private String name;

    //@Max(value = 50, message = "Maximum age can only be 50")
    //@Min(value = 18, message = "Minimum age can be 18")
    private int age;

    @OneToOne(cascade = CascadeType.ALL) //This is so that the operations done by student are reflected on StudentProfile too
    @JoinColumn(name = "profile_id") //This explicitly defines the foreign key column name
    private StudentProfile profile;

    public Student(){}

    public Student(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }
}
