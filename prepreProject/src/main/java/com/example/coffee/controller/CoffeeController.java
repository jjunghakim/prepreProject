package com.example.coffee.controller;

import com.example.coffee.dto.CoffeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v12/coffees")
public class CoffeeController {

    @PostMapping
    public ResponseEntity postCoffee(@RequestBody CoffeeDto.Post coffeeDto){
        return new ResponseEntity<>(coffeeDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id")
                                      @RequestBody CoffeeDto.Patch coffeeDto){
        return new ResponseEntity<>(coffeeDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
