package com.example.fetelix.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    @GetMapping("/")
    public String getMain()
    {
        return "main";
    }
    @GetMapping("/movie/{id}")
    public String movieInfo(@PathVariable Long id, Model model)
    {
        return "redirect:/";
    }
    @PostMapping("/create")
    public String create()
    {
        return "redirect:/";
    }
}
