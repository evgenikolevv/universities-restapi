package com.universities.repositories;

import com.universities.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository  extends JpaRepository<Subject,Long> {
}
