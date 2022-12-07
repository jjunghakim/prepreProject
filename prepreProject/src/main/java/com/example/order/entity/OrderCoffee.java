package com.example.order.entity;

import com.example.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;


    //다대다 매핑 ➡ Coffee : OrderCoffee : Order 로 풀어준다
    //                1    :     N      :    1


    //다대일 1
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    //다대일 2
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order){
        /*this.order = order;
        if(!this.order.getOrderCoffees().contains(this)){
            this.order.getOrderCoffees().add(this);
        }*/
    }

    public void addCoffee(Coffee coffee){
        /*this.coffee = coffee;
        if(!this.coffee.getOrderCoffees().contains(this)){
            this.coffee.getOrderCoffee(this);
        }*/
    }
}
