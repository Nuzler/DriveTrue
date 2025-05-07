package com.drivetrue.DriveTrueApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivetrue.DriveTrueApp.entity.Category;


public interface  CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByCategoryId(Integer categoryId);
   
    
}
