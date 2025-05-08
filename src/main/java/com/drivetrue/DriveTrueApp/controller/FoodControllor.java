package com.drivetrue.DriveTrueApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drivetrue.DriveTrueApp.entity.Food;
import com.drivetrue.DriveTrueApp.service.FoodService;




@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174,https://drive-true-front-end.vercel.app/,https://drive-true-dash-bord-3zgw.vercel.app/"} )
public class FoodControllor {
       
       @Autowired
       private FoodService foodservice;
  
    @GetMapping("/getallfood")
    public List<Food> getMethodName() {
        return foodservice.getMethodName(); 
    }

    @PostMapping("/addfood")
    public Food saveFood(@RequestBody Food food) {
        //TODO: process POST request
        
        return foodservice.saveFood(food);
    }
    
    @GetMapping("/getfood/{foodId}")
    public Food getFoodById(@PathVariable Integer foodId ){

        return foodservice.getFoodById(foodId);
    }
    
    @PutMapping("/food/{foodId}")
    public Food updateFoodById(@PathVariable Integer foodId, @RequestBody Food foodName){
        return foodservice.updateFoodById(foodId,foodName);
    }


    @GetMapping("/getfoods/{categoryId}")
    public List<Food> getfoodbycategory(@PathVariable Integer categoryId){
        
        return foodservice.getfoodbycategory(categoryId);
    }
    
    
   
    
}
