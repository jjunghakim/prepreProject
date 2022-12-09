package com.example.order.service;

import com.example.coffee.repository.CoffeeRepository;
import com.example.coffee.service.CoffeeService;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.member.repository.MemberRepository;
import com.example.member.service.MemberService;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberService memberService;
    final private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository, MemberService memberService, CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order){
        //주문을 한 회원이 존재하는 회원인지 검증
        memberService.findVerifiedMember(order.getMember().getMemberId());

        //커피도 존재하는 커피인지 조회


        return orderRepository.save(order);
    }

    //주문 상태 처리해야함(status 변경될때 update됨 + 시간도 update)
    public Order updateOrder(Order order){
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus(orderStatus));

        findOrder.setModifiedAt(LocalDateTime.now());
        return orderRepository.save(findOrder);
    }

    public Order findOrder(long orderId){
        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public void cancelOrder(long orderId){
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        //이미 확정된 주문은 취소 안됨ㅋ
        if(step >= 2){
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        findOrder.setModifiedAt(LocalDateTime.now());
        orderRepository.save(findOrder);
    }

    //유효한 주문인지 orderId 로 가져온다
    public Order findVerifiedOrder(long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
