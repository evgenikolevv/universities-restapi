package com.universitySubjects.controllers;

import com.universitySubjects.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/major")
public class MajorController {

    @Autowired
    private final MajorService majorService;

    public MajorController(MajorService majorService){
        this.majorService = majorService;
    }


}
