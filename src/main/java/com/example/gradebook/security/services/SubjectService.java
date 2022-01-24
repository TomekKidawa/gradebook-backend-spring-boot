package com.example.gradebook.security.services;

import com.example.gradebook.models.Subject;
import com.example.gradebook.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepo;

    @Autowired
    public SubjectService(SubjectRepository subjectRepo){
        this.subjectRepo = subjectRepo;
    }

    public List<Subject> findAllSubject(){
        return subjectRepo.findAll();
    }

    public Subject findSubjectById(Long id){
        return subjectRepo.findById(id).get();
    }

    public void deleteSubjectById(Long id){
        subjectRepo.deleteById(id);
    }

    public Subject addSubject(Subject subject){
        return subjectRepo.save(subject);
    }
}
