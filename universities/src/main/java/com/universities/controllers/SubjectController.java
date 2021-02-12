package com.universities.controllers;

import com.universities.entities.Subject;
import com.universities.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "{subjectId}")
    public Subject getSubject(@PathVariable("subjectId") Long subjectId) {
        try {
            return subjectService.getSubject(subjectId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    public void addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    @PutMapping(path = "{subjectId}")
    public void updateSubject(@PathVariable("subjectId") Long subjectId,
                              @RequestParam(required = false) String name) {
        try {
            if (name != null) {
                subjectService.updateSubject(subjectId, name);
            }
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(path = "{subjectId}")
    public void deleteSubject(@PathVariable("subjectId") Long subjectId) {
        try {
            subjectService.deleteSubject(subjectId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
