package com.universities.services;

import com.universities.entities.Major;
import com.universities.entities.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universities.repositories.IUniversityRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    private final IUniversityRepository repository;

    @Autowired
    public UniversityService(IUniversityRepository repository) {
        this.repository = repository;
    }

    public University getUniversity(Long universityId) {
        Optional<University> university = repository.findById(universityId);
        if (university.isEmpty()) {
            throw new IllegalStateException("university with id: " + universityId + " does not exists!");
        }
        return university.get();
    }

    public List<University> getUniversities() {
        List<University> universities = repository.findAll();
        if (universities.isEmpty()) {
            throw new IllegalStateException("There are no universities!");
        }
        return universities;
    }

    public void addUniversity(University university) {
        repository.findByName(university.getName())
                .ifPresentOrElse((value) -> {
                    throw new IllegalStateException("University with name: " + university.getName() + " exists!");
                }, () -> {
                    repository.save(university);
                });
    }

    public void deleteUniversity(Long universityId) {
        repository.deleteById(universityId);
    }

    @Transactional
    public void updateUniversity(Long universityId, String name) {
        University university = repository.findById(universityId)
                .orElseThrow(() -> new IllegalStateException("university with id: " + universityId + " does not exists!"));
        university.setName(name);
    }

    public List<Major> getMajorsByUniversityId(Long universityId) {
        List<Major> majors = repository.getMajorsByUniversityId(universityId);
        if (majors.isEmpty()) {
            throw new IllegalStateException("This university does not have any majors!");
        }
        return majors;
    }
}
