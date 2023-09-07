package com.example.firstproject.api;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;

    //    @GetMapping("/api/coffees")
//    public List<Coffee> list() {
//        return coffeeService.list();
//    }
    @GetMapping("/api/coffees/{pageNum}")
    public Page<Coffee> list(@PathVariable int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum-1, 5);
        return coffeeService.list(pageRequest);
    }

}
