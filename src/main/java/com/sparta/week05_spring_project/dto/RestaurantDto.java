package com.sparta.week05_spring_project.dto;

import lombok.Getter;

@Getter
public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
