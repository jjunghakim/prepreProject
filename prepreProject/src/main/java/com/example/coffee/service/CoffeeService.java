package com.example.coffee.service;

import com.example.coffee.entity.Coffee;
import com.example.coffee.repository.CoffeeRepository;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee createCoffee(Coffee coffee){
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        //중복되는 커피 등록하지 않도록
        verifyExistCoffee(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);

        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee){
        Coffee findCoffee = findVerifiedCoffeeByQuery(coffee.getCoffeeId());

        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> findCoffee.setKorName(korName));
        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> findCoffee.setEngName(engName));
        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> findCoffee.setPrice(price));

        Optional.ofNullable(coffee.getCoffeeStatus())
                .ifPresent(coffeeStatus -> findCoffee.setCoffeeStatus(coffeeStatus));

        return coffeeRepository.save(findCoffee);
    }

    public Coffee findCoffee(long coffeeId){
        return findVerifiedCoffeeByQuery(coffeeId);
    }

    public List<Coffee> findCoffees(){
        return (List<Coffee>) coffeeRepository.findAll();
    }

/*    public List<Coffee> findOrderedCoffees(Order order){
        return order.getOrderCoffees()
                .stream()
                .map(coffeeRef -> findCoffee(coffeeRef.getCoffeeId()))
                .collect(Collectors.toList());
    }*/

    public void deleteCoffee(long coffeeId){
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }

    public Coffee findVerifiedCoffee(long coffeeId){
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee =
                optionalCoffee.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
        return findCoffee;
    }

    // ↕ : findVerifiedCoffee : 내가 찾는 커피 id가 원래 있는 커피가 맞는지
    // 없는 커피라면 exception 던져짐

    // verifyExistCoffee : create 시에 이미 있는 이메일인지에 대해 검증하기 위해
    // 이미 있는 이메일 (등록된) 것 이라면 exception 던져짐


    public void verifyExistCoffee(String coffeeCode){
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(coffee.isPresent())
            throw new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS);
    }

    private Coffee findVerifiedCoffeeByQuery(long coffeeId){
        Optional<Coffee> optionalCoffee = coffeeRepository.findByCoffee(coffeeId);
        Coffee findCoffee =
                optionalCoffee.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));

        return findCoffee;
    }
}
