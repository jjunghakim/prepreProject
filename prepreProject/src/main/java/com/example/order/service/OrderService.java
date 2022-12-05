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
//        memberService.findVerifiedMember(order.getMemberId().getId());

        /*order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> {
                    coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId());
                });
        return orderRepository.save(order);
        */
        return null;
    }

    public Order findOrder(long orderId){
        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public void cancleOrder(long orderId){
        Order findOrder = findVerifiedOrder(orderId);
//        int step = findOrder.getOrderStatus().getStepNumber();

        /*if(step >= 2){
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }

        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(findOrder);*/
    }

    public Order findVerifiedOrder(long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
