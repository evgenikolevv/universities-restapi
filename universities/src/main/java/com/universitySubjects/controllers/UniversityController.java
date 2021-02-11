package com.universitySubjects.controllers;

import com.universitySubjects.entities.Major;
import com.universitySubjects.entities.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.universitySubjects.services.UniversityService;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService){
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> getUniversities(){
            return universityService.getUniversities();
    }

    @GetMapping(path="{universityId}")
    public University getUniversity(@PathVariable("universityId") Long universityId){
            return universityService.getUniversity(universityId);
    }

    @GetMapping(path="{universityId}/majors")
    public List<Major> getUniversityMajors(@PathVariable("universityId")Long universityId){
        return universityService.getMajorsByUniversityId(universityId);
    }

    @PostMapping
    public void addUniversity(@RequestBody University university){
        universityService.addUniversity(university);
    }

    @DeleteMapping(path = "{universityId}")
    public void deleteUniversity(@PathVariable("universityId")Long universityId){
            universityService.deleteUniversity(universityId);
    }

    @PutMapping(path = "{universityId}")
    public void updateUniversity(@PathVariable("universityId")Long universityId,
                                 @RequestParam(required = false) String name){

        if(name != null){
            universityService.updateUniversity(universityId,name);
        }
    }
}
