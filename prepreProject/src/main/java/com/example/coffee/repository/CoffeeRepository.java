package com.example.coffee.repository;

import com.example.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    Optional<Coffee> findByCoffeeId(Long coffeeId);
}
