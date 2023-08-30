package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecoundController {

    @GetMapping("random-quote")
    public String randomQuote(Model m){
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지녀라.1 " +
                        "-허버드-",
                "행복은 습관이다. 그것을 몸에 지녀라.2 " +
                        "-허버드-",
                "행복은 습관이다. 그것을 몸에 지녀라.3 " +
                        "-허버드-",
                "행복은 습관이다. 그것을 몸에 지녀라.4 " +
                        "-허버드-",
                "행복은 습관이다. 그것을 몸에 지녀라.5 " +
                        "-허버드-",
        };
        int randInt =(int)(Math.random()*quotes.length);
        m.addAttribute("randomQuote", quotes[randInt]);

        return "quote";
    }
}
