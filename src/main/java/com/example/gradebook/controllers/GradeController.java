package com.example.gradebook.controllers;

import com.example.gradebook.models.Grade;
import com.example.gradebook.models.Subject;
import com.example.gradebook.models.User;
import com.example.gradebook.repository.CategoryRepository;
import com.example.gradebook.repository.GradeRepository;
import com.example.gradebook.repository.SubjectRepository;
import com.example.gradebook.security.services.CategoryService;
import com.example.gradebook.security.services.GradeService;
import com.example.gradebook.security.services.SubjectService;
import com.example.gradebook.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService gradeService;
    private final CategoryService categoryService;
    private final SubjectService subjectService;
    private final UserService userService;


    public GradeController(GradeService gradeService, CategoryService categoryService, SubjectService subjectService, UserService userService){
        this.gradeService = gradeService;
        this.categoryService = categoryService;
        this.subjectService = subjectService;
        this.userService = userService;
    }


    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Grade>>getAllGrades(){
        List<Grade> grade = gradeService.findAllGrades();
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade>getGradeById(@PathVariable("id")Long id){
        Grade grade = gradeService.findGradeById(id);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable("id") Long id){
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade){
        Grade newGrade = gradeService.addGrade(grade);
        return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Grade>updateGrade(@PathVariable("id") Long id,@RequestBody Grade gradeDetails){
        Grade updateGrade = gradeService.findGradeById(id);

        updateGrade.setGrade(gradeDetails.getGrade());
        updateGrade.setCategory(gradeDetails.getCategory());
        updateGrade.setSubject(gradeDetails.getSubject());
        updateGrade.setUser(gradeDetails.getUser());
        gradeService.updateGrade(updateGrade);
        return ResponseEntity.ok(updateGrade);
    }

    @GetMapping("/user/{userId}/grades")
    public ResponseEntity<List<Grade>>getAllGradesByUserId(@PathVariable(value = "userId") Long gradeId){
        List<Grade> grades = gradeRepository.findByUserId(gradeId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }


}
