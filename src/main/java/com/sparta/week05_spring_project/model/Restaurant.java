package com.sparta.week05_spring_project.model;

import com.sparta.week05_spring_project.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Restaurant {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long Id;

    @Column(nullable = false)
    private String name;//음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;//최소주문 가격

    @Column(nullable = false)
    private int deliveryFee;//기본 배달비

    public Restaurant(RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}
