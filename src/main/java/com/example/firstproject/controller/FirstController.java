package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model m, String name){
        m.addAttribute("username", name);
        return "greetings";     // greetings.mustache 파일반환
    }
    @GetMapping("/bye/{name}")
    public String seeYouNext(Model m, @PathVariable String name){
        m.addAttribute("username", name);
        return "goodbye";
    }
}
