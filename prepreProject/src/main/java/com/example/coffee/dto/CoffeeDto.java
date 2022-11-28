package com.example.coffee.dto;

import com.example.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Optional;

public class CoffeeDto {
    @Getter
    @AllArgsConstructor
    public static class Post{

        @NotBlank
        private String korName;

        @NotBlank
        @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",
                message = "커피명은 영문이어야 합니다.")
        private String engName;

        @Range(min = 100, max = 50000)
        private int price;

        @NotBlank
        @Pattern(regexp = "^([A-Za-z]){3}$",
        message = "커피 코드는 3자리 영문이어야 합니다")
        private String coffeeCode;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private long coffeeId;

        @NotBlank(message = "커피명은 공백이 아니어야 합니다")
        private String korName;

        @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", message = "커피명(영문)은 영문이어야 합니다. 예) Cafe Latte")
        private String engName;

        private int price;
//        private Coffee.CoffeeStatus coffeeStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private long coffeeId;
        private String korName;
        private String engName;
        private int price;
    }
}
