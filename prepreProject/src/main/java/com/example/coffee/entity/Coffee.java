package com.example.coffee.entity;

import com.example.order.entity.OrderCoffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false, length = 50)
    private String korName;

    @Column(nullable = false, length = 50)
    private String engName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 3)
    private String coffeeCode;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    //enum 타입 컬럼 매핑
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    public enum CoffeeStatus{
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status){
            this.status = status;
        }
    }

    //일대다매핑 (orderCoffee랑)
    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void addOrderCoffee(OrderCoffee orderCoffee){
        orderCoffees.add(orderCoffee);
    }
}
