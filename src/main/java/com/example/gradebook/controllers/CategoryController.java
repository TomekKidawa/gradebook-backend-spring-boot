package com.example.gradebook.controllers;

import com.example.gradebook.models.Category;
import com.example.gradebook.models.Subject;
import com.example.gradebook.repository.CategoryRepository;
import com.example.gradebook.security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public  CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Category>>findAllCategory(){
        List<Category> category = categoryService.findAllCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category>getCategroyById(@PathVariable("id")Long id){
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory , HttpStatus.CREATED);
    }
}
