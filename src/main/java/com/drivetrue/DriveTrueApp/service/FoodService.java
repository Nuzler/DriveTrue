package com.drivetrue.DriveTrueApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivetrue.DriveTrueApp.entity.Category;
import com.drivetrue.DriveTrueApp.entity.Food;
import com.drivetrue.DriveTrueApp.repository.CategoryRepository;
import com.drivetrue.DriveTrueApp.repository.FoodRepository;

@Service
public class FoodService {
    
    @Autowired
    private  FoodRepository foodRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Food> getMethodName() {

        return foodRepository.findAll();
    
}

    public Food saveFood(Food food){
        return foodRepository.save(food);
    }

    public Food getFoodById(Integer foodId){

        return foodRepository.findByFoodId(foodId);
    }

    public Food updateFoodById(Integer foodId, Food request){
        
        Food food=foodRepository.findByFoodId(foodId);

        if(request.getFoodName()!=null){
           food.setFoodName(request.getFoodName());
        }
        if(request.getPrice()!=null){
            food.setPrice(request.getPrice());
        }
        if(request.getDescription()!=null){
            food.setDescription(request.getDescription());
        }

         if(request.getPopular()!=null){
            food.setPopular(request.getPopular());
        }

        Category category = new Category();

        if(request.getCategory()!=null){
            category.setCategoryId(request.getCategory().getCategoryId());
        }
        foodRepository.save(food);

        return null;
    }

    public List<Food> getfoodbycategory(Integer categoryId){

        Category category = categoryRepository.findByCategoryId(categoryId);

        return foodRepository.findAllByCategory(category);
    }
}
