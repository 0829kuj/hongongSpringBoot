package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoffeeDto {
    private Long id;
    private String name;
    private int price;

    private static CoffeeDto create(Coffee coffee){
        return new CoffeeDto(coffee.getId(), coffee.getName(), coffee.getPrice());
    }
}
