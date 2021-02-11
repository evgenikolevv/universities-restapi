package com.universitySubjects.repositories;

import com.universitySubjects.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMajorRepository extends JpaRepository<Major,Long> {

}
