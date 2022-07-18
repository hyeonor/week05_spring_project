package com.sparta.week05_spring_project.service;

import com.sparta.week05_spring_project.dto.RestaurantDto;
import com.sparta.week05_spring_project.model.Restaurant;
import com.sparta.week05_spring_project.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        //최소 주문 가격 조건
        if (minOrderPrice < 1000) {
            throw new IllegalArgumentException("1,000원 미만 에러");
        }
        if (minOrderPrice > 100000) {
            throw new IllegalArgumentException("100,000원 초과 에러");
        }
        if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력 안 됨 에러");
        }

        //기본 배달비 조건
        if (deliveryFee < 0) {
            throw new IllegalArgumentException("0원 미만 에러");
        }
        if (deliveryFee > 10000) {
            throw new IllegalArgumentException("10,000원 미만 에러");
        }
        if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로 입력 안 됨 에러");
        }

        Restaurant restaurant = new Restaurant(restaurantDto);
        return restaurantRepository.save(restaurant);
    }
}
