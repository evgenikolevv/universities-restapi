package com.universities.services;

import com.universities.entities.Student;
import com.universities.repositories.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {
    private final IStudentRepository repository;

    @Autowired
    public StudentService(IStudentRepository repository) {
        this.repository = repository;
    }

    public Student getStudent(Long studentId) {
        Optional<Student> student = repository.findById(studentId);
        if (student.isEmpty()) {
            throw new IllegalStateException("student with id: " + studentId + " does not exists!");
        }
        return student.get();
    }

    public void addStudent(Student student) {
        repository.findByFacultyNumber(student.getFacultyNumber())
                .ifPresentOrElse((value) -> {
                    throw new IllegalStateException("Student with faculty number: " + student.getFacultyNumber() + " exists!");
                }, () -> {
                    repository.save(student);
                });
    }

    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " does not exists!"));

        if (updatedStudent.getFirstName() != null) {
            student.setFirstName(updatedStudent.getFirstName());
        }

        if (updatedStudent.getSecondName() != null) {
            student.setSecondName(updatedStudent.getSecondName());
        }

        if (updatedStudent.getLastName() != null) {
            student.setLastName(updatedStudent.getLastName());
        }

        if (updatedStudent.getFacultyNumber() != null) {
            student.setFacultyNumber(updatedStudent.getFacultyNumber());

        }

        if (updatedStudent.getMajor() != null) {
            student.setMajor(updatedStudent.getMajor());
        }

        if (updatedStudent.getUniversity() != null) {
            student.setUniversity(updatedStudent.getUniversity());
        }
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> student = repository.findById(studentId);
        if (student.isEmpty()){
            throw  new IllegalStateException("student with id " + studentId + " does not exists!");
        }
        repository.delete(student.get());
    }
}
