package com.sparta.week05_spring_project.service;

import com.sparta.week05_spring_project.dto.OrderFoodDto;
import com.sparta.week05_spring_project.dto.OrderFoodResponseDto;
import com.sparta.week05_spring_project.dto.OrderRequestDto;
import com.sparta.week05_spring_project.dto.OrderResponseDto;
import com.sparta.week05_spring_project.model.Food;
import com.sparta.week05_spring_project.model.Order;
import com.sparta.week05_spring_project.model.OrderFood;
import com.sparta.week05_spring_project.model.Restaurant;
import com.sparta.week05_spring_project.repository.FoodRepository;
import com.sparta.week05_spring_project.repository.OrderRepository;
import com.sparta.week05_spring_project.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDto order(OrderRequestDto dto) {
        Long restaurantId = dto.getRestaurantId();
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("없는 식당입니다."));

        orderResponseDto.setRestaurantName(restaurant.getName());
        orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());

        List<OrderFoodDto> orderFoodDtoList = dto.getFoods();

        Order order = new Order();
        order.setRestaurant(restaurant);
        List<OrderFood> orderFoodList = new ArrayList<>();//Entity에 입력할 List
        List<OrderFoodResponseDto> foodReaponseDtoList = new ArrayList<>();// 반환할 List

        int totalPrice = 0;

        for (OrderFoodDto orderFoodDto : orderFoodDtoList) {
            Long foodId = orderFoodDto.getId();
            int quantity = orderFoodDto.getQuantity();

            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("주문 수량은 1 ~ 100 사이로 입력");
            }

            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new IllegalArgumentException("업는 음식입니다."));

            int foodPrice = food.getPrice() * quantity;
            totalPrice += foodPrice;

            OrderFood orderFood = new OrderFood(food, order, quantity);

            orderFoodList.add(orderFood);
            foodReaponseDtoList.add(new OrderFoodResponseDto(food.getName(), quantity, foodPrice));
        }
        order.setOrderFoods(orderFoodList);
        orderResponseDto.setFoods(foodReaponseDtoList);
        orderResponseDto.setTotalPrice(totalPrice + restaurant.getDeliveryFee());

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("식당의 최소 주문 금액보다 낮습니다.");
        }

        orderRepository.save(order);

        return orderResponseDto;
    }

    public List<OrderResponseDto> showOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setRestaurantName(restaurant.getName());
            orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());

            List<OrderFoodResponseDto> orderFoodResponseDtoList = new ArrayList<>();
            List<OrderFood> orderFoods = order.getOrderFoods();

            int totalPrice = 0;

            for (OrderFood orderFood : orderFoods) {
                OrderFoodResponseDto orderFoodResponseDto = new OrderFoodResponseDto(
                        orderFood.getFood().getName(),
                        orderFood.getQuantity(),
                        orderFood.getFood().getPrice() * orderFood.getQuantity());

                totalPrice += orderFood.getFood().getPrice() * orderFood.getQuantity();
                orderFoodResponseDtoList.add(orderFoodResponseDto);
            }
            orderResponseDto.setFoods(orderFoodResponseDtoList);
            orderResponseDto.setTotalPrice(totalPrice + restaurant.getDeliveryFee());

            orderResponseDtoList.add(orderResponseDto);
        }
        return orderResponseDtoList;
    }
}
