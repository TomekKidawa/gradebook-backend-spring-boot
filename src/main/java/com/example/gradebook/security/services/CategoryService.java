package com.example.gradebook.security.services;

import com.example.gradebook.models.Category;
import com.example.gradebook.models.Subject;
import com.example.gradebook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public List<Category>findAllCategory(){
        return categoryRepo.findAll();
    }

    public Category findCategoryById(Long id){
        return categoryRepo.findById(id).get();
    }

    public void deleteCategoryById(Long id){
        categoryRepo.deleteById(id);
    }

    public Category addCategory(Category category){
        return categoryRepo.save(category);
    }
}
