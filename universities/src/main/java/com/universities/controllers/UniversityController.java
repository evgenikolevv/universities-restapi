package com.universities.controllers;

import com.universities.entities.Major;
import com.universities.entities.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.universities.services.UniversityService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> getUniversities() {
        try {
            return universityService.getUniversities();
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = "{universityId}")
    public University getUniversity(@PathVariable("universityId") Long universityId) {
        try {
            return universityService.getUniversity(universityId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = "{universityId}/majors")
    public List<Major> getUniversityMajors(@PathVariable("universityId") Long universityId) {
        try {
            return universityService.getMajorsByUniversityId(universityId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    public void addUniversity(@RequestBody University university) {
        try {
            universityService.addUniversity(university);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e );
        }
    }

    @DeleteMapping(path = "{universityId}")
    public void deleteUniversity(@PathVariable("universityId") Long universityId) {
        universityService.deleteUniversity(universityId);
    }

    @PutMapping(path = "{universityId}")
    public void updateUniversity(@PathVariable("universityId") Long universityId,
                                 @RequestParam(required = false) String name) {
        try {
            if (name != null) {
                universityService.updateUniversity(universityId, name);
            }
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }
}
