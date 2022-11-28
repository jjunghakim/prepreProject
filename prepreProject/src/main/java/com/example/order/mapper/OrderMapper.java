package com.example.order.mapper;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderDto.Post orderPostDto);
    Order orderPatchDtoToOrder(OrderDto.Patch orderPatchDto);
    OrderDto.Response orderToOrderResponseDto(Order order);
}
