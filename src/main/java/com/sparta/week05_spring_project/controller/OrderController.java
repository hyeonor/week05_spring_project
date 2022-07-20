package com.sparta.week05_spring_project.controller;

import com.sparta.week05_spring_project.dto.OrderRequestDto;
import com.sparta.week05_spring_project.dto.OrderResponseDto;
import com.sparta.week05_spring_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    //주문
    @PostMapping("/order/request")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto requestDto) {
        OrderResponseDto orderResponseDto = orderService.order(requestDto);
        return ResponseEntity.ok().body(orderResponseDto);
    }

    //메뉴판 조회
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        List<OrderResponseDto> orderResponseDtos = orderService.showOrders();
        return ResponseEntity.ok().body(orderResponseDtos);
    }
}
