package com.example.order.controller;

import com.example.order.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v12/orders")
public class OrderController {
    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderDto.Post orderDto){
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@Valid @PathVariable("order-id") @RequestBody OrderDto.Patch orderDto){
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") long orderId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id") long orderId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
