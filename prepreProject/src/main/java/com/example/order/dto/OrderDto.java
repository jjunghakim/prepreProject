package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;

public class OrderDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Positive
        private long memberId;
        //@NotNull(message = "주문할 커피 정보는 필수입니다.")
//        private List<OrderCoffeeDto> orderCoffees;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long orderId;
//        private Order.OrderStatus orderStatus;
    }

}
