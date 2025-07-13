package com.example.musicplayer.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller below handles http request to the server

@Controller
@RequestMapping("")//base entry point: http://localhost:8080/
public class MainAppController{
    
    @GetMapping("") //handling get mapping
    public String HomeView(){
        return "index";
    }

    @GetMapping("/home")
    public String MainHomeViw(){
        System.out.println("Home controller hit!");
        return "homePage";
    }
}