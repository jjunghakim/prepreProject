package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class OrderDto {
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Positive
        private long orderId;
        //@NotNull(message = "주문할 커피 정보는 필수입니다.")
//        private List<OrderCoffeeDto> orderCoffees;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private long orderId;
//        private Order.OrderStatus orderStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private long orderId;
        private long memberId;
        private LocalDateTime createdAt;
    }

}
