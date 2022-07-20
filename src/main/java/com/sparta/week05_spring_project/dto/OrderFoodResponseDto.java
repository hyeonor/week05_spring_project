package com.sparta.week05_spring_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderFoodResponseDto {
    private String name;
    private int quantity; //주문 수량

    // 음식 금액 * price;
    private int price;

    public OrderFoodResponseDto(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
