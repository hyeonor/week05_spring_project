package com.sparta.week05_spring_project.controller;

import com.sparta.week05_spring_project.dto.RestaurantDto;
import com.sparta.week05_spring_project.model.Restaurant;
import com.sparta.week05_spring_project.repository.RestaurantRepository;
import com.sparta.week05_spring_project.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController //JSON으로 데이터를 주고받음을 선언합니다.
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto requestDto) {
        Restaurant restaurant = restaurantService.registerRestaurant(requestDto);
        return ResponseEntity.ok().body(restaurant);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public  ResponseEntity<List<Restaurant>> getRestaurants() {
    List<Restaurant> restaurantList= restaurantRepository.findAll();
        return ResponseEntity.ok().body(restaurantList);
    }
}
