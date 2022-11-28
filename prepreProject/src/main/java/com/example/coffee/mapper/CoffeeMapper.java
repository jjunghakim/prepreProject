package com.example.coffee.mapper;

import com.example.coffee.dto.CoffeeDto;
import com.example.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeeDto.Post coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeeDto.Patch coffeePatchDto);
    CoffeeDto.Response coffeeToCoffeeResponseDto(Coffee coffee);
}
