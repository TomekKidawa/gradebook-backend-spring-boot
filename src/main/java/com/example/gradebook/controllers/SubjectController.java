package com.example.gradebook.controllers;


import com.example.gradebook.models.Subject;
import com.example.gradebook.repository.SubjectRepository;
import com.example.gradebook.security.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Subject>>getAllSubjects(){
        List<Subject> subject = subjectService.findAllSubject();
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject>getSubjectById(@PathVariable("id")Long id){
        Subject subject = subjectService.findSubjectById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id){
        subjectService.deleteSubjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject){
        Subject newSubject = subjectService.addSubject(subject);
        return new ResponseEntity<>(newSubject , HttpStatus.CREATED);
    }


}
