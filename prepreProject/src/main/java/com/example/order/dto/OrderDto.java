package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private long memberId;
//        private List<OrderCoffeeDto> orderCoffees;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long orderId;
//        private Order.OrderStatus orderStatus;
    }

}
