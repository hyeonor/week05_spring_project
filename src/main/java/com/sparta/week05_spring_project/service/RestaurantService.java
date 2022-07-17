package com.sparta.week05_spring_project.service;

import com.sparta.week05_spring_project.dto.RestaurantDto;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    public void registerMinOrderPrice(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() < 1000) {
            throw new IllegalArgumentException("1,000원 미만 에러");
        }
        if (restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("100,000원 초과 에러");
        }
        if (restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력 안 됨 에러");
        }
        if (restaurantDto.getDeliveryFee() < 0) {
            throw new IllegalArgumentException("0원 미만 에러");
        }
        if (restaurantDto.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("10,000원 미만 에러");
        }
        if (restaurantDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로 입력 안 됨 에러");
        }
    }
}
