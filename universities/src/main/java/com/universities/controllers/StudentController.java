package com.universities.controllers;

import com.universities.entities.Major;
import com.universities.entities.Student;
import com.universities.entities.University;
import com.universities.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        try {
            return studentService.getStudent(studentId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String secondName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String facultyNumber,
                              @RequestParam(required = false) Major major,
                              @RequestParam(required = false) University university) {
        try {
            Student student = new Student(firstName, secondName, lastName,
                    facultyNumber, major, university);
            studentService.updateStudent(studentId, student);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e
            );
        }
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        try{
            studentService.deleteStudent(studentId);
        }catch (IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e );
        }
    }
}
