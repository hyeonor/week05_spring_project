package com.sparta.week05_spring_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;
}
