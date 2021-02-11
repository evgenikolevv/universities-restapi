package com.universitySubjects.repositories;

import com.universitySubjects.entities.Major;
import com.universitySubjects.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUniversityRepository extends JpaRepository<University,Long> {

    @Query("SELECT m FROM Major m WHERE m.university.id = :universityId")
    List<Major> getMajorsByUniversityId(@Param("universityId") Long universityId);
}
