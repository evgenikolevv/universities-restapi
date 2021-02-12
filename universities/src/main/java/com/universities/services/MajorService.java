package com.universities.services;

import com.universities.entities.Major;
import com.universities.entities.Subject;
import com.universities.repositories.IMajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {
    private final IMajorRepository repository;

    @Autowired
    public MajorService(IMajorRepository repository){
        this.repository = repository;
    }

    public Major getMajor(Long majorId){
        Optional<Major> major = repository.findById(majorId);
        if (major.isEmpty()){
            throw new IllegalStateException("major with id: " + majorId + " does not exists!");
        }
        return major.get();
    }

    public void addMajor(Major major){
        repository.save(major);
    }

    @Transactional
    public void updateMajor(Long majorId, String name){
        Major major = repository.findById(majorId)
                .orElseThrow(()->  new IllegalStateException("major with id " + majorId + " does not exists!"));
        major.setName(name);
    }

   public List<Subject> getSubjectsByMajorId(Long majorId){
        return repository.getSubjectsByMajorId(majorId);
   }
}
