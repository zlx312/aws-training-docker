package com.linxue.homeworkdocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class MainController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/")
    public String login (){
        return "Hello world, My name is Linxue.";
    }
}
