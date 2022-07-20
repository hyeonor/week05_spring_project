package com.sparta.week05_spring_project.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderFoodDto> foods;
}
