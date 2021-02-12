package com.universities.repositories;

import com.universities.entities.Major;
import com.universities.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMajorRepository extends JpaRepository<Major,Long> {

    @Query("SELECT s FROM Subject s WHERE s.major.id = :majorId")
    List<Subject> getSubjectsByMajorId(@Param("majorId") Long majorId);
}
