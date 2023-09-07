package com.example.firstproject.service;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    @Autowired
    CoffeeRepository coffeeRepository;

    public Page<Coffee> list(PageRequest pageRequest) {
        return coffeeRepository.findAll(pageRequest);
    }
}
