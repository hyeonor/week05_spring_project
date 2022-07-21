package com.sparta.week05_spring_project.service;

import com.sparta.week05_spring_project.dto.FoodDto;
import com.sparta.week05_spring_project.dto.FoodResponseDto;
import com.sparta.week05_spring_project.model.Food;
import com.sparta.week05_spring_project.model.Restaurant;
import com.sparta.week05_spring_project.repository.FoodRepository;
import com.sparta.week05_spring_project.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerFood(Long restaurantId, List<FoodDto> foodDto) { //가격 조건

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("음식점을 찾을 수 없습니다."));

        HashSet<String> hashSet = new HashSet<>();
        for (FoodDto foodDtoList : foodDto) {
            hashSet.add(foodDtoList.getName());
        }
        // 중복이 있으면 hashSet.size()가 foodDto.size()보다 작음
        if (hashSet.size() != foodDto.size())
            throw new IllegalArgumentException("입력된 음식명 중복 에러");
        if (validCheck(restaurant, foodDto))
            throw new IllegalArgumentException("기존 저장된 음식명과 중복");


        for (FoodDto foodDtoList : foodDto) {
            int price = foodDtoList.getPrice();

            if (price < 100) {
                throw new IllegalArgumentException("100원 미만 에러");
            }
            if (price > 1000000) {
                throw new IllegalArgumentException("1,000,000원 초과 에러");
            }
            if (price % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력 안 됨 에러");
            }

            Food food = new Food(restaurant, foodDtoList);
            foodRepository.save(food);
        }
    }

    public boolean validCheck(Restaurant restaurantId, List<FoodDto> foodDto) {
        for (FoodDto foodDtoList : foodDto) {
            if (foodRepository.existsByNameAndRestaurant(foodDtoList.getName(), restaurantId))
                return true;
        }
        return false;
    }

    public List<FoodResponseDto> showFood(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("음식점을 찾을 수 없습니다."));

        List<FoodResponseDto> menu = new ArrayList<>();
        List<Food> foodList = foodRepository.findAllByRestaurant(restaurant);

        for (Food food : foodList) {
            FoodResponseDto foodResponseDto = new FoodResponseDto(food.getId(), food.getName(), food.getPrice());
            menu.add(foodResponseDto);
        }
        return menu;
    }
}
