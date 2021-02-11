package com.universitySubjects.services;

import com.universitySubjects.entities.Major;
import com.universitySubjects.entities.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universitySubjects.repositories.IUniversityRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    private final IUniversityRepository repository;
    @Autowired
    public UniversityService(IUniversityRepository repository){
        this.repository = repository;
    }

    public University getUniversity(Long universityId){
        Optional<University> university = repository.findById(universityId);
        if(university.isEmpty()){
            throw new IllegalStateException("university with id:" + universityId + " does not exists!");
        }
        return university.get();
    }

    public List<University> getUniversities(){
        return  repository.findAll();
    }

    public void addUniversity(University university){
        repository.save(university);
    }

    public void deleteUniversity(Long universityId){
        repository.deleteById(universityId);
    }

    @Transactional
    public void updateUniversity(Long universityId, String name){
        University university = repository.findById(universityId)
                .orElseThrow(()-> new IllegalStateException("university with id" + "does not exists!"));
        university.setName(name);
    }

    public List<Major> getMajorsByUniversityId(Long universityId) {
        return  repository.getMajorsByUniversityId(universityId);
    }
}
