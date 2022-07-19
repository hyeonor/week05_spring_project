package com.sparta.week05_spring_project.repository;

import com.sparta.week05_spring_project.model.Food;
import com.sparta.week05_spring_project.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByNameAndRestaurant(String name, Restaurant Restaurant);
    List<Food> findAllByRestaurant(Restaurant Restaurant);
}
