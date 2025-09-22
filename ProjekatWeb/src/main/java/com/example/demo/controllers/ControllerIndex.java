package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerIndex {

    @GetMapping("/")
    public String pocetna() {
        return "index"; // Spring zna da treba /WEB-INF/jsp/index.jsp
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
