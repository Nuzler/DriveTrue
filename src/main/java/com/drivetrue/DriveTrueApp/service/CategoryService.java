package com.drivetrue.DriveTrueApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivetrue.DriveTrueApp.entity.Category;
import com.drivetrue.DriveTrueApp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private  CategoryRepository categoryRepository;
    
public List<Category> getAllCategory(){
    return categoryRepository.findAll();
}

public Category saveCategory(Category category){
    
    categoryRepository.save(category);
    return null;

}

public Category getCategorybyid(Integer ID){

    return categoryRepository.findByCategoryId(ID);
}


}
