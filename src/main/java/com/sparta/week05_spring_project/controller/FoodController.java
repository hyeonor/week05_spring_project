package com.sparta.week05_spring_project.controller;

import com.sparta.week05_spring_project.dto.FoodDto;
import com.sparta.week05_spring_project.dto.FoodResponseDto;
import com.sparta.week05_spring_project.model.Food;
import com.sparta.week05_spring_project.model.Restaurant;
import com.sparta.week05_spring_project.repository.FoodRepository;
import com.sparta.week05_spring_project.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;
    private final FoodRepository foodRepository;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Food> createFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> requestDto) {
        foodService.registerFood(restaurantId, requestDto);
        return ResponseEntity.ok().body(null);
    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<FoodResponseDto>> getFoods(@PathVariable Long restaurantId) {
        List<FoodResponseDto> foods = foodService.showFood(restaurantId);
        return ResponseEntity.ok().body(foods);
    }
}
