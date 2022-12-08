package com.example.order.entity;

import com.example.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // 다대일 매핑(order ~ member)
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") //외래키에 해당하는 컬럼명
    private Member member;

    // 일대다 매핑 (order ~ orderCoffee)
    @OneToMany(mappedBy = "order")
    List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void addOrderCoffees(OrderCoffee orderCoffee){
        this.orderCoffees.add(orderCoffee);
    }


    public void addMember(Member member){
        this.member = member;
    }

    public enum OrderStatus{
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 처리 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription){
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
