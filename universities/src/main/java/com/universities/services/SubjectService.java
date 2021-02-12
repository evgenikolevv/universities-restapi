package com.universities.services;

import com.universities.entities.Subject;
import com.universities.repositories.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SubjectService {
    private final ISubjectRepository repository;

    @Autowired
    public SubjectService(ISubjectRepository repository) {
        this.repository = repository;
    }

    public Subject getSubject(Long subjectId) {
        Optional<Subject> subject = repository.findById(subjectId);
        if (subject.isEmpty()) {
            throw new IllegalStateException("subject with id: " + subjectId + " does not exists!");
        }
        return subject.get();
    }

    public void addSubject(Subject subject) {
        repository.save(subject);
    }

    @Transactional
    public void updateSubject(Long subjectId, String name) {
        Subject subject = repository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException("Subject with id: " + subjectId + " does not exists!"));
        subject.setName(name);
    }

    public void deleteSubject(Long subjectId) {
        Optional<Subject> subject = repository.findById(subjectId);
        if (subject.isEmpty()) {
            throw new IllegalStateException("Subject with id:" + subjectId + " does not exists!");
        }
        repository.deleteById(subject.get().getId());
    }

}
