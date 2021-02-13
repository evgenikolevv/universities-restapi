package com.universities.repositories;

import com.universities.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s WHERE s.facultyNumber = :facultyNumber")
    Optional<Student> findByFacultyNumber(@Param("facultyNumber") String facultyNumber);
}
