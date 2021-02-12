package com.universities.repositories;

import com.universities.entities.Major;
import com.universities.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUniversityRepository extends JpaRepository<University,Long> {

    @Query("SELECT m FROM Major m WHERE m.university.id = :universityId")
    List<Major> getMajorsByUniversityId(@Param("universityId") Long universityId);

    @Query("SELECT u FROM University u WHERE u.name = :name")
    Optional<University> findByName(@Param("name") String name);
}
