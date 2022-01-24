package com.example.gradebook.security.services;

import com.example.gradebook.models.Grade;
import com.example.gradebook.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepo;

    @Autowired
    public GradeService(GradeRepository gradeRepo){
        this.gradeRepo = gradeRepo;
    }

    public List<Grade>findAllGrades(){
        return gradeRepo.findAll();
    }

    public Grade findGradeById(Long id){
        return gradeRepo.findById(id).get();
    }

    public void deleteGradeById(Long id){
        gradeRepo.deleteById(id);
    }

    public Grade addGrade(Grade grade){
        return gradeRepo.save(grade);
    }

    public Grade updateGrade(Grade grade){
        return gradeRepo.save(grade);
    }


}
