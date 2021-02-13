package com.universities.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@uid")
@Entity(name = "Student")
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;

    @Column(name = "faculty_number", unique = true)
    private String facultyNumber;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    public Student() {

    }

    public Student(Long id, String firstName, String secondName, String lastName, String facultyNumber, University university, Major major) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.facultyNumber = facultyNumber;
        this.university = university;
        this.major = major;
    }

    public Student(String firstName, String secondName, String lastName, String facultyNumber, Major major, University university) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.facultyNumber = facultyNumber;
        this.major = major;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }
}
