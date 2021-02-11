package com.universitySubjects.services;

import com.universitySubjects.entities.Major;
import com.universitySubjects.repositories.IMajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {
    private IMajorRepository repository;

    @Autowired
    public MajorService(IMajorRepository repository){
        this.repository = repository;
    }

}
