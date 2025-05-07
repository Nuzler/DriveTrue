package com.drivetrue.DriveTrueApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivetrue.DriveTrueApp.entity.Category;
import com.drivetrue.DriveTrueApp.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findByFoodId(Integer foodId);

    List<Food>findAllByCategory(Category category);
}
