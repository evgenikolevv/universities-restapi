package com.universities.controllers;

import com.universities.entities.Major;
import com.universities.entities.Subject;
import com.universities.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/major")
public class MajorController {

    @Autowired
    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping(path = "{majorId}")
    public Major getMajor(@PathVariable("majorId") Long majorId) {
        try {
            return majorService.getMajor(majorId);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    public void addMajor(@RequestBody Major major) {
        majorService.addMajor(major);
    }

    @PutMapping(path = "{majorId}")
    public void updateMajor(@PathVariable("majorId") Long majorId,
                            @RequestParam(required = false) String name) {
        try {
            if (name != null) {
                majorService.updateMajor(majorId, name);
            }
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(path = "{majorId}/subjects")
    public List<Subject> getMajorSubjects(@PathVariable("majorId") Long majorId) {
        return majorService.getSubjectsByMajorId(majorId);
    }


}
