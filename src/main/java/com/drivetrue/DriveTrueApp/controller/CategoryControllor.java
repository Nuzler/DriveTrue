package com.drivetrue.DriveTrueApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drivetrue.DriveTrueApp.entity.Category;
import com.drivetrue.DriveTrueApp.service.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping
@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174,https://drive-true-front-end.vercel.app/"} )
public class CategoryControllor {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/getcategory") 
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    
    @PostMapping("/savecategory")
    public Category saveCategory(@RequestBody Category category ){
        categoryService.saveCategory(category);

        return null;

    }

    @GetMapping("/getcategory/{ID}")  
    public Category getCategorybyId(@PathVariable Integer ID){

        return categoryService.getCategorybyid(ID);
    }
    
}
