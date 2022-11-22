package com.example.coffee.dto;

import com.example.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CoffeeDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String korName;
        private String engName;
        private int price;
        private String coffeeCode;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long coffeeId;
        private String korName;
        private String engName;
//        private Coffee.CoffeeStatus coffeeStatus;
    }




}
